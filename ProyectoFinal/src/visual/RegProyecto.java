package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Empresa;
import logico.Proyecto;
import logico.Trabajador;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class RegProyecto extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField Idtext;
    private JTextField nombretext;
    private JList list; 
    private static DefaultListModel<Trabajador> model = new DefaultListModel();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegProyecto dialog = new RegProyecto();
            dialog.loadTrabajadores();
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegProyecto() {
    	setResizable(false);
        setTitle("Registrar Proyecto");
        setModal(true);
		setBounds(100, 100, 291, 426);
		setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);
            
            JLabel lblNewLabel = new JLabel("ID:");
            lblNewLabel.setBounds(10, 25, 46, 14);
            panel.add(lblNewLabel);
            
            JLabel lblNewLabel_1 = new JLabel("Nombre:");
            lblNewLabel_1.setBounds(10, 60, 50, 14);
            panel.add(lblNewLabel_1);
            
            Idtext = new JTextField();
            Idtext.setEditable(false);
            Idtext.setText("P-"+Empresa.idProyectos);
            Idtext.setBounds(70, 22, 191, 20);
            panel.add(Idtext);
            Idtext.setColumns(10);
            {
                nombretext = new JTextField();
                nombretext.setBounds(70, 57, 191, 20);
                panel.add(nombretext);
                nombretext.setColumns(10);
            }
            {
                JLabel lblNewLabel_2 = new JLabel("Seleccionar trabajadores:");
                lblNewLabel_2.setBounds(10, 100, 160, 14);
                panel.add(lblNewLabel_2);
            }
            
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(10, 125, 251, 222);
            panel.add(scrollPane);
            
            list = new JList();
            list.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            list.setBackground(Color.WHITE);
            list.setModel(model);
            scrollPane.setViewportView(list);
            list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Registrar");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {    
                        Proyecto aux = new Proyecto(Idtext.getText(), nombretext.getText());
                        ArrayList<Trabajador> trabajadores = new ArrayList<>();
                        DefaultListModel<Trabajador> model = ((DefaultListModel<Trabajador>) list.getModel());
                        int[] selectedIndices = list.getSelectedIndices();

                        for (int index : selectedIndices) {
                            trabajadores.add(model.getElementAt(index));
                        }

                        aux.setLosTrabajadores(trabajadores);

                        Empresa.getInstance().registarProyecto(aux);
                        cleanProyecto();
                        JOptionPane.showMessageDialog(null, "Se ha registrado satisfactoriamente", "Registro", JOptionPane.INFORMATION_MESSAGE);
                      }
                    });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
        loadTrabajadores();
        cleanProyecto();
    }
    
    public void cleanProyecto() {
        Idtext.setText("P-" + Empresa.idProyectos);
        nombretext.setText("");
        list.clearSelection();
    }
    
    private void loadTrabajadores() {
    	model.removeAllElements();
        ArrayList<Trabajador> trabajadores = Empresa.getInstance().getMistabajadores();
        
        for (Trabajador trabajador : trabajadores) {
            model.addElement(trabajador);
        }
    }
}