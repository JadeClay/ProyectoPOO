package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.sun.security.ntlm.Client;

import logico.Cliente;
import logico.Database;
import logico.Empresa;
import logico.Proyecto;
import sun.util.locale.provider.AuxLocaleProviderAdapter;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistrarCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField txtIdentificacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarCliente dialog = new RegistrarCliente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarCliente(Cliente aux) {
		setTitle("Registrar cliente");
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 451, 289);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		setLocationRelativeTo(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 11, 404, 172);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblId = new JLabel("Id:");
			lblId.setBounds(10, 27, 46, 14);
			panel_1.add(lblId);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(10, 87, 71, 14);
			panel_1.add(lblNombre);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(10, 115, 71, 14);
			panel_1.add(lblDireccion);
			
			txtId = new JTextField();
			txtId.setText("C-"+Empresa.idClientes);
			txtId.setEditable(false);
			txtId.setBounds(73, 24, 109, 20);
			panel_1.add(txtId);
			txtId.setColumns(10);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(107, 84, 284, 20);
			panel_1.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtDireccion = new JTextField();
			txtDireccion.setBounds(107, 115, 284, 20);
			panel_1.add(txtDireccion);
			txtDireccion.setColumns(10);
			
			txtIdentificacion = new JTextField();
			txtIdentificacion.setColumns(10);
			txtIdentificacion.setBounds(107, 54, 284, 20);
			panel_1.add(txtIdentificacion);
			
			JLabel lblIdentificacion = new JLabel("Identificacion:");
			lblIdentificacion.setBounds(10, 57, 85, 14);
			panel_1.add(lblIdentificacion);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				if (aux == null) {
					okButton = new JButton("Registrar");
				} else {
					okButton = new JButton("Modificar");	
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (aux == null) {
							crearCliente();
						} else {
							aux.setDireccion(txtDireccion.getText());
							aux.setIdentificacion(txtIdentificacion.getText());
							aux.setNombre(txtNombre.getText());
							JOptionPane.showMessageDialog(null,"Modificacion Exitosa" ,"Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();
							ListadoClientes.loadClients();
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		if (aux != null) {
			txtId.setText(aux.getId());
			txtIdentificacion.setText(aux.getIdentificacion());
			txtNombre.setText(aux.getNombre());
			txtDireccion.setText(aux.getDireccion());
		}
		Image img = new ImageIcon(this.getClass().getResource("/visual/img//client.png")).getImage();
		this.setIconImage(img);
	}
	
	private void clearRegistro() {
		txtId.setText("C-"+Empresa.idClientes);
		txtIdentificacion.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
	}
	
	private void crearCliente() {
		if(!txtIdentificacion.getText().isEmpty() && !txtNombre.getText().isEmpty() && !txtDireccion.getText().isEmpty()) {
			Cliente client = new Cliente(txtId.getText(),txtIdentificacion.getText(), txtNombre.getText(), txtDireccion.getText());
			Empresa.getInstance().getMisclientes().add(client);
			Empresa.idClientes++;
			
			Database database = new Database();
			database.addClient(client.getIdentificacion(), client.getNombre(), client.getDireccion());
			clearRegistro();
			
			JOptionPane.showMessageDialog(null,"Registro Satisfactorio" ,"Información", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null,"Hubo un error al registrar el cliente" ,"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}

