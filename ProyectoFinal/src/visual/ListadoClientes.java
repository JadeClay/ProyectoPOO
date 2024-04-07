package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Empresa;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.Color;

public class ListadoClientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSalir;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private Cliente client = null;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoClientes dialog = new ListadoClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoClientes() {
		setResizable(false);
		setModal(true);
		setTitle("Listado De Clientes");
		setBounds(100, 100, 580, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Clientes:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String[] headers = {"Id","Nombre","No.Identificacion","Direccion", "Proyectos Activos"};

					model = new DefaultTableModel(){

					    @Override
					    public boolean isCellEditable(int row, int column) {
					       //all cells false
					       return false;
					    }
					};
					model.setColumnIdentifiers(headers);
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) { 
							
							int index = table.getSelectedRow();
							
							if(index > -1){
								btnEliminar.setEnabled(true);
								btnModificar.setEnabled(true);

								if (client == null) {
									
									client = Empresa.getInstance().buscarClienteById((table.getValueAt(index, 0).toString()));
									
								}
								
							}
						}
					});
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Empresa.getInstance().eliminarCliente(client);
						loadClients();
					}
				});
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RegistrarCliente modifCli = new RegistrarCliente(client);
						modifCli.setVisible(true);
						
					}
				});
				buttonPane.add(btnModificar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnNewButton = new JButton("Ver Perfil");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							PerfilCliente perCli = new PerfilCliente(client);
							perCli.setVisible(true);
						}
					});
					buttonPane.add(btnNewButton);
				}
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		loadClients();
	}

	public static void loadClients() { 
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		
		for (Cliente c : Empresa.getInstance().getMisclientes()) {
			rows[0] = c.getId();
			rows[1] = c.getNombre();
			rows[2] = c.getIdentificacion();
			rows[3] = c.getDireccion();
			rows[4] = c.getCantProyectosActivos();
			model.addRow(rows);
		}
	}
}
