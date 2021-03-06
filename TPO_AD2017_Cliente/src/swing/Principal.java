package swing;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;



public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Sistema Restaurante");
		setAlwaysOnTop(true);
		setBackground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 862, 747);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 10, 2000, 26);
		
		
		
		JMenu mnNewMenu = new JMenu("Materiales");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAlta = new JMenuItem("Nuevo Material");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaMaterial altaM=new AltaMaterial();
				dispose();
				altaM.setExtendedState(JFrame.MAXIMIZED_BOTH);
				altaM.setVisible(true);
			
			}
		});
		mntmAlta.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		contentPane.setLayout(null);
		mnNewMenu.add(mntmAlta);
		
		JMenuItem mntmModificarDeposito = new JMenuItem("Modificar Material");
		mntmModificarDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ModificarMaterial bj=new ModificarMaterial();
				setVisible(false);
				bj.setExtendedState(JFrame.MAXIMIZED_BOTH);
				bj.setVisible(true);
			}
		});
		mntmModificarDeposito.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnNewMenu.add(mntmModificarDeposito);
		
		JMenuItem mntmEliminarFactura = new JMenuItem("Eliminar Material");
		mntmEliminarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaMaterial mm=new BajaMaterial();
				setVisible(false);
				mm.setExtendedState(JFrame.MAXIMIZED_BOTH);
				mm.setVisible(true);
			}
		});
		mntmEliminarFactura.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnNewMenu.add(mntmEliminarFactura);
		
		JMenu mnProductos = new JMenu("Productos");
		mnProductos.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		menuBar.add(mnProductos);
		
		JMenu mnElaborados = new JMenu("Elaborados");
		mnElaborados.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnProductos.add(mnElaborados);
		
		JMenuItem mntmNuevoPlatoElaborado = new JMenuItem("Nuevo Plato Elaborado");
		mntmNuevoPlatoElaborado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaElaborado altaM=new AltaElaborado();
				dispose();
				altaM.setExtendedState(JFrame.MAXIMIZED_BOTH);
				altaM.setVisible(true);
			}
		});
		mntmNuevoPlatoElaborado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnElaborados.add(mntmNuevoPlatoElaborado);
		
		JMenuItem mntmModificarPlatoElaborado = new JMenuItem("Modificar Plato Elaborado");
		mntmModificarPlatoElaborado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarElaborado altaM=new ModificarElaborado();
				dispose();
				altaM.setExtendedState(JFrame.MAXIMIZED_BOTH);
				altaM.setVisible(true);
			}
		});
		mntmModificarPlatoElaborado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnElaborados.add(mntmModificarPlatoElaborado);
		
		JMenuItem mntmEliminarPlatoElaborado = new JMenuItem("Eliminar Plato Elaborado");
		mntmEliminarPlatoElaborado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaElaborado altaM=new BajaElaborado();
				dispose();
				altaM.setExtendedState(JFrame.MAXIMIZED_BOTH);
				altaM.setVisible(true);
			}
		});
		mntmEliminarPlatoElaborado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnElaborados.add(mntmEliminarPlatoElaborado);
		
		JMenu mnSemielaborados = new JMenu("SemiElaborados");
		mnSemielaborados.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnProductos.add(mnSemielaborados);
		
		JMenuItem mntmNuevoPlatoSemielaborado = new JMenuItem("Nuevo Plato SemiElaborado");
		mntmNuevoPlatoSemielaborado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaSemiElaborado altaM=new AltaSemiElaborado();
				dispose();
				altaM.setExtendedState(JFrame.MAXIMIZED_BOTH);
				altaM.setVisible(true);
			}
		});
		mntmNuevoPlatoSemielaborado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSemielaborados.add(mntmNuevoPlatoSemielaborado);
		
		JMenuItem mntmModificarPlatoSemielaborado = new JMenuItem("Modificar Plato SemiElaborado");
		mntmModificarPlatoSemielaborado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarSemiElaborado altaM=new ModificarSemiElaborado();
				dispose();
				altaM.setExtendedState(JFrame.MAXIMIZED_BOTH);
				altaM.setVisible(true);
			}
		});
		mntmModificarPlatoSemielaborado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSemielaborados.add(mntmModificarPlatoSemielaborado);
		
		JMenuItem mntmEliminarPlatoSemielaborado = new JMenuItem("Eliminar Plato SemiElaborado");
		mntmEliminarPlatoSemielaborado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaSemiElaborado altaM=new BajaSemiElaborado();
				dispose();
				altaM.setExtendedState(JFrame.MAXIMIZED_BOTH);
				altaM.setVisible(true);
			}
		});
		mntmEliminarPlatoSemielaborado.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSemielaborados.add(mntmEliminarPlatoSemielaborado);
		contentPane.add(menuBar);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmNuevoUsuario = new JMenuItem("Nuevo Usuario");
		mntmNuevoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmNuevoUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaUsuario au=new AltaUsuario();
				dispose();
				au.setExtendedState(JFrame.MAXIMIZED_BOTH);
				au.setVisible(true);
			}
		});
		mnUsuarios.add(mntmNuevoUsuario);
		
		JMenuItem mntmEliminarUsuario = new JMenuItem("Eliminar Usuario");
		mntmEliminarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaUsuario el=new BajaUsuario();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		
		JMenuItem mntmModificarUsuario = new JMenuItem("Modificar Usuario");
		mntmModificarUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mntmModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarUsuario el=new ModificarUsuario();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
				
			
		});
		mnUsuarios.add(mntmModificarUsuario);
		
		mnUsuarios.add(mntmEliminarUsuario);
		
		JMenu mnMesas = new JMenu("Mesas");
		mnMesas.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		menuBar.add(mnMesas);
		
		JMenuItem mntmNuevaMesa = new JMenuItem("Nueva Mesa");
		mntmNuevaMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaMesa el=new AltaMesa();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmNuevaMesa.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnMesas.add(mntmNuevaMesa);
		
		JMenuItem mntmModificarMesa = new JMenuItem("Modificar Mesa");
		mntmModificarMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarMesa el=new ModificarMesa();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmModificarMesa.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnMesas.add(mntmModificarMesa);
		
		JMenuItem mntmEliminarMesa = new JMenuItem("Eliminar Mesa");
		mntmEliminarMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaMesa el=new BajaMesa();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmEliminarMesa.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnMesas.add(mntmEliminarMesa);
		
		JMenu mnSectores = new JMenu("Sectores");
		mnSectores.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		menuBar.add(mnSectores);
		
		JMenuItem mntmAltaSector = new JMenuItem("Alta Sector");
		mntmAltaSector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AltaSector el=new AltaSector();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmAltaSector.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSectores.add(mntmAltaSector);
		
		JMenuItem mntmModificarSector = new JMenuItem("Modificar Sector");
		mntmModificarSector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarSector el=new ModificarSector();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmModificarSector.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSectores.add(mntmModificarSector);
		
		JMenuItem mntmEliminarSector = new JMenuItem("Eliminar Sector");
		mntmEliminarSector.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaSector el=new BajaSector();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmEliminarSector.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnSectores.add(mntmEliminarSector);
		
		JMenu mnMozos = new JMenu("Mozos");
		mnMozos.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		menuBar.add(mnMozos);
		
		JMenuItem mntmAltaMozo = new JMenuItem("Alta Mozo");
		mntmAltaMozo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaMozo el=new AltaMozo();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmAltaMozo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnMozos.add(mntmAltaMozo);
		
		JMenuItem mntmModificarMozo = new JMenuItem("Modificar Mozo");
		mntmModificarMozo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarMozo el=new ModificarMozo();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmModificarMozo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnMozos.add(mntmModificarMozo);
		
		JMenuItem mntmEliminarMozo = new JMenuItem("Eliminar Mozo");
		mntmEliminarMozo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaMozo el=new BajaMozo();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmEliminarMozo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnMozos.add(mntmEliminarMozo);
		
		JMenu mnCarta = new JMenu("Cartas");
		mnCarta.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		menuBar.add(mnCarta);
		
		JMenuItem mntmNuevaCarta = new JMenuItem("Nueva Carta");
		mntmNuevaCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaCarta el=new AltaCarta();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
		});
		mntmNuevaCarta.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnCarta.add(mntmNuevaCarta);
		
		JMenuItem mntmEliminarCarta = new JMenuItem("Eliminar Carta");
		mntmEliminarCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BajaCarta el=new BajaCarta();
				dispose();
				el.setExtendedState(JFrame.MAXIMIZED_BOTH);
				el.setVisible(true);
			}
			
		});
		mntmEliminarCarta.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		mnCarta.add(mntmEliminarCarta);
		
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		JLabel lblIcono = new JLabel("");
		lblIcono.setIcon(new ImageIcon(Principal.class.getResource("/swing/logo.jpg")));
		lblIcono.setBounds((int) ((int) width/2.65), (int) ((int) height/2.9), 418, 278);
		//lblIcono.setBounds((int) width-400, (int) ((int) height-((int)height*0.40)), 418, 278);
		contentPane.add(lblIcono);
		
	}
}
