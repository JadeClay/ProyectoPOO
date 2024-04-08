package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Empresa;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

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
						Usuario aux = new Usuario(new String("U-1"), "Admin", "Admin", 1);
						Empresa.getInstance().regUser(aux);
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
					login frame = new login();
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
	public login() {
		setTitle("Log-in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(39, 39, 66, 14);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(39, 98, 105, 14);
		panel.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(39, 64, 191, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario aux = Empresa.getInstance().confirmLogin(txtUsuario.getText(), new String(txtPassword.getPassword()));
				if(aux != null){
					Principal frame = new Principal(aux);
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
					
					dispose();
					frame.setVisible(true);
				};
				
			}
		});
		btnLogin.setBounds(37, 175, 89, 23);
		panel.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(39, 125, 191, 22);
		panel.add(txtPassword);
	}
}
