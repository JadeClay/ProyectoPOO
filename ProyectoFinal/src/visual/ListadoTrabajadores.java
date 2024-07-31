package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
import logico.Database;
import logico.Disenador;
import logico.Empresa;
import logico.JefeProyecto;
import logico.Planificador;
import logico.Programador;

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
								Database db = new Database();
								btnDelete.setEnabled(true);
								btnVer.setEnabled(true);
								selected = db.searchWorkerById(new Integer(table.getValueAt(index, 0).toString().substring(2)));

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
								Database database = new Database();
								
								if(selected instanceof JefeProyecto) {
									database.deleteJefeProyecto(new Integer(selected.getId().substring(2)));
								} else if (selected instanceof Programador) {
									database.deleteProgramador(new Integer(selected.getId().substring(2)));
								} else if(selected instanceof Planificador) {
									database.deletePlanificador(new Integer(selected.getId().substring(2)));
								} else {
									database.deleteWorker(new Integer(selected.getId().substring(2)));
								}
								
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
		Image img = new ImageIcon(this.getClass().getResource("/visual/img//trabajadores.png")).getImage();
		this.setIconImage(img);
	}

	public static void loadClientes() {
		Database db = new Database();
		
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		
		ArrayList<Trabajador> trabajadores = db.getAllWorkers();
		
		for (Trabajador t : trabajadores) {
			rows[0] = t.getId();
			rows[1] = t.getIdentificacion();
			rows[2] = t.getNombre();
			rows[3] = t.getApellidos();
			rows[4] = t.getDireccion();
			rows[5] = t.getEdad();
			rows[6] = new String("RD$ " + t.getSalario());
			rows[7] = t.getEvaluacionActual();
			model.addRow(rows);
		}
		
	}



}
