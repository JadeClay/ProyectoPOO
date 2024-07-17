package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Database;
import logico.Empresa;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.Font;

public class CrearUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearUsuario dialog = new CrearUsuario();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearUsuario() {
		setTitle("Crear usuario");
		setResizable(false);
		setBounds(100, 100, 539, 282);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setBounds(235, 67, 56, 16);
			contentPanel.add(lblUsuario);
		}
		{
			JLabel lblPassword = new JLabel("Contrase\u00F1a:");
			lblPassword.setBounds(235, 127, 71, 16);
			contentPanel.add(lblPassword);
		}
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(316, 64, 161, 22);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(316, 124, 161, 22);
		contentPanel.add(txtPassword);
		
		JRadioButton rdbtnBasico = new JRadioButton("Basico");
		rdbtnBasico.setSelected(true);
		rdbtnBasico.setBounds(314, 157, 71, 25);
		contentPanel.add(rdbtnBasico);
		
		JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnBasico.setSelected(false);
				rdbtnAdministrador.setSelected(true);
			}
		});
		rdbtnAdministrador.setBounds(387, 157, 116, 25);
		contentPanel.add(rdbtnAdministrador);
		{
			JLabel lblRegistrar = new JLabel("Registrar");
			lblRegistrar.setFont(new Font("SansSerif", Font.BOLD, 30));
			lblRegistrar.setBounds(331, 17, 245, 36);
			contentPanel.add(lblRegistrar);
		}
		{
			JLabel lbllockimg = new JLabel("");
			Image img = new ImageIcon(this.getClass().getResource("/visual/img//user.png")).getImage();
			Image newImage = img.getScaledInstance(203, 170, Image.SCALE_DEFAULT);//w y h
			lbllockimg.setIcon(new ImageIcon(newImage));
			
			this.setIconImage(img);
			
			lbllockimg.setBounds(10, 27, 204, 171);
			contentPanel.add(lbllockimg);
		}
		{
			JButton okButton = new JButton("Registrar");
			okButton.setBounds(281, 189, 104, 36);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Usuario aux = Empresa.getInstance().confirmLogin(txtUsuario.getText(), new String(txtPassword.getPassword()));
					int tipo = 0;
					if(aux == null){
						if(rdbtnAdministrador.isSelected()) {
							tipo = 1;
						}
						Usuario nuevoUsuario = new Usuario(new String("U-" + Empresa.getInstance().idUsuarios), txtUsuario.getText(), new String(txtPassword.getPassword()), tipo);
						Empresa.getInstance().regUser(nuevoUsuario);
						Database database = new Database();
						database.registerUser(nuevoUsuario.getUsuario(), nuevoUsuario.getPassword(), nuevoUsuario.getTipo());
						
						JOptionPane.showMessageDialog(null, "El usuario ha sido creado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Ya hay un usuario con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
					};
					
				}
			});
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancelar.setActionCommand("OK");
			btnCancelar.setBounds(399, 189, 104, 36);
			contentPanel.add(btnCancelar);
		}
		
		rdbtnBasico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAdministrador.setSelected(false);
				rdbtnBasico.setSelected(true);
			}
		});
		
	}
}
