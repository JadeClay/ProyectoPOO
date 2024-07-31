package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logico.Cliente;
import logico.Proyecto;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PerfilCliente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNom;
	private JTextField txtDirecc;
	private JTextField txtIdentif;
	private JList listProyectos = new JList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PerfilCliente dialog = new PerfilCliente(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PerfilCliente(Cliente client) {
		setTitle("Perfil De "+client.getNombre());
		setResizable(false);
		setModal(true);
		
		setBounds(100, 100, 440, 402);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Datos:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 11, 404, 172);
			panel.add(panel_1);
			
			JLabel label = new JLabel("Id:");
			label.setBounds(10, 27, 46, 14);
			panel_1.add(label);
			
			JLabel label_1 = new JLabel("Nombre:");
			label_1.setBounds(10, 87, 71, 14);
			panel_1.add(label_1);
			
			JLabel label_2 = new JLabel("Direccion:");
			label_2.setBounds(10, 115, 71, 14);
			panel_1.add(label_2);
			
			txtId = new JTextField();
			txtId.setText("C-0");
			txtId.setEditable(false);
			txtId.setColumns(10);
			txtId.setBounds(73, 24, 109, 20);
			panel_1.add(txtId);
			
			txtNom = new JTextField();
			txtNom.setEditable(false);
			txtNom.setColumns(10);
			txtNom.setBounds(107, 84, 284, 20);
			panel_1.add(txtNom);
			
			txtDirecc = new JTextField();
			txtDirecc.setEditable(false);
			txtDirecc.setColumns(10);
			txtDirecc.setBounds(107, 115, 284, 20);
			panel_1.add(txtDirecc);
			
			txtIdentif = new JTextField();
			txtIdentif.setEditable(false);
			txtIdentif.setColumns(10);
			txtIdentif.setBounds(107, 54, 284, 20);
			panel_1.add(txtIdentif);
			
			JLabel label_3 = new JLabel("Identificacion:");
			label_3.setBounds(10, 57, 85, 14);
			panel_1.add(label_3);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Proyectos:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(10, 194, 404, 125);
			panel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_2.add(scrollPane, BorderLayout.CENTER);
			
			scrollPane.setViewportView(listProyectos);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}

		loadEverything(client);
		Image img = new ImageIcon(this.getClass().getResource("/visual/img//client.png")).getImage();
		this.setIconImage(img);
	}
	private void loadEverything(Cliente client) {
		DefaultListModel<String> dataProyectos = new DefaultListModel<String>();
		
		for (Proyecto p : client.getLosProyectos()) {
			System.out.println(p.getNombre());
			String nombre = new String(p.getId()+" - "+p.getNombre());
			dataProyectos.addElement(nombre);
		}
		
		listProyectos.setModel(dataProyectos);
		
		txtId.setText(client.getId());
		txtNom.setText(client.getNombre());
		txtDirecc.setText(client.getDireccion());
		txtIdentif.setText(client.getIdentificacion());	
		
	}
}
