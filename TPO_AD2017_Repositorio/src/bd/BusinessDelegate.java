package bd;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dto.*;
import exceptions.ComandaException;
import exceptions.DepositoException;
import exceptions.FacturaException;
import exceptions.MesaException;
import exceptions.MozoException;
import exceptions.PlatoException;
import exceptions.ReservaException;
import exceptions.SalonException;
import exceptions.SectorException;
import interfazRemota.manejoNegocio;


public class BusinessDelegate
{

	private static BusinessDelegate instance;
	private manejoNegocio remoteObject;
	
	public BusinessDelegate() throws RemoteException {
		super();
		try {
			remoteObject=(manejoNegocio) Naming.lookup("//localhost/ObjetoRemoto");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static BusinessDelegate getInstance() throws RemoteException {
		if(instance == null) {
			instance = new BusinessDelegate();
		}
		return instance;
	}
	
	

/*	
	public void insertarRubro(RubroDto rubro) throws RemoteException {
		this.remoteObject.insertarRubro(rubro);		
	}*/
	//---------------------------------------PLATOS------------------------------------------------------------
	public List<PlatoDTO> listarPlatos() throws RemoteException, PlatoException
	{
		try {
			return remoteObject.listarPlatos();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			throw new PlatoException("Error! no hay plato");
		}
	}
	
	public List<PlatoDTO> BuscarPlatosparecidos(String nombre, String categoriaplato) throws RemoteException, PlatoException 
	{
		return remoteObject.BuscarPlatosparecidos(nombre, categoriaplato);
	}
	//---------------------------------------COMANDAS------------------------------------------------------------
	public void grabarComanda(ComandaDTO comanda) throws RemoteException, ComandaException 
	{
		try {
			remoteObject.grabarComanda(comanda);
		} catch (Exception e) {
			throw new ComandaException("No se pudo grabar la comanda!");
		}
	}
	
	public List<ComandaDTO> mostrarComandas() throws RemoteException, ComandaException 
	{
		try {
			return remoteObject.mostrarComandas();
		} catch (Exception e) {
			throw new ComandaException("No se pudo LISTAR las comandas!");
		}
	}
	
	public ComandaDTO BuscarComandasPorCod(Integer codComanda) throws RemoteException, ComandaException
	{
		return remoteObject.BuscarComandasPorCod(codComanda);
	}
	
	//---------------------------------------FACTURAS------------------------------------------------------------
	public void grabarFactura(FacturaDTO factura) throws RemoteException, FacturaException 
	{
		try {
			remoteObject.grabarFactura(factura);
		} catch (Exception e) {
			//throw new FacturaException("No se pudo grabar la FACTURA!");
		}
	}
	
	public List<FacturaDTO> mostrarFacturas() throws RemoteException, FacturaException 
	{
		return remoteObject.mostrarFacturas();
	}
	//---------------------------------------RESERVAS------------------------------------------------------------
	
	public void grabarReserva(ReservaDTO reserva) throws RemoteException, ReservaException 
	{
		try {
			remoteObject.grabarReserva(reserva);
		} catch (Exception e) {
			throw new ReservaException("No se pudo GRABAR la RESERVA");
		}
	}
	
	public List<ReservaDTO> mostrarReservas()throws RemoteException,ReservaException
	{
		try {
			return remoteObject.mostrarReservas();
		} catch (Exception e) {
			throw new ReservaException("No se pudo LISTAR las RESERVAS");
		}
	}
	
	//-----------------------------------MOZOS----------------------------------------------------------------------
	public List<MozoDTO> mostrarMozos() throws RemoteException,MozoException
	{
		return remoteObject.mostrarMozos();
	}
	
	//-----------------------------------MESAS-------------------------------------------------------------------------
	public List<MesaDTO> mostrarMesas() throws RemoteException,MesaException
	{
		return remoteObject.mostrarMesas();
	}
	
	//-----------------------------------SECTORES-------------------------------------------------------------------------
	public List<SectorDTO> mostrarSectores() throws RemoteException, SectorException
	{
		return remoteObject.mostrarSectores();
	}
	
	//-----------------------------------SALONES-------------------------------------------------------------------------
	public List<SalonDTO> mostrarSalones() throws RemoteException, SalonException 
	{
		return remoteObject.mostrarSalones();
	}
	
	//-----------------------------------DEPOSITOS-------------------------------------------------------------------------
	public List<DepositoDTO> mostrarDepositos() throws RemoteException, DepositoException
	{
		return remoteObject.mostrarDepositos();
	}
}
