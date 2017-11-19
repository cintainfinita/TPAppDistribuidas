package bd;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dto.*;
import exceptions.ComandaException;
import exceptions.FacturaException;
import exceptions.PlatoException;
import exceptions.ReservaException;
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
		return remoteObject.mostrarComandas();
	}
	
	
	//---------------------------------------FACTURAS------------------------------------------------------------
	public void grabarFactura(FacturaDTO factura) throws RemoteException, FacturaException 
	{
		try {
			remoteObject.grabarFactura(factura);
		} catch (Exception e) {
			throw new FacturaException("No se pudo grabar la FACTURA!");
		}
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
	
	
	
}
