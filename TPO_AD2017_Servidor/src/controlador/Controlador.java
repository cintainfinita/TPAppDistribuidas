package controlador;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.protocol.CorbaMessageMediator;

import dao.PlatoDAO;
import dto.ComandaDTO;
import dto.PlatoDTO;
import enumns.Estado;
import negocio.Caja;
import negocio.Comanda;
import negocio.Mesa;
import negocio.Mozo;
import negocio.Plato;


public class Controlador {
	
	private static Controlador instancia; 
	
	public static Controlador getInstance(){
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
	
	
	public void guardarComanda(ComandaDTO comanda)
	{
		Comanda comandaN=new Comanda();
		//comandaN.setMozo(comanda.getMozo()); //tengo que pasar comandaDTO a comandaNegocio
		comandaN.setEstado(comanda.getEstado());
		//(Mozo mozo, Mesa mesa,Caja caja,Estado estado) 
		comandaN.save() ; //no estoy segura si no va a pinchar porque falta el id,pero en la bd es autonumerico
	}
	
}
