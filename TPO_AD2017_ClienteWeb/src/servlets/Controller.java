package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bd.BusinessDelegate;
import dto.ComandaDTO;
import dto.FacturaDTO;
import dto.ItemComandaDTO;
import dto.ItemFacturaDTO;
import dto.MesaDTO;
import dto.MozoDTO;
import dto.PlatoDTO;
import dto.ReservaDTO;
import dto.SectorDTO;
import enumns.AreaRest;
import enumns.Estado;
import enumns.EstadoItemComanda;
import exceptions.ComandaException;
import exceptions.FacturaException;
import exceptions.MesaException;
import exceptions.MozoException;
import exceptions.PlatoException;
import exceptions.ReservaException;
import exceptions.SectorException;
import exceptions.UsuarioException;
import exceptions.itemComandaException;



/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
@MultipartConfig
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if(opcion == null){
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		else {

			 HttpSession session=request.getSession();  
				
			
				if(opcion.equals("login")){
				
					String usuario = request.getParameter("usuario");
					String clave = request.getParameter("clave");
					Boolean ok = false;
					try {
						ok = BusinessDelegate.getInstance().verificarPassword(usuario, clave); 
						ok = true;
						
					} catch (UsuarioException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (ok) {
					     session.setAttribute("usuario",usuario);  
						 RequestDispatcher rd = request.getRequestDispatcher("/menu.jsp");
						 rd.forward(request, response);
					} else
					{
						 RequestDispatcher rd = request.getRequestDispatcher("/login.jsp?mensaje=Usuario o Clave Incorrectos");
						 rd.forward(request, response);
						
					}
				}
				
				if(opcion.equals("logout")){
					
					session.invalidate();    
		            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
					rd.forward(request, response);
					}
				
				if(opcion.equals("cargarArchivo")){
					
					String descripcion = request.getParameter("descripcion"); 
				    Part filePart = request.getPart("archivo");
				    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				    InputStream fileContent = filePart.getInputStream();
				    int read = 0;
			        final byte[] bytes = new byte[1024];

			        while ((read = fileContent.read(bytes)) != -1) {
			            System.out.write(bytes, 0, read);
			        }
			        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.forward(request, response);
									}
				
			
			if(opcion.equals("verPlatos")){
				
				List<PlatoDTO> platos = new ArrayList<PlatoDTO>();
				try {
					platos = BusinessDelegate.getInstance().listarPlatos();
					request.setAttribute("platos", platos);
					RequestDispatcher rd = request.getRequestDispatcher("/verPlatos.jsp");
					rd.forward(request, response);
				} catch (PlatoException e) {
					System.out.println(e.getMessage());
				}

			}
			
			if(opcion.equals("mesas")){
				
				List<MesaDTO> mesas = new ArrayList<MesaDTO>();
				try {
					mesas = BusinessDelegate.getInstance().mostrarMesasLibres();
					request.setAttribute("mesas", mesas);
					RequestDispatcher rd = request.getRequestDispatcher("/mesas.jsp");
					rd.forward(request, response);
				
				} catch (MesaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
if(opcion.equals("ocuparMesa")){
	 
	String[] mesa = request.getParameterValues("mesaelegida[]");
	
	try {
		System.out.println(mesa[0]);
		BusinessDelegate.getInstance().ocuparMesaPorCod(Integer.parseInt(mesa[0]));
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MesaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
				
			}

if(opcion.equals("combinarMesas")){
	 
String[] mesa = request.getParameterValues("mesaelegida[]");
	Integer nuevamesa = 0;
	try {
		nuevamesa = BusinessDelegate.getInstance().combinarMesasPorCod(Integer.parseInt(mesa[0]),Integer.parseInt(mesa[1]));
		RequestDispatcher rd = request.getRequestDispatcher("/decirMesa.jsp?nuevamesa="+nuevamesa);
		rd.forward(request, response);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (MesaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
					
			}
			

if(opcion.equals("facturarMesa")){
	
	List<MesaDTO> mesas = new ArrayList<MesaDTO>();
	/*try {
		//mesas = BusinessDelegate.getInstance().mostrarMesasFacturables();
		request.setAttribute("mesas", mesas);
		RequestDispatcher rd = request.getRequestDispatcher("/facturarMesa.jsp");
		rd.forward(request, response);
	
	} catch (MesaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

}

if(opcion.equals("facturarMesaYa")){
	
	String mesa = request.getParameter("mesaelegida"); 
	
	/*try {
		//BusinessDelegate.getInstance().facturarMesa(Integer.parseInt(mesa));
		RequestDispatcher rd = request.getRequestDispatcher("/verFacturas.jsp");
		rd.forward(request, response);
	
	} catch (MesaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

}


if(opcion.equals("verDetalleFactura")){
	
	String facturaelegida = request.getParameter("facturaelegida"); 
	List<ItemFacturaDTO> items = new ArrayList<ItemFacturaDTO>();

	
	try { 
		items = BusinessDelegate.getInstance().obtenerItemsFacturaByCodFactura(Integer.parseInt(facturaelegida));
		request.setAttribute("items", items);
		RequestDispatcher rd = request.getRequestDispatcher("/verDetalleFactura.jsp");
		rd.forward(request, response);
	
	} catch (FacturaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

if(opcion.equals("verMozos")){
				
				List<MozoDTO> mozos = new ArrayList<MozoDTO>();
				try {
					mozos = BusinessDelegate.getInstance().mostrarMozos();
					request.setAttribute("mozos", mozos);
					RequestDispatcher rd = request.getRequestDispatcher("/verMozos.jsp");
					rd.forward(request, response);
				} catch (MozoException e) {
					System.out.println(e.getMessage());
				}

			}
		
		if(opcion.equals("verReservas")){
			
			List<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
			try {
				reservas = BusinessDelegate.getInstance().mostrarReservas();
				request.setAttribute("reservas", reservas);
				RequestDispatcher rd = request.getRequestDispatcher("/verReservas.jsp");
				rd.forward(request, response);
			} catch (ReservaException e) {
				System.out.println(e.getMessage());
			}

		}
		
		
		if(opcion.equals("verFacturas")){
			
			List<FacturaDTO> facturas = new ArrayList<FacturaDTO>();
			try {
				facturas = BusinessDelegate.getInstance().mostrarFacturas();
				request.setAttribute("facturas", facturas);
				RequestDispatcher rd = request.getRequestDispatcher("/verFacturas.jsp");
				rd.forward(request, response);
			} catch (FacturaException e) {
				System.out.println(e.getMessage());
			}

		}
		

		if(opcion.equals("cargarReserva")){
			String nombre = request.getParameter("nombre");
			String startDateStr = request.getParameter("fecha");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//surround below line with try catch block as below code throws checked exception
			Date startDate = null;
			try {
				startDate = sdf.parse(startDateStr);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
			ReservaDTO reserva = new ReservaDTO(nombre,startDate,cantidad);
			try {
			
				BusinessDelegate.getInstance().grabarReserva(reserva);
				
			} catch (ReservaException e) {
				System.out.println(e.getMessage());
			}
			RequestDispatcher rd = request.getRequestDispatcher("Controller?opcion=verReservas");
			rd.forward(request, response);
		}
		
		if(opcion.equals("verComandas")){
			
			 if ((session.getAttribute("usuario") != null) & session.getAttribute("usuario").equals("admin"))
			  { 
			
			List<ComandaDTO> comandas = new ArrayList<ComandaDTO>();
			try {
				comandas = BusinessDelegate.getInstance().mostrarComandas();
				
				request.setAttribute("comandas", comandas);
				RequestDispatcher rd = request.getRequestDispatcher("/verComandas.jsp");
				rd.forward(request, response);
			} catch (ComandaException e) {
				System.out.println(e.getMessage());
			}

		}else if ((session.getAttribute("usuario") != null) & session.getAttribute("usuario").equals("cocina")) {
			
			List<ItemComandaDTO> items = new ArrayList<ItemComandaDTO>();
				//FALTA LA CAPTURA DE EXCEPCION!
				items = BusinessDelegate.getInstance().getItemsPendientesxArea(AreaRest.Cocina);
				request.setAttribute("items", items);
				RequestDispatcher rd = request.getRequestDispatcher("/verMisItems.jsp");
				rd.forward(request, response);
			
				
		}
		}
		
		if(opcion.equals("verDatosParaCargarComanda")){
			
				List<MesaDTO> mesas = new ArrayList<MesaDTO>();
			
			
			try {
				mesas = BusinessDelegate.getInstance().mostrarMesas();
				request.setAttribute("mesas", mesas);
			} catch (MesaException e) {
				System.out.println(e.getMessage());
			}
			
			request.setAttribute("datosParaComanda", "listos");
			RequestDispatcher rd = request.getRequestDispatcher("/cargarComanda.jsp");
			rd.forward(request, response);

		}
		
		if(opcion.equals("agregarItemsComanda")){
			
			Integer id_mesa = Integer.parseInt(request.getParameter("mesa"));
			
			MesaDTO mesa = new MesaDTO();
			
			try {
				mesa = BusinessDelegate.getInstance().BuscarMesaPorCod(id_mesa);
			} catch (MesaException e) {
				System.out.println(e.getMessage());
			}
			
			ComandaDTO comanda = new ComandaDTO(mesa.getMozo(),mesa,Estado.EnProceso);
			List<ComandaDTO> comandas = new ArrayList<ComandaDTO>();
			
			try {
			  BusinessDelegate.getInstance().grabarComanda(comanda);
			  
			 
				try {
					comandas = BusinessDelegate.getInstance().mostrarComandas();
					request.setAttribute("comandas", comandas);
					
				} catch (ComandaException e) {
					System.out.println(e.getMessage());
				}
			  
			
			} catch (ComandaException e) {
				System.out.println(e.getMessage());
			}
			
			request.setAttribute("comanda", comandas.get(comandas.size()-1));
			
			List<PlatoDTO> platos = new ArrayList<PlatoDTO>();
			try {
				platos = BusinessDelegate.getInstance().listarPlatos();
				
			} catch (PlatoException e) {
				System.out.println(e.getMessage());
			}
			
			request.setAttribute("platos", platos);
			request.setAttribute("vamosACargarItems", "listos");
			RequestDispatcher rd = request.getRequestDispatcher("/agregarItemAComanda.jsp");
			rd.forward(request, response);
	}
		
if(opcion.equals("agregarItemsComanda_2step")){
			
	Integer codComanda = Integer.parseInt(request.getParameter("codComanda"));
	Integer codPlato = Integer.parseInt(request.getParameter("codPlato"));
		Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
			
		String accion = request.getParameter("boton");
			
		ComandaDTO comanda = new ComandaDTO();
			PlatoDTO plato = new PlatoDTO();
			
			
			try {
				comanda = BusinessDelegate.getInstance().BuscarComandasPorCod(codComanda);
			} catch (ComandaException e) {
				System.out.println(e.getMessage());
			}
			
			try {
				plato = BusinessDelegate.getInstance().BuscarPlatoPorCod(codPlato);
				
			} catch (PlatoException e) {
				System.out.println(e.getMessage());
			}
			
			ItemComandaDTO itemComanda = new ItemComandaDTO(cantidad,plato,EstadoItemComanda.Pendiente, comanda);
			itemComanda.setComanda(comanda);
			try {
			BusinessDelegate.getInstance().grabarItemComanda(itemComanda);
			} catch (itemComandaException e) {
				System.out.println(e.getMessage());
			}
			
			if (accion.equals("Aceptar")) {
			RequestDispatcher rd = request.getRequestDispatcher("/verComandas.jsp");
			rd.forward(request, response);}
			if (accion.equals("Otro")) {
				
				List<PlatoDTO> platos = new ArrayList<PlatoDTO>();
				try {
					platos = BusinessDelegate.getInstance().listarPlatos();
					
				} catch (PlatoException e) {
					System.out.println(e.getMessage());
				}
				
				request.setAttribute("platos", platos);
				request.setAttribute("comanda", comanda);
				request.setAttribute("vamosACargarItems", "listos");
				RequestDispatcher rd = request.getRequestDispatcher("/agregarItemAComanda.jsp");
				rd.forward(request, response);}
				
			
	}
		
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
