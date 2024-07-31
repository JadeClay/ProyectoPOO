package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import logico.Contrato;
import logico.Database;
import logico.Empresa;
import logico.Proyecto;
import logico.Trabajador;

public class ListadoProyecto extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JButton btnCancel;
    private JTable table;
    private JButton btnDelete;
    private JButton btnFinalizarProrrogar;
    private static DefaultTableModel model;
    private static Object[] rows;
    private Contrato selected = null;
    private JButton btnVer;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ListadoProyecto dialog = new ListadoProyecto();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ListadoProyecto() {
        setResizable(false);
        setTitle("Listado de Proyectos");
        setModal(true);
        setBounds(100, 100, 738, 530);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null, "Proyectos Registrados:", TitledBorder.LEADING,
                    TitledBorder.TOP, null, null));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(new BorderLayout(0, 0));
            {
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                panel.add(scrollPane, BorderLayout.CENTER);
                {
                    String[] headers = { "ID", "Nombre", "Trabajadores", "Estado" }; // Agregamos el estado del proyecto
                    model = new DefaultTableModel() {
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            // all cells false
                            return false;
                        }
                    };
                    model.setColumnIdentifiers(headers);
                    table = new JTable();
                    table.setModel(model);
                    scrollPane.setViewportView(table);
                    table.getSelectionModel().addListSelectionListener(event -> {
                        if (!event.getValueIsAdjusting()) {
                            int selectedRow = table.getSelectedRow();
                            if (selectedRow != -1) {
                            	Database db = new Database();
                                String projectId = (String) model.getValueAt(selectedRow, 0);
                                selected = db.searchContractById(new Integer(projectId.substring(2)));
                                if (selected != null) {
                                    btnDelete.setEnabled(true);
                                    btnFinalizarProrrogar.setEnabled(true);
                                    btnVer.setEnabled(true);
                                } else {
                                    btnDelete.setEnabled(false);
                                    btnFinalizarProrrogar.setEnabled(false);
                                }
                            }
                        }
                    });
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btnFinalizarProrrogar = new JButton("Finalizar/Prorrogar");
                btnFinalizarProrrogar.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                	    if (selected != null) {
                	        String[] options = {"Finalizar", "Prorrogar"};
                	        int choice = JOptionPane.showOptionDialog(null, "¿Qué desea hacer con el proyecto?",
                	                "Finalizar/Prorrogar Proyecto", JOptionPane.DEFAULT_OPTION,
                	                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                	        if (choice == 0) { // Finalizar
                	        	Database db = new Database();
                	            db.finalizeProjecct(selected);
                	            loadProyectos();
                	        } else if (choice == 1) { // Prorrogar
                	            String hours = JOptionPane.showInputDialog(null, "Ingrese la cantidad de horas a prorrogar:");
                	            if (hours != null && !hours.isEmpty()) {
                	                int hoursToExtend = Integer.parseInt(hours);
                	                Database db = new Database();
                	                System.out.println(selected.getId());
                	                Contrato contrato = db.searchContractById(new Integer(selected.getId().substring(2)));
                	                db.prorrogarProyecto(selected, hoursToExtend);
                	                loadProyectos();
                	            }
                	        }
                	    }
                	}
                });
                btnFinalizarProrrogar.setEnabled(false);
                buttonPane.add(btnFinalizarProrrogar);
            }
            {
                btnDelete = new JButton("Eliminar");
                btnDelete.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (selected != null) {
                            int option = JOptionPane.showConfirmDialog(null,
                                    "Seguro desea eliminar el proyecto con ID: " + selected.getId(), "Eliminar",
                                    JOptionPane.WARNING_MESSAGE);
                            if (option == JOptionPane.YES_OPTION) {
                            	Database database = new Database();
                	            database.deleteProject(selected.getId());
                                loadProyectos();
                            }
                        }
                    }
                });
                {
                	btnVer = new JButton("Ver Detalles");
                	btnVer.addActionListener(new ActionListener() {
                		public void actionPerformed(ActionEvent e) {
                			PerfilContrato perfilcontrato = new PerfilContrato(selected);
                			perfilcontrato.setVisible(true);
                		}
                	});
                	btnVer.setEnabled(false);
                	buttonPane.add(btnVer);
                }
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
                btnCancel.setActionCommand("Cancel");
                buttonPane.add(btnCancel);
            }
        }

        loadProyectos();
        Image img = new ImageIcon(this.getClass().getResource("/visual/img//teamwork.png")).getImage();
		this.setIconImage(img);
    }

    public static void loadProyectos() {
    	Database db = new Database();
    	
        model.setRowCount(0);
        rows = new Object[model.getColumnCount()];
        
		ArrayList[] datos = db.getAllProjects();
		ArrayList<Proyecto> proyectos = datos[1];
		
        for (Proyecto p : proyectos) {
            rows[0] = p.getId();
            rows[1] = p.getNombre();
            rows[2] = p.getLosTrabajadores().size();
            rows[3] = p.getEstado() ? "Activo" : "Inactivo"; 
            model.addRow(rows);
        }

    }

}
