package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Database;
import logico.Empresa;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JLabel lbllockimg;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
            public void run() {
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
		setResizable(false);
		setTitle("Log-in");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(224, 68, 66, 14);
		panel.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(224, 121, 105, 14);
		panel.add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(295, 65, 191, 20);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database database = new Database();
				Usuario aux = null;
				aux = database.logUser(txtUsuario.getText(), new String(txtPassword.getPassword()));

				if(aux != null){
					Principal frame = new Principal(aux);
					dispose();
					frame.setVisible(true);
				};
				
			}
		});
		btnLogin.setBounds(295, 175, 90, 29);
		panel.add(btnLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(295, 117, 191, 22);
		panel.add(txtPassword);
		
		lbllockimg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/visual/img//userlock.png")).getImage();
		Image newImage = img.getScaledInstance(203, 170, Image.SCALE_DEFAULT);//w y h
		lbllockimg.setIcon(new ImageIcon(newImage));
		lbllockimg.setBounds(10, 33, 204, 171);
		panel.add(lbllockimg);
		
		this.setIconImage(newImage);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(396, 175, 90, 29);
		panel.add(btnCancelar);
		
		JLabel lblNewLabel_1 = new JLabel("Iniciar sesi\u00F3n");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel_1.setBounds(295, 11, 245, 36);
		panel.add(lblNewLabel_1);
	}
	
}
