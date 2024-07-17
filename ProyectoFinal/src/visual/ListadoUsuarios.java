package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.Cliente;
import logico.Database;
import logico.Empresa;
import logico.Usuario;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import java.awt.Color;

public class ListadoUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSalir;
	private JButton btnEliminar;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private Usuario usuario = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListadoUsuarios dialog = new ListadoUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoUsuarios() {
		setResizable(false);
		setModal(true);
		setTitle("Listado De Usuarios");
		setBounds(100, 100, 580, 366);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Usuarios:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					String[] headers = {"Id","Usuario","Tipo"};

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

								if (usuario == null) {
									
									usuario = Empresa.getInstance().buscarUsuarioById((table.getValueAt(index, 0).toString()));
									
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
						Empresa.getInstance().eliminarUsuario(usuario);
						Database database = new Database();
						database.deleteUser(new Integer(usuario.getId().substring(2)));
						
						loadUsuarios();
					}
				});
				buttonPane.add(btnEliminar);
			}
			{
				btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		loadUsuarios();
		Image img = new ImageIcon(this.getClass().getResource("/visual/img//users.png")).getImage();
		this.setIconImage(img);
	}

	public static void loadUsuarios() { 
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		
		for (Usuario c : Empresa.getInstance().getLosusuarios()) {
			rows[0] = c.getId();
			rows[1] = c.getUsuario();
			rows[2] = getTipoUsuario(c.getTipo());
			model.addRow(rows);
		}
	}
	
	public static String getTipoUsuario(int tipo) {
		if(tipo == 1) {
			return "Administrador";
		}
		
		return "Básico";
	}
}
