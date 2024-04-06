package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import com.sun.xml.internal.ws.api.Component;

import logico.Cliente;
import logico.Contrato;
import logico.Disenador;
import logico.Empresa;
import logico.JefeProyecto;
import logico.Planificador;
import logico.Programador;
import logico.Proyecto;
import logico.Trabajador;

import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RegistrarContrato extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JButton okButton;
    private JTextField txtIdCliente;
    private JTextField txtIdProyecto;
    private JTextField txtNomPro;
    private JButton cancelButton;
    private Cliente client = null;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtIdentificacion;
    private JTextField textField;
    private JButton btnBuscar;
    private JList<Programador> listProgramadoresDisp;
    private JList<Programador> listProgramadoresAsignados;
    private boolean existeCliente;
    private static DefaultListModel<Trabajador> model = new DefaultListModel();
    private DefaultListModel<Programador> modelAsignados = new DefaultListModel<>();
    private DefaultListModel<Programador> modelDisp = new DefaultListModel<>();



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegistrarContrato dialog = new RegistrarContrato();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegistrarContrato() {
        setTitle("Registro de Proyecto");
        setResizable(false);
        setModal(true);
        setBounds(100, 100, 607, 666);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BorderLayout(0, 0));
        {
            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            contentPanel.add(panel, BorderLayout.CENTER);
            panel.setLayout(null);
            {
                JPanel panel_CLient = new JPanel();
                panel_CLient.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                panel_CLient.setBounds(10, 13, 564, 155);
                panel.add(panel_CLient);
                panel_CLient.setLayout(null);
                
                JLabel label = new JLabel("Id:");
                label.setBounds(12, 22, 23, 14);
                panel_CLient.add(label);
                
                txtIdCliente = new JTextField();
                txtIdCliente.setEditable(false);

                txtIdCliente.setText("C-");
                txtIdCliente.setColumns(10);
                txtIdCliente.setBounds(47, 19, 109, 20);
                panel_CLient.add(txtIdCliente);
                
                txtNombre = new JTextField();
                txtNombre.setEnabled(false);
                txtNombre.setColumns(10);
                txtNombre.setBounds(108, 79, 429, 20);
                panel_CLient.add(txtNombre);
                
                JLabel label_1 = new JLabel("Nombre:");
                label_1.setBounds(12, 82, 71, 14);
                panel_CLient.add(label_1);
                
                JLabel label_2 = new JLabel("Direccion:");
                label_2.setBounds(12, 113, 71, 14);
                panel_CLient.add(label_2);
                
                txtDireccion = new JTextField();
                txtDireccion.setEnabled(false);
                txtDireccion.setColumns(10);
                txtDireccion.setBounds(108, 110, 429, 20);
                panel_CLient.add(txtDireccion);
                
                JLabel lblIdentificacion = new JLabel("Identificaci\u00F3n:");
                lblIdentificacion.setBounds(10, 52, 87, 14);
                panel_CLient.add(lblIdentificacion);
                
                txtIdentificacion = new JTextField();
                txtIdentificacion.setColumns(10);
                txtIdentificacion.setBounds(108, 49, 331, 20);
                txtIdentificacion.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        btnBuscar.setEnabled(true);
                    }
                });
                panel_CLient.add(txtIdentificacion);
                
                btnBuscar = new JButton("Buscar");
                btnBuscar.setEnabled(false);
                btnBuscar.setBounds(443, 47, 94, 25);
                btnBuscar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        buscarCliente(txtIdentificacion.getText());
                    }
                });
                panel_CLient.add(btnBuscar);
            }
            {
                JPanel panel_Pr = new JPanel();
                panel_Pr.setBorder(new TitledBorder(null, "Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                panel_Pr.setBounds(10, 181, 564, 383);
                panel.add(panel_Pr);
                panel_Pr.setLayout(null);
                
                JLabel label = new JLabel("Id:");
                label.setBounds(10, 24, 27, 14);
                panel_Pr.add(label);
                
                JLabel label_1 = new JLabel("Nombre:");
                label_1.setBounds(10, 54, 71, 14);
                panel_Pr.add(label_1);
                
                txtIdProyecto = new JTextField();
                txtIdProyecto.setEditable(false);
                txtIdProyecto.setText("P-"+Empresa.idProyectos);
                txtIdProyecto.setColumns(10);
                txtIdProyecto.setBounds(49, 21, 109, 20);
                panel_Pr.add(txtIdProyecto);
                
                txtNomPro = new JTextField();
                txtNomPro.setColumns(10);
                txtNomPro.setBounds(127, 51, 194, 20);
                panel_Pr.add(txtNomPro);
                
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Programadores disponibles:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
                panel_1.setBounds(10, 176, 194, 189);
                panel_Pr.add(panel_1);
                panel_1.setLayout(new BorderLayout(0, 0));
                
                JScrollPane scrollPane = new JScrollPane();
                panel_1.add(scrollPane);
                
                listProgramadoresDisp = new JList<Programador>(); // Removed re-declaration here
                scrollPane.setViewportView(listProgramadoresDisp);
                
                JPanel panel_2 = new JPanel();
                panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Programadores asignados:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
                panel_2.setBounds(358, 176, 194, 189);
                panel_Pr.add(panel_2);
                panel_2.setLayout(new BorderLayout(0, 0));
                
                JScrollPane scrollPane_1 = new JScrollPane();
                panel_2.add(scrollPane_1, BorderLayout.CENTER);
                
                listProgramadoresAsignados = new JList(); // Removed re-declaration here
                scrollPane_1.setViewportView(listProgramadoresAsignados);
                
                fillProgramadoresList();
                listProgramadoresDisp.setCellRenderer(new DefaultListCellRenderer() {
                    @Override
                    public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        if (value instanceof Programador) {
                            value = ((Programador) value).getNombre(); // Obt�n el nombre del Programador
                        }
                        return (java.awt.Component) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    }
                });
                listProgramadoresAsignados.setCellRenderer(new DefaultListCellRenderer() {
                    @Override
                    public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        if (value instanceof Programador) {
                            value = ((Programador) value).getNombre(); // Obt�n el nombre del Programador
                        }
                        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    }
                });
                

                
                JPanel panel_3 = new JPanel();
                panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                panel_3.setBounds(358, 23, 194, 140);
                panel_Pr.add(panel_3);
                panel_3.setLayout(null);
                
                JLabel lblHoras = new JLabel("Horas aprox:");
                lblHoras.setBounds(12, 29, 86, 16);
                panel_3.add(lblHoras);
                
                JSpinner spnHoras = new JSpinner();
                spnHoras.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
                spnHoras.setBounds(95, 26, 87, 22);
                panel_3.add(spnHoras);
                
                JLabel lblFechaFin = new JLabel("Fecha fin estimada:");
                lblFechaFin.setFont(new Font("Tahoma", Font.BOLD, 13));
                lblFechaFin.setBounds(43, 61, 112, 16);
                panel_3.add(lblFechaFin);
                
                textField = new JTextField();
                textField.setEditable(false);
                textField.setBounds(53, 90, 86, 22);
                panel_3.add(textField);
                textField.setColumns(10);
                
                JLabel label_2 = new JLabel("Jefe del Proyecto:");
                label_2.setBounds(10, 84, 138, 14);
                panel_Pr.add(label_2);
                
                JComboBox<Trabajador> comboBoxJefe = new JComboBox<>();
                comboBoxJefe.setBounds(127, 81, 194, 22);
                panel_Pr.add(comboBoxJefe);
                
                
                JComboBox<Trabajador> comboBoxDise�ador = new JComboBox<>();
                comboBoxDise�ador.setBounds(127, 111, 194, 22);
                panel_Pr.add(comboBoxDise�ador);
                
                JLabel label_3 = new JLabel("Dise\u00F1ador:");
                label_3.setBounds(10, 114, 138, 14);
                panel_Pr.add(label_3);
                
                JLabel label_4 = new JLabel("Planificador:");
                label_4.setBounds(10, 144, 138, 14);
                panel_Pr.add(label_4);
                
                JComboBox<Trabajador> comboBoxPlanificador = new JComboBox<>();
                comboBoxPlanificador.setBounds(127, 141, 194, 22);
                panel_Pr.add(comboBoxPlanificador);
                
                fillComboBoxes(comboBoxJefe, comboBoxDise�ador, comboBoxPlanificador);
                comboBoxJefe.setRenderer(new DefaultListCellRenderer() {
                    @Override
                    public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        if (value instanceof Trabajador) {
                            value = ((Trabajador) value).getNombre(); // Obt�n el nombre del Trabajador
                        }
                        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    }
                });

                comboBoxDise�ador.setRenderer(new DefaultListCellRenderer() {
                    @Override
                    public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        if (value instanceof Trabajador) {
                            value = ((Trabajador) value).getNombre(); // Obt�n el nombre del Trabajador
                        }
                        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    }
                });

                comboBoxPlanificador.setRenderer(new DefaultListCellRenderer() {
                    @Override
                    public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                        if (value instanceof Trabajador) {
                            value = ((Trabajador) value).getNombre(); // Obt�n el nombre del Trabajador
                        }
                        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    }
                });

                
                
                JButton btnQuitarEmpleado = new JButton("<<<");
                btnQuitarEmpleado.setBounds(224, 225, 97, 25);
                panel_Pr.add(btnQuitarEmpleado);
                btnQuitarEmpleado.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Obtener el programador seleccionado en la lista de programadores asignados
                        Programador selectedProgramador = listProgramadoresAsignados.getSelectedValue();
                        
                        // Si hay un programador seleccionado y no est� en la lista de disponibles, agregarlo
                        if (selectedProgramador != null && !modelDisp.contains(selectedProgramador)) {
                            modelDisp.addElement(selectedProgramador); // Agregar a la lista de disponibles
                            modelAsignados.removeElement(selectedProgramador); // Quitar de la lista de asignados
                        }
                    }
                });
                
                
                JButton btnAgregarEmpleado = new JButton(">>>");
                btnAgregarEmpleado.setBounds(224, 283, 97, 25);
                panel_Pr.add(btnAgregarEmpleado);
                btnAgregarEmpleado.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Obtener el programador seleccionado en la lista de programadores disponibles
                        Programador selectedProgramador = listProgramadoresDisp.getSelectedValue();
                        
                        // Si hay un programador seleccionado y no est� en la lista de asignados, agregarlo
                        if (selectedProgramador != null && !modelAsignados.contains(selectedProgramador)) {
                            modelAsignados.addElement(selectedProgramador); // Agregar a la lista de asignados
                            modelDisp.removeElement(selectedProgramador); // Quitar de la lista de disponibles
                        }
                    }
                });
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton("Formalizar");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {


                        //Contrato contrato = new Contrato(lblIdContrato.getText(), client, client.getLosProyectos().get(comboBoxPoyectos.getSelectedIndex()), (int)spnDias.getValue());
                        //System.out.println(contrato.getFechaEntrega());

                        //Empresa.getInstance().getLoscontratos().add(contrato);
                        clearContract();
                        JOptionPane.showMessageDialog(null,"Registro Satisfactorio" ,"Informaci�n", JOptionPane.INFORMATION_MESSAGE);
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
    }
    
    private void fillComboBoxes(JComboBox<Trabajador> comboBoxJefe, JComboBox<Trabajador> comboBoxDise�ador,
            JComboBox<Trabajador> comboBoxPlanificador) {
        ArrayList<Trabajador> trabajadores = Empresa.getInstance().getMistabajadores();

        for (Trabajador trabajador : trabajadores) {
            if (trabajador instanceof JefeProyecto) {
                comboBoxJefe.addItem(trabajador);
            }
        }

        for (Trabajador trabajador : trabajadores) {
            if (trabajador instanceof Disenador) {
                comboBoxDise�ador.addItem(trabajador);
            }
        }

        for (Trabajador trabajador : trabajadores) {
            if (trabajador instanceof Planificador) {
                comboBoxPlanificador.addItem(trabajador);
            }
        }
    }

    
    private void fillProgramadoresList() {
        ArrayList<Trabajador> trabajadores = Empresa.getInstance().getMistabajadores();

        DefaultListModel<Programador> model = new DefaultListModel<>();
        for (Trabajador trabajador : trabajadores) {
            if (trabajador instanceof Programador) {
                model.addElement((Programador) trabajador);
            }
        }
        listProgramadoresDisp.setModel(model); // Aqu� se asigna el modelo a la lista de programadores disponibles

        listProgramadoresAsignados.setModel(modelAsignados); // Aqu� se asigna el modelo de programadores asignados a la lista correspondiente
    }
   

    
    private void buscarCliente(String id) {
        Cliente aux = null;
        if(!id.isEmpty()) {
            aux = Empresa.getInstance().buscarClienteByIdentificador(id);
            
            if(aux != null) {
                txtNombre.setText(aux.getNombre());
                txtDireccion.setText(aux.getDireccion());
                txtIdCliente.setText(aux.getId());
                existeCliente = true;
            } else

 {
                txtIdCliente.setText(new String("C-"+Empresa.getInstance().idClientes));
                txtNombre.setText("");
                txtDireccion.setText("");
                existeCliente = false;
            }
            
            txtNombre.setEnabled(true);
            txtDireccion.setEnabled(true);
        }
    }
    
    private Cliente guardarCliente() {
        Cliente aux = null;
        if(existeCliente) {
            aux = Empresa.getInstance().buscarClienteByIdentificador(txtIdCliente.getText());
            aux.setNombre(txtNombre.getText());
            aux.setDireccion(txtDireccion.getText());
            
        }else {
            if(!txtNombre.getText().isEmpty() && !txtDireccion.getText().isEmpty() && !txtIdentificacion.getText().isEmpty()) {
                String id = txtIdCliente.getText();
                Empresa.getInstance().idClientes++;
                aux = new Cliente(id,txtIdentificacion.getText(),txtNombre.getText(),txtDireccion.getText());
                Empresa.getInstance().registrarCliente(aux);
            }
        }
        
        return aux;
    }
    
    private void clearContract() {
        Empresa.idContratos++;
        txtDireccion.setText("");
        txtIdCliente.setText("C-");
        txtNombre.setText("");
        txtNomPro.setText("P-");
            
    }
}