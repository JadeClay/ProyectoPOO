package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Empresa;
import logico.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

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
		setBounds(100, 100, 344, 266);
		setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblUsuario = new JLabel("Usuario:");
			lblUsuario.setBounds(111, 13, 56, 16);
			contentPanel.add(lblUsuario);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setBounds(111, 73, 71, 16);
			contentPanel.add(lblPassword);
		}
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(111, 30, 116, 22);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(111, 90, 116, 22);
		contentPanel.add(txtPassword);
		
		JRadioButton rdbtnBasico = new JRadioButton("Basico");
		rdbtnBasico.setSelected(true);
		rdbtnBasico.setBounds(81, 138, 71, 25);
		contentPanel.add(rdbtnBasico);
		
		JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnBasico.setSelected(false);
				rdbtnAdministrador.setSelected(true);
			}
		});
		rdbtnAdministrador.setBounds(156, 138, 116, 25);
		contentPanel.add(rdbtnAdministrador);
		
		rdbtnBasico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAdministrador.setSelected(false);
				rdbtnBasico.setSelected(true);
			}
		});
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
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
							JOptionPane.showMessageDialog(null, "El usuario ha sido creado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "Ya hay un usuario con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
						};
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
