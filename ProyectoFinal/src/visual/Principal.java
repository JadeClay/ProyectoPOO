package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

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
		setBounds(100, 100, 742, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 726, 26);
		panel_1.add(menuBar);
		
		JMenu mnTrabajadores = new JMenu("Trabajadores");
		menuBar.add(mnTrabajadores);
		
		JMenuItem mntmContratar = new JMenuItem("Contratar");
		mnTrabajadores.add(mntmContratar);
		
		JSeparator separator = new JSeparator();
		mnTrabajadores.add(separator);
		
		JMenuItem mntmListadoTrabajadores = new JMenuItem("Listado");
		mnTrabajadores.add(mntmListadoTrabajadores);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmRegistrar = new JMenuItem("Registrar");
		mnClientes.add(mntmRegistrar);
		
		JSeparator separator_1 = new JSeparator();
		mnClientes.add(separator_1);
		
		JMenuItem mntmListadoClientes = new JMenuItem("Listado");
		mnClientes.add(mntmListadoClientes);
		
		JMenu mnProyectos = new JMenu("Proyectos");
		menuBar.add(mnProyectos);
		
		JMenuItem mntmCrear = new JMenuItem("Crear");
		mnProyectos.add(mntmCrear);
		
		JSeparator separator_2 = new JSeparator();
		mnProyectos.add(separator_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Listado");
		mnProyectos.add(mntmNewMenuItem);
		
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setBounds(0, 23, 726, 446);
		panel_1.add(backgroundPanel);
		backgroundPanel.setLayout(null);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(Principal.class.getResource("/visual/img/background.jpg")));
		lblBackground.setBounds(0, 0, 726, 446);
		backgroundPanel.add(lblBackground);
	}
}
