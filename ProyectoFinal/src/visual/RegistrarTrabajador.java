package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import logico.Database;
import logico.Disenador;
import logico.Empresa;
import logico.JefeProyecto;
import logico.Planificador;
import logico.Programador;
import logico.Trabajador;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class RegistrarTrabajador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JRadioButton rbtnJefeProyecto;
	private JRadioButton rbtnDisenador;
	private JRadioButton rbtnProgramador;
	private JRadioButton rbtnPlanificador;
	private JPanel pnlProgramador;
	private JPanel panel_2;
	private JPanel pnlPlanificador;
	private JSpinner spnFrecuencia;
	private JTextField txtLenguaje;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtIdentificacion;
	private JComboBox cbxSexo;
	private JSpinner spnSalario;
	private JSpinner spnEdad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarTrabajador dialog = new RegistrarTrabajador();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarTrabajador() {
		setTitle("Registro");
		setBounds(100, 100, 489, 493);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 11, 437, 235);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblId = new JLabel("Id:");
			lblId.setBounds(10, 29, 46, 14);
			panel_1.add(lblId);
			
			txtId = new JTextField();
			txtId.setEditable(false);
			txtId.setBounds(33, 26, 57, 20);
			panel_1.add(txtId);
			txtId.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(24, 93, 50, 14);
			panel_1.add(lblNombre);
			
			JLabel lblApellido = new JLabel("Apellido:");
			lblApellido.setBounds(231, 93, 50, 14);
			panel_1.add(lblApellido);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(104, 89, 115, 22);
			panel_1.add(txtNombre);
			txtNombre.setColumns(10);
			
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
			txtApellido.setBounds(293, 89, 116, 22);
			panel_1.add(txtApellido);
			
			JLabel lblDireccion = new JLabel("Direccion:");
			lblDireccion.setBounds(24, 124, 80, 14);
			panel_1.add(lblDireccion);
			
			txtDireccion = new JTextField();
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(104, 120, 305, 22);
			panel_1.add(txtDireccion);
			
			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(24, 155, 50, 14);
			panel_1.add(lblSexo);
			
			JLabel lblEdad = new JLabel("Edad:");
			lblEdad.setBounds(231, 155, 33, 14);
			panel_1.add(lblEdad);
			
			spnEdad = new JSpinner();
			spnEdad.setModel(new SpinnerNumberModel(18, 16, 100, 1));
			spnEdad.setBounds(293, 151, 116, 22);
			panel_1.add(spnEdad);
			
			JLabel lblSalariopagoX = new JLabel("Salario (Pago x hora):");
			lblSalariopagoX.setBounds(24, 190, 133, 14);
			panel_1.add(lblSalariopagoX);
			
			spnSalario = new JSpinner();
			spnSalario.setModel(new SpinnerNumberModel(new Float(150), null, null, new Float(1)));
			spnSalario.setBounds(169, 186, 240, 22);
			panel_1.add(spnSalario);
			
			JLabel lblIdentificacion = new JLabel("Identificacion:");
			lblIdentificacion.setBounds(20, 66, 80, 14);
			panel_1.add(lblIdentificacion);
			
			txtIdentificacion = new JTextField();
			txtIdentificacion.setColumns(10);
			txtIdentificacion.setBounds(104, 59, 305, 22);
			panel_1.add(txtIdentificacion);
			
			cbxSexo = new JComboBox();
			cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar...", "F", "M"}));
			cbxSexo.setBounds(104, 151, 115, 22);
			panel_1.add(cbxSexo);
			
			panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cargo:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(10, 259, 437, 64);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			rbtnJefeProyecto = new JRadioButton("Jefe Proyecto");
			rbtnJefeProyecto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnProgramador.setSelected(false);
					rbtnDisenador.setSelected(false);
					rbtnJefeProyecto.setSelected(true);
					rbtnPlanificador.setSelected(false);
					pnlProgramador.setVisible(false);
					pnlPlanificador.setVisible(false);
				}
			});
			rbtnJefeProyecto.setBounds(113, 22, 105, 23);
			panel_2.add(rbtnJefeProyecto);
			
			rbtnDisenador = new JRadioButton("Dise\u00F1ador");
			rbtnDisenador.setSelected(true);
			rbtnDisenador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnProgramador.setSelected(false);
					rbtnDisenador.setSelected(true);
					rbtnJefeProyecto.setSelected(false);
					rbtnPlanificador.setSelected(false);
					pnlProgramador.setVisible(false);
					pnlPlanificador.setVisible(false);
				}
			});
			rbtnDisenador.setBounds(18, 22, 85, 23);
			panel_2.add(rbtnDisenador);
			
			rbtnProgramador = new JRadioButton("Programador");
			rbtnProgramador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					rbtnProgramador.setSelected(true);
					rbtnDisenador.setSelected(false);
					rbtnJefeProyecto.setSelected(false);
					rbtnPlanificador.setSelected(false);
					pnlProgramador.setVisible(true);
					pnlPlanificador.setVisible(false);
				}
			});
			rbtnProgramador.setBounds(222, 22, 105, 23);
			panel_2.add(rbtnProgramador);
			
			rbtnPlanificador = new JRadioButton("Planificador");
			rbtnPlanificador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnPlanificador.setSelected(true);
					rbtnProgramador.setSelected(false);
					rbtnDisenador.setSelected(false);
					rbtnJefeProyecto.setSelected(false);
					pnlProgramador.setVisible(false);
					pnlPlanificador.setVisible(true);
				}
			});
			rbtnPlanificador.setBounds(331, 22, 98, 23);
			panel_2.add(rbtnPlanificador);
			
			pnlPlanificador = new JPanel();
			pnlPlanificador.setVisible(false);
			pnlPlanificador.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlPlanificador.setBounds(10, 336, 437, 47);
			panel.add(pnlPlanificador);
			pnlPlanificador.setLayout(null);
			
			JLabel lblFrecuencia = new JLabel("Frecuencia de Planificacion:");
			lblFrecuencia.setBounds(10, 16, 161, 14);
			pnlPlanificador.add(lblFrecuencia);
			
			spnFrecuencia = new JSpinner();
			spnFrecuencia.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnFrecuencia.setBounds(183, 13, 60, 20);
			pnlPlanificador.add(spnFrecuencia);
			
			pnlProgramador = new JPanel();
			pnlProgramador.setBounds(10, 336, 437, 47);
			panel.add(pnlProgramador);
			pnlProgramador.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlProgramador.setLayout(null);
			
			JLabel lblLenguaje = new JLabel("Lenguaje");
			lblLenguaje.setBounds(10, 16, 60, 14);
			pnlProgramador.add(lblLenguaje);
			
			txtLenguaje = new JTextField();
			txtLenguaje.setBounds(77, 12, 116, 22);
			pnlProgramador.add(txtLenguaje);
			txtLenguaje.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("Contratar");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Trabajador trabajador = null;
						String id = txtId.getText();
						String identificacion = txtIdentificacion.getText();
						String nombre = txtNombre.getText();
						String apellido = txtApellido.getText();
						String direccion = txtDireccion.getText();
						String sexo = cbxSexo.getSelectedItem().toString();
						int edad = (int) spnEdad.getValue();
						float salario = (float) spnSalario.getValue();
						boolean validado = false;
						
						if(rbtnPlanificador.isSelected()){
							int cantDias = new Integer(spnFrecuencia.getValue().toString());
							trabajador = new Planificador(id,identificacion,nombre,apellido,direccion,sexo,edad,salario,cantDias);
						}
						else if(rbtnDisenador.isSelected()){ 
							trabajador = new Trabajador(id,identificacion,nombre,apellido,direccion,sexo,edad,salario);
						}
						else if(rbtnProgramador.isSelected()){
							String lenguaje = txtLenguaje.getText();
							trabajador = new Programador(id,identificacion,nombre,apellido,direccion,sexo,edad,salario,lenguaje);
							if(lenguaje.isEmpty()) {
								validado = true;
							}
						} else {
							trabajador = new JefeProyecto(id,identificacion,nombre,apellido,direccion,sexo,edad,salario,0);
						}
						
						if(validado || verificarCampos(id,identificacion,nombre,apellido,direccion,sexo,edad,salario)) {
							Database database = new Database();
							if(trabajador instanceof Planificador) {
								database.addPlanificador(new Integer(trabajador.getId().substring(2)), trabajador.getIdentificacion(), trabajador.getNombre(), trabajador.getApellidos(), trabajador.getDireccion(), trabajador.getSexo(), trabajador.getEdad(), trabajador.getSalario(), new Integer(spnFrecuencia.getValue().toString()));
							} else if (trabajador instanceof JefeProyecto) {
								database.addJefeProyecto(new Integer(trabajador.getId().substring(2)), trabajador.getIdentificacion(), trabajador.getNombre(), trabajador.getApellidos(), trabajador.getDireccion(), trabajador.getSexo(), trabajador.getEdad(), trabajador.getSalario());
							} else if(trabajador instanceof Programador) {
								database.addProgramador(new Integer(trabajador.getId().substring(2)), trabajador.getIdentificacion(), trabajador.getNombre(), trabajador.getApellidos(), trabajador.getDireccion(), trabajador.getSexo(), trabajador.getEdad(), trabajador.getSalario(), txtLenguaje.getText());
							} else {
								database.addDisenador(new Integer(trabajador.getId().substring(2)), trabajador.getIdentificacion(), trabajador.getNombre(), trabajador.getApellidos(), trabajador.getDireccion(), trabajador.getSexo(), trabajador.getEdad(), trabajador.getSalario());
							}
							
							resetValues();
							JOptionPane.showMessageDialog(null,"Registro Satisfactorio" ,"Información", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null,"Registro fallido" ,"Información", JOptionPane.ERROR_MESSAGE);
						}
						
						
					}

			
				});
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCerrar = new JButton("Cancelar");
				btnCerrar.setActionCommand("Cancel");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(btnCerrar);
			}
		}
		resetValues();
		Image img = new ImageIcon(this.getClass().getResource("/visual/img//trabajador.png")).getImage();
		this.setIconImage(img);
	}
	
	private void resetValues() {
		rbtnProgramador.setSelected(false);
		rbtnDisenador.setSelected(true);
		rbtnJefeProyecto.setSelected(false);
		pnlProgramador.setVisible(false);
		pnlPlanificador.setVisible(false);
		txtLenguaje.setText("");
		txtNombre.setText("");
		txtApellido.setText("");
		txtDireccion.setText("");
		
		txtIdentificacion.setText("");
		
		Database db = new Database();
		txtId.setText("T-" + db.recoverLastIDWorker());
		
		spnFrecuencia.setValue(new Integer(1));
		spnEdad.setValue(new Integer(18));
		spnSalario.setValue(new Float(150));
		
	}
	
	private boolean verificarCampos(String id, String identificacion, String nombre, String apellidos, String direccion, String sexo,
			int edad, float salario) {
		boolean result = false;
		
		if(!id.isEmpty() && !identificacion.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty() && !direccion.isEmpty() 
				&& !sexo.isEmpty() && (edad != 0) && (salario != 0)) {
			result = true;
		}
		
		return result;
	}
}
