package controlador;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.classic.Session;

import com.sun.corba.se.spi.protocol.CorbaMessageMediator;

import dao.CajaDAO;
import dao.CartaDAO;
import dao.ComandaDAO;
import dao.DepositoDAO;
import dao.ElaboradoDAO;
import dao.FacturaDAO;
import dao.ItemComandaDAO;
import dao.MateriaPrimaDAO;
import dao.MesaDAO;
import dao.MozoDAO;
import dao.PlanDeProduccionDAO;
import dao.PlatoDAO;
import dao.RemitoDAO;
import dao.ReservaDAO;
import dao.SalonDAO;
import dao.SectorDAO;
import dao.SemiElaboradoDAO;
import dao.SolicitudDiariaDAO;
import dao.SolicitudIndividualDAO;
import dao.UnidadDAO;
import dao.UsuariosDAO;
import dto.CartaDTO;
import dto.ComandaDTO;
import dto.CombinadorDTO;
import dto.DepositoDTO;
import dto.ElaboradoDTO;
import dto.FacturaDTO;
import dto.ItemComandaDTO;
import dto.ItemFacturaDTO;
import dto.ItemPlanProduccionDTO;
import dto.ItemRemitoDTO;
import dto.MateriaPrimaDTO;
import dto.MesaDTO;
import dto.MozoDTO;
import dto.PlanDeProduccionDTO;
import dto.PlatoDTO;
import dto.RemitoDTO;
import dto.ReservaDTO;
import dto.SalonDTO;
import dto.SectorDTO;
import dto.SemiElaboradoDTO;
import dto.SolicitudDiariaDTO;
import dto.SolicitudIndividualDTO;
import dto.UnidadDTO;
import dto.UsuariosDTO;
import entities.ItemRemitoEntity;
import entities.MesaEntity;
import entities.MozoEntity;
import entities.PlatoEntity;
import entities.SalonEntity;
import entities.SectorEntity;
import enumns.AreaRest;
import enumns.CategoriaPlato;
import enumns.Estado;
import enumns.EstadoRemito;
import enumns.MedioDePago;
import exceptions.FacturaException;
import exceptions.RemitoException;
import negocio.Caja;
import negocio.Carta;
import negocio.Comanda;
import negocio.Deposito;
import negocio.Factura;
import negocio.Ingrediente;
import negocio.ItemComanda;
import negocio.ItemFactura;
import negocio.ItemPlanProduccion;
import negocio.ItemRemito;
import negocio.MateriaPrima;
import negocio.Mesa;
import negocio.Mozo;
import negocio.PlanDeProduccion;
import negocio.Plato;
import negocio.*;
import negocio.Reserva;
import negocio.Salon;
import negocio.Sector;
import negocio.SemiElaborado;
import negocio.SolicitudDiaria;
import negocio.SolicitudIndividual;
import negocio.Unidad;
import negocio.Usuarios;


public class Controlador {
	
	private static Controlador instancia; 
	
	public static Controlador getInstance()
	{
		if (instancia == null)
		return instancia = new Controlador();
		else
		return instancia;
	}
	
/*	public List<View> funcion() {
		List<UnidadView> uv = new ArrayList<UnidadView>();
		List<Unidad> unidades = UnidadDAO.getInstance().getAll();
		for(Unidad u : unidades)
			uv.add(u.toView());
		return uv;
	}*/
	
	
	//----------------------------------------------------------------------------------------------------------------------------------------
		//USUARIOS
		
		
	public boolean autenticarPassword(String login, String password) 
	{
			boolean resu = false;
			resu=UsuariosDAO.getInstance().validarPassword(login, password);
			return resu;
	}
	
	public void grabarUsuarios(UsuariosDTO usuario) 
	{
		new Usuarios(usuario.getLogin(),usuario.getPassword(),usuario.getNombre(),usuario.getApellido(),usuario.getArea()).save();
	}
	
	
	public UsuariosDTO listarUserPorLogin(String login) 
	{
		UsuariosDTO usuario=new UsuariosDTO();
		Usuarios usuarioN=UsuariosDAO.getInstance().getUserPorLogin(login);
		usuario=usuarioN.toDTO();
		return usuario;
	}
	
	
	public void borrarUsuario (UsuariosDTO usuario) 
	{
		new Usuarios(usuario.getLogin(),usuario.getPassword(),usuario.getNombre(),usuario.getApellido(),usuario.getArea()).delete();
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------
	//PLATOS
	public List<PlatoDTO> listarPlatos()
	{
		//aca hay que llamar al DAO, el DAO me devuelve un obje de negocio
		//El obj de negocio hay que pasarlo a DTO
		//este metodo devuelve un DTO
		List<PlatoDTO> listaDTO=new ArrayList<PlatoDTO>();
		List <Plato> listaN=PlatoDAO.getInstance().getPlatos();
		for(Plato p: listaN) 
		{
			listaDTO.add(p.toDTO());
		}
		return listaDTO;
		
	}
	
	public List<PlatoDTO> BuscarPlatosparecidos(String nombre, CategoriaPlato categoriaplato)
	{
		List<Plato> platosN=PlatoDAO.getInstance().getPlatosparecidos(nombre, categoriaplato);
		List<PlatoDTO> platosp=new ArrayList<PlatoDTO>();
		for(Plato p:platosN) 
		{
			platosp.add(p.toDTO());
		}
		return platosp;
	}
	
	public PlatoDTO listarPlatoPorCod(Integer codPlato) 
	{
		Plato platoN=PlatoDAO.getInstance().getPlatoPorCod(codPlato);
		PlatoDTO plato=platoN.toDTO();
		return plato;
	}
	//----------------------------------------------------------------------------------------------------------------------------------
   //COMANDAS
	
	public void guardarComanda(ComandaDTO comanda) 
	{
		Integer codMozo=comanda.getMozo().getDni();
		Mozo mozo=MozoDAO.getInstancia().getMozosByCod(codMozo);
		Integer codMesa=comanda.getMesa().getCodMesa();
		Mesa mesa=MesaDAO.getInstance().getMesaN(codMesa);
		Estado estado=comanda.getEstado();
		new Comanda(mozo,mesa,estado).save();
	}
	
	
	public List<ComandaDTO> listarComandas()
	{
		List<ComandaDTO> listaCom=new ArrayList<ComandaDTO>();
		List<Comanda> listaComN=ComandaDAO.getInstance().getComandas();
		for(Comanda c:listaComN)
		{
			listaCom.add(c.toDTO());
			
		}
		return listaCom;
	}
	
	
	public ComandaDTO listarComandasPorCod(Integer codComanda)
	{
		ComandaDTO comanda=new ComandaDTO(); 
		Comanda comandaN=ComandaDAO.getInstance().obtenerComandaByCod(codComanda);
		comanda=comandaN.toDTO();
		return comanda;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	//ITEM COMANDA
	public boolean guardarItemComanda(ItemComandaDTO itemComanda) 
	{
		//(Integer cantidad, Plato plato,Comanda comanda
		Integer codPlato=itemComanda.getPlato().getCodigo();
		Plato plato=PlatoDAO.getInstance().getPlatoPorCod(codPlato);
		List <Ingrediente> ingredientesplato=PlatoDAO.getInstance().getIngredientes(plato);
		boolean haycantidades=ItemComandaDAO.getInstance().HaySuficiente(ingredientesplato, itemComanda.getCantidad());
		if (haycantidades==true)
		{
		Integer codComanda=itemComanda.getComanda().getCodComanda();
		Comanda comanda=ComandaDAO.getInstance().obtenerComandaByCod(codComanda);
		new ItemComanda(itemComanda.getCantidad(),itemComanda.getEstado(), plato,comanda).save();
			return true;
		}
		else
			return false;
	}
	
	//OBTENER ITEMS DE COMANDAS POR AREA
	public List<ItemComandaDTO> getItemsPendientesxArea(AreaRest area) {
		List<ItemComandaDTO> items=new ArrayList<ItemComandaDTO>();
		List<ItemComanda> itemsnegocio=ItemComandaDAO.getInstance().getItemsPendientesxArea(area);
		for(ItemComanda item:itemsnegocio)
		{
			items.add(item.toDTO());
		}
		return items;
	}
	
	//OBTENER ITEMS DE COMANDAS POR COMANDA COD
	
	public List<ItemComandaDTO> obtenerItemsComandaByCodComanda(int parseInt) {
		List<ItemComandaDTO> items=new ArrayList<ItemComandaDTO>();
		List<ItemComanda> itemsnegocio=ItemComandaDAO.getInstance().obtenerItemComandasAbiertasxMesa(parseInt);
		for(ItemComanda item:itemsnegocio)
		{
			items.add(item.toDTO());
		}
		return items;
	}

	public void itemComandaLista(int coditemcomanda)
	{
		ItemComanda itemnegocio=ItemComandaDAO.getInstance().obtenerItemComandaByCod(coditemcomanda);
		ItemComandaDAO.getInstance().reducirstockxItemComanda(itemnegocio);
		ItemComandaDAO.getInstance().updateitemComandatoFinalizada(itemnegocio);
	
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	//FACTURAS

	public void facturarMesa(int codMesa, MedioDePago formadepago) 
	{
		FacturaDAO.getInstance().facturarMesa(codMesa, formadepago);
	}
	
	public  void guardarFactura(FacturaDTO factura) 
	{
		Integer codMesa=factura.getMesa().getCodMesa();
		Mesa mesa=MesaDAO.getInstance().getMesaN(codMesa);
		new Factura(factura.getFecha(),factura.getImporte(),factura.getMedioPago(),mesa).save();
		
	} 
	
	public List<FacturaDTO> listarFacturas()
	{
		List<FacturaDTO> facturas=new ArrayList<FacturaDTO>();
		List<Factura> listaF=FacturaDAO.getInstance().getFacturas();
		for(Factura f:listaF) 
		{
			facturas.add(f.toDTO());
		}
		return facturas;
		
	}
	
	public FacturaDTO listarFacturaByCod(int nroFact)
	{
		FacturaDTO factura=new FacturaDTO();
		Factura facturaN=FacturaDAO.getInstance().obtenerFacturaByCod(nroFact);
		factura=facturaN.toDTO();
		return factura;
		
	}
	
	public List<ItemFacturaDTO> obtenerItemsFacturaByCodFactura(Integer codFactura)
	{
		List <ItemFacturaDTO> items=new ArrayList <ItemFacturaDTO>();
		List <ItemFactura> itemsnegocio=FacturaDAO.getInstance().obtenerItemFacturaByCod(codFactura);
		for (ItemFactura i:itemsnegocio)
		{
		 items.add(i.toDTO());
		}
		return items;
	}
	//-----------------------------------------------------------------------------------------------------------------------------------
	//RESERVAS
	
	public void guardarReserva(ReservaDTO reserva)
	{
		new Reserva(reserva.getNombre(),reserva.getFechayHora(),reserva.getCantidadComensales()).save();
	}
	
	public List<ReservaDTO> listarReservas()
	{
		List<ReservaDTO> listaReservas=new ArrayList<ReservaDTO>();
		List<Reserva> reservaN=ReservaDAO.getInstancia().getReservas();
		for(Reserva r:reservaN) 
		{
			listaReservas.add(r.toDTO());
		}
		return listaReservas;
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	//MOZOS
	public List<MozoDTO> listarMozos()
	{
		List<MozoDTO> mozos=new ArrayList<MozoDTO>();
		List<Mozo> listaM=MozoDAO.getInstancia().getMozos();
		for(Mozo m:listaM) 
		{
			mozos.add(m.toDTO());
		}
		
		return mozos;
	}
	
	
	//COMISIONES MOZO
	
	public double listarComisionesAPagar (Date FechaDesde,Date FechaHasta) 
	{
		double comisiones=0;
		comisiones=MozoDAO.getInstancia().getComisionesapagar(FechaDesde, FechaHasta);
		return comisiones;
	}
	
	
	public List<String[]> ResultadoComisionesMozo (Date FechaDesde,Date FechaHasta)
	{
		List<String[]> resuComisiones=MozoDAO.getInstancia().ResultadoComisiones(FechaDesde,FechaHasta);
		return resuComisiones;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------
	// MESAS
	public List<MesaDTO> listarMesas() 
	{
		List<MesaDTO> mesas=new ArrayList<MesaDTO>();
		List<Mesa> listaM=MesaDAO.getInstance().getMesas();
		for(Mesa m:listaM) 
		{
			mesas.add(m.toDTO());
		}
		return mesas;
	}
	
	public MesaDTO listarMesaPorCod(Integer codMesa) 
	{
		MesaDTO mesa=new MesaDTO();
		Mesa mesaN=MesaDAO.getInstance().obtenerMesaPorCod(codMesa);
		mesa=mesaN.toDTO();
		return mesa;
	}
	
	
	public List<MesaDTO> listarMesasLibres()
	{
		List<MesaDTO> mesas=new ArrayList<MesaDTO>();
		List<Mesa> mesasN=MesaDAO.getInstance().getMesasLibres();
		for(Mesa m:mesasN)
		{
			mesas.add(m.toDTO());
		}
		return mesas;
	}
	public List<MesaDTO> listarMesasOcupadas()
	{
		List<MesaDTO> mesas=new ArrayList<MesaDTO>();
		List<Mesa> mesasN=MesaDAO.getInstance().getMesasOcupadas();
		for(Mesa m:mesasN)
		{
			mesas.add(m.toDTO());
		}
		return mesas;
	}
	public List<MesaDTO> listarMesasFacturables()
	{
		List<MesaDTO> mesas=new ArrayList<MesaDTO>();
		List<Mesa> mesasN=MesaDAO.getInstance().getMesasFacturables();
		for(Mesa m:mesasN)
		{
			mesas.add(m.toDTO());
		}
		return mesas;
	}
	
	public void ocuparMesaPorCod(Integer codMesa)
	{
		Mesa mesaN=MesaDAO.getInstance().obtenerMesaPorCod(codMesa);
		MesaDAO.getInstance().updateMesaToOcupada(mesaN);
	}
	
	public Integer combinarMesasPorCod(Integer codmesa1, Integer codmesa2)
	{
		Mesa mesa1=MesaDAO.getInstance().getMesaN(codmesa1);
		Mesa mesa2=MesaDAO.getInstance().getMesaN(codmesa2);
		Mesa mesaCombinada=MesaDAO.getInstance().combinarMesa(mesa1, mesa2);
		return mesaCombinada.getCodMesa();
	}

	public void guardarMesa(MesaDTO mesa) 
	{
		Mozo mozo=MozoDAO.getInstancia().getMozosByCod(mesa.getMozo().getDni());
		Sector sector=SectorDAO.getInstancia().getSectorByCod(mesa.getSector().getCodSector());
		//nteger cantidadPersonas, Integer capacidad, Integer estado, MozoEntity mozo, SectorEntity sector
		new Mesa(mesa.getCantidadPersonas(),mesa.getCapacidad(),mesa.getEstado(),mozo,sector).save();
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------
    //LISTAR SECTORES
	
	public void guardarSectores(SectorDTO sector)
	{
		//String descripcion, SalonEntity salon
		Salon salon=SalonDAO.getInstancia().getSalonByCod(sector.getSalon().getCodSalon());
		new Sector(sector.getDescripcion(),salon).save();
	}
	
	public List<SectorDTO> listarSectores()
	{
		List<SectorDTO> sectores=new ArrayList<SectorDTO>();
		List<Sector> listaS=SectorDAO.getInstancia().getSectores();
		for(Sector s:listaS)
		{
			sectores.add(s.toDTO());
		}
		return sectores;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
    //LISTAR SALONES
	
	public List<SalonDTO> listarSalones()
	{
		List<SalonDTO> salones=new ArrayList<SalonDTO>();
		List<Salon> listaS=SalonDAO.getInstancia().getSalones();
		for(Salon s:listaS)
		{
			salones.add(s.toDTO());
		}
		return salones;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------
    //LISTAR DEPOSITOS
	public List<DepositoDTO> listarDepositos()
	{
		List<DepositoDTO> depositos=new ArrayList<DepositoDTO>();
		List<Deposito> listaS=DepositoDAO.getInstancia().getDepositos();
		for(Deposito s:listaS)
		{
			depositos.add(s.toDTO());
		}
		return depositos;
	}
	
	//OBTENER DEPOSITO POR CODIGO
	public DepositoDTO DepositoByCod(Integer codDeposito) {
		// TODO Auto-generated method stub
		Deposito depo=DepositoDAO.getInstancia().getDepositoByCod(codDeposito);
		DepositoDTO deposito=depo.toDTO();
		return deposito;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
    //LISTAR FACTURADO EN CAJA
	public double listarTotalFacturadoCaja (Date FechaDesde,Date FechaHasta)
	{
		double importe=CajaDAO.getInstancia().getTotalFacturadoCaja(FechaDesde, FechaHasta);
		return importe;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
    //UNIDADES
	//LISTAR TODAS LAS UNIDADES
	public List<UnidadDTO> listarUnidades() {
		// TODO Auto-generated method stub
		
		List<UnidadDTO> unidadesDTO=new ArrayList<UnidadDTO>();
		List<Unidad> unidadesCo=UnidadDAO.getInstance().getUnidades();
		
		for(Unidad uniCom:unidadesCo) {
			unidadesDTO.add(uniCom.toDTO());
		}
		return unidadesDTO;
	}
	
	//OBTENER UNIDAD POR DESCRIPCION
	public UnidadDTO UnidadByDescp(String descp) {
		// TODO Auto-generated method stub
		Unidad uni = UnidadDAO.getInstance().getUnidadByDescp(descp);
		UnidadDTO unidad =uni.toDTO();
		return unidad;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
    //MATERIA PRIMA
	//GRABAR MATERIA PRIMA
	public void grabarMateriaPrima(MateriaPrimaDTO materia) {
		// TODO Auto-generated method stub
		Unidad uni= UnidadDAO.getInstance().getUnidadByDescp(materia.getUnidadUso().getDescripcion());
		//Deposito depo= new Deposito();
		Deposito depo=DepositoDAO.getInstancia().getDepositoByCod(materia.getDeposito().getCodDeposito());
		new MateriaPrima(materia.getDescripcion(), uni, materia.getCantidad(), depo).save();
		
	}
	public List<MateriaPrimaDTO> listarStock() {
		// TODO Auto-generated method stub
		
		List<MateriaPrimaDTO> materiaprimaDTO=new ArrayList<MateriaPrimaDTO>();
		List<MateriaPrima> materiaprimanegocio=MateriaPrimaDAO.getInstance().getMateriales();
		
		for(MateriaPrima mp:materiaprimanegocio) {
			materiaprimaDTO.add(mp.toDTO());
		}
		return materiaprimaDTO;
	}

	//--------------------------------------------------------------------------------------------------------------------------------------------------
    //CARTAS
	//BUSCAR CARTA POR CODIGO

	public CartaDTO listarCartaPorCod(int nrocarta) 
	{
		CartaDTO carta=new CartaDTO();
		Carta cartaN=CartaDAO.getInstance().obtenerCartaPorCod(nrocarta);
		carta=cartaN.toDTO();
		return carta;
	}
	
	public List<PlatoDTO> listarPlatosporCodCarta(int nrocarta)
	{
		List<PlatoDTO> platos=new ArrayList<PlatoDTO>();
		List<Plato> platosN=CartaDAO.getInstance().getPlatosporCodCarta(nrocarta);
		for(Plato p:platosN)
		{
			platos.add(p.toDTO());
		}
		return platos;
	}
	
	public List<CartaDTO> listarCartas()
	{
		List<CartaDTO> cartas=new ArrayList<CartaDTO>();
		List<Carta> cartasN=CartaDAO.getInstance().getCartas();
		for(Carta c:cartasN) 
		{
			cartas.add(c.toDTO());
		}
		return cartas;
	}
	//--------------------------------------------------------------------------------------------------------------------------------------------------
    //PLAN DE PRODUCCION
	
	public List<PlanDeProduccionDTO> listarPlanesProduccion()
	{
		List<PlanDeProduccionDTO> planes=new ArrayList<PlanDeProduccionDTO>();
		List<PlanDeProduccion> planesN=PlanDeProduccionDAO.getInstance().getPlanesProduccion();
		for(PlanDeProduccion p: planesN) 
		{
			planes.add(p.toDTO());
		}
		return planes;
	}
	
	public PlanDeProduccionDTO listarPlanByCod(int codPdp) 
	{
		PlanDeProduccionDTO pdp=new PlanDeProduccionDTO();
		PlanDeProduccion pdpN=PlanDeProduccionDAO.getInstance().getPlanByCod(codPdp);
		pdp=pdpN.toDTO();
		return pdp;
		
	}
	
	
	public void grabarPdP(PlanDeProduccionDTO pdp) {
		List<ItemPlanProduccion> itemspdp = new ArrayList<ItemPlanProduccion>();
		new PlanDeProduccion(Estado.EnProceso,itemspdp,pdp.getFechaplan()).save();
		
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------------------------
    //ITEM PLAN DE PRODUCCION
	public List<ItemPlanProduccionDTO> listarItemPlanPorCodPlan(int nroPlanDeProduccion)
	{
		List<ItemPlanProduccionDTO> items=new ArrayList<ItemPlanProduccionDTO>();
		List<ItemPlanProduccion> itemsN=PlanDeProduccionDAO.getInstance().getItemPlanPorCodPlan(nroPlanDeProduccion);
		for(ItemPlanProduccion i:itemsN)
		{
			items.add(i.toDTO());
		}
		return items;
	}
	
	public SemiElaboradoDTO getSemiElaboradoByCod(int parseInt) {
		
		SemiElaboradoDTO semi=new SemiElaboradoDTO();
		SemiElaborado semiN=SemiElaboradoDAO.getInstance().getSemisPorCod(parseInt);
		semi=semiN.toDTO();
		return semi;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//REMITO
	
	public void guardarRemito(RemitoDTO remito)
	{
		//Integer codigoProveedor, Date fecha,List<ItemRemitoEntity> itemsRemito,EstadoRemito estado
		//List<ItemRemito> items=remito.getItemsRemito();
		Deposito deposito = DepositoDAO.getInstancia().getDepositoByCod(remito.getDeposito().getCodDeposito());
		new Remito(remito.getCodigoProveedor(),remito.getFecha(),deposito,EstadoRemito.Procesado).save();

	}
	
	public RemitoDTO getRemitoByCod(Integer parseInt) 
	{
		Remito rem=new Remito();
		RemitoDTO remDTO = new RemitoDTO();
		rem = RemitoDAO.getInstance().getRemitoByCod(parseInt);
		remDTO=rem.toDTO();
		return remDTO;
		
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//ITEM REMITO
	
	public void guardarItemRemito(ItemRemitoDTO itemRemito)
	{
		//MateriaPrima materiaprima, Float cantidad, EstadoRemito estadoremito, Remito remito
		MateriaPrima materia=MateriaPrimaDAO.getInstance().getMateriaPrimaByCod(itemRemito.getMateriaprima().getCodigo());
		Remito remito=RemitoDAO.getInstance().getRemitoByCod(itemRemito.getRemito().getCodRemito());
		new ItemRemito(materia,itemRemito.getCantidad(),itemRemito.getEstadoremito(),remito).save();
		RemitoDAO.getInstance().ingresarMateriaPrimaporItemRemito ();
	}

	public List<RemitoDTO> listarRemitos()
	{
		List<RemitoDTO> remitos=new ArrayList<RemitoDTO>();
		List<Remito> remitoN=RemitoDAO.getInstance().getRemitos();
		for(Remito r: remitoN)
		{	
			
			remitos.add(r.toDTO());
		}
		return remitos;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//MATERIA PRIMA
	public MateriaPrimaDTO listarMateriaPrimaByCod(Integer codMaterial) 
	{
		MateriaPrimaDTO materias=new MateriaPrimaDTO();
		MateriaPrima mpsN=MateriaPrimaDAO.getInstance().getMateriaPrimaByCod(codMaterial);
		materias=mpsN.toDTO();
		return materias;
		
	}
	
	public void actualizarMateriaPrima(MateriaPrimaDTO materia) {
		// TODO Auto-generated method stub
		MateriaPrima mate =new  MateriaPrima(materia.getDescripcion(), null, materia.getCantidad(), null);
		mate.setCodigo(materia.getCodigo());
		mate.update();
	}
	
	public void eliminarMateria(MateriaPrimaDTO materia) {
		// TODO Auto-generated method stub
		MateriaPrima mate =new  MateriaPrima(materia.getDescripcion(), null, materia.getCantidad(), null);
		mate.setCodigo(materia.getCodigo());
		mate.delete();
	}


	//----------------------------------------------------------------------------------------------------------------------------------
	//SOLICITUD INDIVIDUAL
	public List<SolicitudIndividualDTO> listarSolicitudesIndividuales()
	{
		List<SolicitudIndividualDTO> solicitudes=new ArrayList<SolicitudIndividualDTO>();
		List<SolicitudIndividual> soliN=SolicitudIndividualDAO.getInstance().getsolicitudes();
		for(SolicitudIndividual s: soliN)
		{
			solicitudes.add(s.toDTO());
		}
		return solicitudes;
	}
	
	public List<SolicitudIndividualDTO> listarSolicitudesNoDiarias()
	{
		List<SolicitudIndividualDTO> solicitudes=new ArrayList<SolicitudIndividualDTO>();
		List<SolicitudIndividual> soliN=SolicitudIndividualDAO.getInstance().getsolicitudesIndividualesnoDiarias();
		for(SolicitudIndividual s: soliN)
		{
			solicitudes.add(s.toDTO());
		}
		return solicitudes;
	}
	
	public void UnirSolicitudesIndividuales (List<CombinadorDTO> resultado)
	{
		Deposito deposito=new Deposito();
		deposito.setCodDeposito(1);
		List <SolicitudIndividual> solicitudestodiaria=new ArrayList <SolicitudIndividual>();
		
		for (CombinadorDTO resultado2 : resultado)
		{
			System.out.println("sys control"+resultado2);
			SolicitudIndividual solicitud=SolicitudIndividualDAO.getInstance().getSolicitudIndividualPorId(Integer.parseInt(resultado2.getId()));
			System.out.println("sys control"+solicitud.getResponsable());
			
			solicitudestodiaria.add(solicitud);
		}
		
		SolicitudDiaria soli = new SolicitudDiaria();
		soli.setDeposito(deposito);
		soli.setSolicitudes(solicitudestodiaria);
		SolicitudDiariaDAO.getInstance().save(soli);
		
	}
	
	
	public void guardarSolicitudIndividual(SolicitudIndividualDTO solicitud)
	{
		MateriaPrima mp=MateriaPrimaDAO.getInstance().getMateriaPrimaByCod(solicitud.getMateriaprima().getCodigo());
		new SolicitudIndividual(solicitud.getArea(),solicitud.getResponsable(),solicitud.getMotivo(), mp,solicitud.getCantidad(),solicitud.getEstado()).save();
	}

	public List<SemiElaboradoDTO> mostrarSemiElaborados() {
		List<SemiElaboradoDTO> semis=new ArrayList<SemiElaboradoDTO>();
		List<SemiElaborado> semiN=SemiElaboradoDAO.getInstance().getSemis();
		for(SemiElaborado s: semiN)
		{	
			
			semis.add(s.toDTO());
		}
		return semis;
	}

	public void grabarItemPdP(ItemPlanProduccionDTO itemPdP) {
		SemiElaborado semi=SemiElaboradoDAO.getInstance().getSemisPorCod((itemPdP.getSemielaborado().getNumero()));
		PlanDeProduccion pdp=PlanDeProduccionDAO.getInstance().getPlanByCod(itemPdP.getPlandeProduccion().getCodigoPDP());
		new ItemPlanProduccion(semi,itemPdP.getCantidad(),0,pdp).save();
	
		
	}

	public List<SolicitudDiariaDTO> listarSolicitudesDiarias()
	{
		List<SolicitudDiariaDTO> solicitudes=new ArrayList<SolicitudDiariaDTO>();
		List<SolicitudDiaria> soliN=SolicitudDiariaDAO.getInstance().getsolicitudesDiarias();
		for(SolicitudDiaria s: soliN)
		{
			solicitudes.add(s.toDTO());
		}
		return solicitudes;
	}
	
	
	//ELABORADOS
	public void grabarElaborado(ElaboradoDTO elab) {
		// TODO Auto-generated method stub
		PlanDeProduccion  plan=new PlanDeProduccion();
		plan=plan.getPlanPorCod(elab.getPlan().getCodigoPDP());
		Unidad uni = new Unidad();
		uni=uni.obtenerUniPorCod(elab.getUnidad().getCodigo());
		List<SemiElaborado> semis=new ArrayList<SemiElaborado>();
		Unidad uni2= new Unidad();
		for(SemiElaboradoDTO semi: elab.getComponentes()) {
			 uni2=uni2.obtenerUniPorCod(semi.getUnidad().getCodigo());
			semis.add(new SemiElaborado(semi.getTipo(), semi.getCalidad(), semi.getDescripcion(),semi.getCantidad(), semi.getCaducidad(), uni2));
		}
		Elaborado elaborado=new Elaborado(elab.getTipo(), elab.getCalidad(), elab.getDescripcion(),plan, elab.getCantidad(), elab.getCaducidad(),uni, semis);
		elaborado.save();
	}

	public ElaboradoDTO getElaboradoByCod(int parseInt) {
		// TODO Auto-generated method stub
		Elaborado ela=new Elaborado();
		ela=ela.getElaboradoByCod(parseInt);
		ElaboradoDTO ela2= ela.toDTO();
		ela2.setCodElab(ela.getCodigoProd());
		return ela2;
	}

	public void actualizarElaborado(ElaboradoDTO elab) {
		// TODO Auto-generated method stub
		Elaborado elaborado= new Elaborado();
		elaborado=elaborado.getElaboradoByCod(elab.getCodElab());
		elaborado.setCantidad(elab.getCantidad());
		elaborado.setDescripcion(elab.getDescripcion());
		elaborado.setTipo(elab.getTipo());
	//Ahora update
		elaborado.update();
	}


	
	
	//----------------------------------------------------------------------------------------------------------------------------------
	//SOLICITUD DIARIA
}
