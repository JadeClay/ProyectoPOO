package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Trabajador;
import logico.Empresa;

public class ListadoTrabajadores extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JButton btnCancel;
	private JTable table;
	private JButton btnDelete;
	private static DefaultTableModel model;
	private static Object[] rows;
	private Trabajador selected = null;
	private JButton btnVer;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoTrabajadores dialog = new ListadoTrabajadores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoTrabajadores() {
		setResizable(false);
		setTitle("Listado de Trabajadores");
		setModal(true);
		setBounds(100, 100, 738, 530);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Trabajadores Registrados:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String[] headers = {"Código","Identificacion","Nombres","Apellidos","Direccion","Edad","Salario/hora","Evaluación"};
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
								btnDelete.setEnabled(true);
								btnVer.setEnabled(true);
								selected = Empresa.getInstance().buscarTrabajadorById(table.getValueAt(index, 0).toString());
								System.out.print(selected.getId());
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
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDelete = new JButton("Eliminar");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(selected != null){
							int option = JOptionPane.showConfirmDialog(null, "Seguro desea eliminar el trabajador con código: "+ selected.getId(), "Eliminar", JOptionPane.WARNING_MESSAGE);
							if(option == JOptionPane.YES_OPTION){
								Empresa.getInstance().eliminarTrabajador(selected);
								loadClientes();
							}
						}
					}
				});
				btnDelete.setEnabled(false);
				buttonPane.add(btnDelete);
			}
			{
				btnCancel = new JButton("Cancelar");
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}	
				});
				{
					btnVer = new JButton("Ver perfil");
					btnVer.setEnabled(false);
					btnVer.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(selected != null) {
								PerfilTrabajador ventana = new PerfilTrabajador(selected);
								ventana.setVisible(true);
							}
						}
					});
					buttonPane.add(btnVer);
				}
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		
		loadClientes();
	}

	public static void loadClientes() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		int cant = Empresa.getInstance().getMistabajadores().size();
		for (int i = 0; i < cant; i++) {
			rows[0] = Empresa.getInstance().getMistabajadores().get(i).getId();
			rows[1] = Empresa.getInstance().getMistabajadores().get(i).getIdentificacion();
			rows[2] = Empresa.getInstance().getMistabajadores().get(i).getNombre();
			rows[3] = Empresa.getInstance().getMistabajadores().get(i).getApellidos();
			rows[4] = Empresa.getInstance().getMistabajadores().get(i).getDireccion();
			rows[5] = Empresa.getInstance().getMistabajadores().get(i).getEdad();
			rows[6] = new String("RD$ " + Empresa.getInstance().getMistabajadores().get(i).getSalario());
			rows[7] = Empresa.getInstance().getMistabajadores().get(i).getEvaluacionActual();
			model.addRow(rows);
		}
		
	}



}
