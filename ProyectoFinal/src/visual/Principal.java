package visual;

import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import logico.Usuario;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmRegistrar;
	private JMenuItem mntmListadoClientes;

	/**
	 * Create the frame.
	 */
	public Principal(Usuario aux) {
		setResizable(false);
		setTitle("Gestion de Software Empresarial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 630);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 797, 26);
		panel_1.add(menuBar);
		
		JMenu mnTrabajadores = new JMenu("Trabajadores");
		menuBar.add(mnTrabajadores);
		
		JMenuItem mntmContratar = new JMenuItem("Contratar");
		mntmContratar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarTrabajador ventana = new RegistrarTrabajador();
				ventana.setVisible(true);
			}
		});
		mnTrabajadores.add(mntmContratar);
		
		JSeparator separator = new JSeparator();
		mnTrabajadores.add(separator);
		
		JMenuItem mntmListadoTrabajadores = new JMenuItem("Listado");
		mntmListadoTrabajadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoTrabajadores ventana = new ListadoTrabajadores();
				ventana.setVisible(true);
			}
		});
		mnTrabajadores.add(mntmListadoTrabajadores);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		mntmRegistrar = new JMenuItem("Registrar");
		mntmRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCliente regCli = new RegistrarCliente(null);
				regCli.setVisible(true);
	
			}
		});
		mnClientes.add(mntmRegistrar); 
		
		JSeparator separator_1 = new JSeparator();
		mnClientes.add(separator_1);
		
		mntmListadoClientes = new JMenuItem("Listado");
		mntmListadoClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoClientes ListCli = new ListadoClientes();
				ListCli.setVisible(true);

			}
		});
		mnClientes.add(mntmListadoClientes);
		
		JMenu mnProyectos = new JMenu("Proyectos");
		menuBar.add(mnProyectos);
		
		JMenuItem mntmCrear = new JMenuItem("Registrar");
		mntmCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarContrato ventana = new RegistrarContrato();
				ventana.setVisible(true);
			}
		});
		mnProyectos.add(mntmCrear);
		
		JSeparator separator_2 = new JSeparator();
		mnProyectos.add(separator_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Listado");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoProyecto ventana = new ListadoProyecto();
				ventana.setVisible(true);
			}
		});
		mnProyectos.add(mntmNewMenuItem);
		
		JMenu mnAdministracion = new JMenu("Administraci\u00F3n");
		if(aux.getTipo() == 0) {
			mnAdministracion.setEnabled(false);
		}
		menuBar.add(mnAdministracion);
		
		JMenuItem mntmCrearUsuario = new JMenuItem("Registrar usuario");
		mntmCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearUsuario ventana = new CrearUsuario();
				ventana.setVisible(true);
			}
		});
		mnAdministracion.add(mntmCrearUsuario);
		
		JSeparator separator_3 = new JSeparator();
		mnAdministracion.add(separator_3);
		
		JMenuItem mntmListadoUsuarios = new JMenuItem("Listado");
		mntmListadoUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoUsuarios ventana = new ListadoUsuarios();
				ventana.setVisible(true);
			}
		});
		mnAdministracion.add(mntmListadoUsuarios);
		
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBounds(0, 23, 797, 562);
		panel_1.add(backgroundPanel);
		backgroundPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(588, 0, 209, 71);
		backgroundPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido,");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 13, 136, 31);
		panel.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("usuario.");
		if(aux != null) {
			lblNombre.setText(aux.getUsuario() + ".");
		}
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(12, 39, 185, 16);
		panel.add(lblNombre);
		
		JLabel lblBackground = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/visual/img/background.png")).getImage();
		Image newImage = img.getScaledInstance(797, 563, Image.SCALE_DEFAULT);//w y h
		lblBackground.setIcon(new ImageIcon(newImage));
		
		this.setIconImage(new ImageIcon(this.getClass().getResource("/visual/img/codeicon.png")).getImage());
//		URL url = getClass().getResource("/visual/img/try3.gif");
//	    Icon icon = new ImageIcon(url);
//	    lblBackground.setIcon(icon);
	    
		lblBackground.setBounds(0, 0, 797, 563);
		backgroundPanel.add(lblBackground);
	}
}
