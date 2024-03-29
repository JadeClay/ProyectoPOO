package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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

public class RegistrarTrabajador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JRadioButton rbtnJefeProyecto;
	private JRadioButton rbtnDisenador;
	private JRadioButton rbtnProgramador;
	private JRadioButton rbtnPlanificador;
	private JPanel pnlDisenador;
	private JSpinner spnAnios;
	private JPanel pnlProgramador;
	private JPanel panel_2;
	private JPanel pnlPlanificador;
	private JSpinner spnFrecuencia;
	private JTextField txtLenguaje;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDireccion;
	private JTextField txtSexo;
	private JTextField txtIdentificacion;

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
			//txtId.setText("Q-"+Complejo.getInstance().idQueso);
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
			
			txtSexo = new JTextField();
			txtSexo.setColumns(10);
			txtSexo.setBounds(104, 151, 100, 22);
			panel_1.add(txtSexo);
			
			JLabel lblEdad = new JLabel("Edad:");
			lblEdad.setBounds(231, 155, 33, 14);
			panel_1.add(lblEdad);
			
			JSpinner spnEdad = new JSpinner();
			spnEdad.setModel(new SpinnerNumberModel(18, 16, 100, 1));
			spnEdad.setBounds(293, 151, 116, 22);
			panel_1.add(spnEdad);
			
			JLabel lblSalariopagoX = new JLabel("Salario (Pago x hora):");
			lblSalariopagoX.setBounds(24, 190, 133, 14);
			panel_1.add(lblSalariopagoX);
			
			JSpinner spnSalario = new JSpinner();
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
					pnlDisenador.setVisible(false);
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
					pnlDisenador.setVisible(true);
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
					pnlDisenador.setVisible(false);
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
					pnlDisenador.setVisible(false);
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
			
			pnlDisenador = new JPanel();
			pnlDisenador.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlDisenador.setBounds(10, 336, 437, 47);
			panel.add(pnlDisenador);
			pnlDisenador.setLayout(null);
			
			JLabel lblAnios = new JLabel("A\u00F1os de Experiencia:");
			lblAnios.setBounds(10, 16, 120, 14);
			pnlDisenador.add(lblAnios);
			
			spnAnios = new JSpinner();
			spnAnios.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnAnios.setBounds(157, 13, 141, 20);
			pnlDisenador.add(spnAnios);
			
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
						//Queso queso = null;
						String id = txtId.getText();
						if(rbtnJefeProyecto.isSelected()){
							float radioEsfera = new Float(spnAnios.getValue().toString());
							//queso = new Esfera(id,costoUnitario,precioBase, radioEsfera);
						}
						if(rbtnDisenador.isSelected()){ 
							//queso = new Cilindro(id, costoUnitario, precioBase, radioCilindro, longitudCilindro);
						}
						if(rbtnProgramador.isSelected()){
							float radioCilindroHExt = new Float(spnFrecuencia.getValue().toString());
							//queso = new CilindroHueco(id, costoUnitario, precioBase, radioCilindroHExt, longitudCilindroH, radioCilindroHInt);
						}
						
						//Complejo.getInstance().insertarQueso(queso);
						JOptionPane.showMessageDialog(null,"Registro Satisfactorio" ,"Información", JOptionPane.INFORMATION_MESSAGE);
						clean();
					}

			
				});
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnCerrar = new JButton("Cancelar");
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
	}
	
	private void clean() {
		rbtnProgramador.setSelected(false);
		rbtnDisenador.setSelected(true);
		rbtnJefeProyecto.setSelected(false);
		pnlDisenador.setVisible(true);
		pnlProgramador.setVisible(false);
		pnlPlanificador.setVisible(false);
		
	//	txtId.setText("Q-"+Complejo.getInstance().idQueso);
		
		spnFrecuencia.setValue(new Float(0));
		spnAnios.setValue(new Float(0));
		
	}
}
