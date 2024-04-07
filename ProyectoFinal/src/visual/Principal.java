package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Empresa;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmRegistrar;
	private JMenuItem mntmListadoClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream empresa;
				FileOutputStream empresa2;
				ObjectInputStream empresaRead;
				ObjectOutputStream empresaWrite;
				try {
					empresa = new FileInputStream ("empresa.dat");
					empresaRead = new ObjectInputStream(empresa);
					Empresa temp = (Empresa)empresaRead.readObject();
					Empresa.setEmpresa(temp);
					empresa.close();
					empresaRead.close();
				} catch (FileNotFoundException e) {
					try {
						empresa2 = new  FileOutputStream("empresa.dat");
						empresaWrite = new ObjectOutputStream(empresa2);
						//User aux = new User("Administrador", "Admin", "Admin");
						//Empresa.getInstance().regUser(aux);
						empresaWrite.writeObject(Empresa.getInstance());
						empresa2.close();
						empresaWrite.close();
					} catch (FileNotFoundException e1) {
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
					
				} 
				
				try {
					Principal frame = new Principal();
					frame.addWindowListener(new java.awt.event.WindowAdapter() {
					    @Override
					    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
							FileOutputStream empresa2;
							ObjectOutputStream empresaWrite;
							try {
								empresa2 = new  FileOutputStream("empresa.dat");
								empresaWrite = new ObjectOutputStream(empresa2);
								empresaWrite.writeObject(Empresa.getInstance());
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					    }
					});
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
				RegistrarCliente regCli = new RegistrarCliente();
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
		
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBounds(0, 23, 797, 562);
		panel_1.add(backgroundPanel);
		backgroundPanel.setLayout(null);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(Principal.class.getResource("/visual/img/background.jpg")));
		lblBackground.setBounds(0, 0, 797, 563);
		backgroundPanel.add(lblBackground);
	}
}
