package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import logico.JefeProyecto;
import logico.Planificador;
import logico.Programador;
import logico.Trabajador;
import logico.Disenador;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class PerfilTrabajador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtIdentificacion;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtSexo;
	private JTextField txtEdad;
	private JTextField txtSalario;
	private JTextField txtEvaluacion;
	private JTextField txtExtra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Trabajador trabajador = null;
			PerfilTrabajador dialog = new PerfilTrabajador(trabajador);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public PerfilTrabajador(Trabajador trabajador) {
		setResizable(false);
		setModal(true);
		setTitle("Perfil de " + trabajador.getNombre() + " " + trabajador.getApellidos());
		setBounds(100, 100, 523, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Hist\u00F3rico Evaluativo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(22, 158, 457, 204);
		contentPanel.add(scrollPane);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Integer) {
                	if(((int) value) > 90) {
                		value = new String(value + " - Destacado");
                	} else if(((int) value) > 70) {
                		value = new String(value + " - Cumplidor");
                	} else {
                		value = new String(value + " - Incumplidor");
                	}
                }
                return (java.awt.Component) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });
		scrollPane.setViewportView(list);
		{
			DefaultListModel<Integer> historial = new DefaultListModel<Integer>();
			for(int evaluacion : trabajador.getHistorialPuntuacion()) {
				historial.addElement(evaluacion);
			}
			
			list.setModel(historial);
		}
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(22, 13, 24, 16);
		contentPanel.add(lblId);
		
		JLabel lblIdentificacion = new JLabel("Identificaci\u00F3n:");
		lblIdentificacion.setBounds(22, 42, 88, 16);
		contentPanel.add(lblIdentificacion);
		
		JLabel lblNombres = new JLabel("Nombres:");
		lblNombres.setBounds(22, 71, 64, 16);
		contentPanel.add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(22, 100, 64, 16);
		contentPanel.add(lblApellidos);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setBounds(276, 13, 42, 16);
		contentPanel.add(lblSexo);
		
		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setBounds(276, 42, 42, 16);
		contentPanel.add(lblEdad);
		
		JLabel lblSalario = new JLabel("Salario:");
		lblSalario.setBounds(276, 71, 45, 16);
		contentPanel.add(lblSalario);
		
		JLabel lblNewLabel = new JLabel("Evaluaci\u00F3n:");
		lblNewLabel.setBounds(276, 100, 71, 16);
		contentPanel.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(127, 10, 137, 22);
		contentPanel.add(txtId);
		txtId.setColumns(10);
		txtIdentificacion = new JTextField();
		txtIdentificacion.setEditable(false);
		txtIdentificacion.setColumns(10);
		txtIdentificacion.setBounds(127, 39, 137, 22);
		contentPanel.add(txtIdentificacion);
		txtId.setText(trabajador.getId());
		txtIdentificacion.setText(trabajador.getIdentificacion());
		
		txtNombres = new JTextField();
		txtNombres.setEditable(false);
		txtNombres.setColumns(10);
		txtNombres.setBounds(127, 68, 137, 22);
		contentPanel.add(txtNombres);
		txtNombres.setText(trabajador.getNombre());
		
		txtApellidos = new JTextField();
		txtApellidos.setEditable(false);
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(127, 97, 137, 22);
		contentPanel.add(txtApellidos);
		txtApellidos.setText(trabajador.getApellidos());
		
		txtSexo = new JTextField();
		txtSexo.setEditable(false);
		txtSexo.setBounds(363, 10, 116, 22);
		contentPanel.add(txtSexo);
		txtSexo.setColumns(10);
		txtSexo.setText(trabajador.getSexo());
		
		txtEdad = new JTextField();
		txtEdad.setEditable(false);
		txtEdad.setBounds(363, 39, 116, 22);
		contentPanel.add(txtEdad);
		txtEdad.setColumns(10);
		txtEdad.setText(new String("" + trabajador.getEdad()));
		
		txtSalario = new JTextField();
		txtSalario.setEditable(false);
		txtSalario.setBounds(363, 68, 116, 22);
		contentPanel.add(txtSalario);
		txtSalario.setColumns(10);
		txtSalario.setText(new String("RD$" + trabajador.getSalario()));
		
		txtEvaluacion = new JTextField();
		txtEvaluacion.setEditable(false);
		txtEvaluacion.setBounds(363, 97, 116, 22);
		contentPanel.add(txtEvaluacion);
		txtEvaluacion.setColumns(10);
		txtEvaluacion.setText(trabajador.getEvaluacionActual());
		
		JLabel lblExtra = new JLabel("Extra:");
		lblExtra.setBounds(102, 132, 132, 16);
		contentPanel.add(lblExtra);
		
		txtExtra = new JTextField();
		txtExtra.setEditable(false);
		txtExtra.setBounds(246, 129, 116, 22);
		contentPanel.add(txtExtra);
		txtExtra.setColumns(10);
		
		if(trabajador instanceof Programador) {
			lblExtra.setText("Lenguaje:");
			txtExtra.setText(((Programador) trabajador).getLenguaje());
		} else if(trabajador instanceof Planificador) {
			lblExtra.setText("Frecuencia:");
			txtExtra.setText(new String("" + ((Planificador) trabajador).getCantDias()));
		} else if(trabajador instanceof JefeProyecto) {
			lblExtra.setText("Empleados a cargo:");
			txtExtra.setText(new String("" + ((JefeProyecto) trabajador).getCantTrabajadores()));
		} else {
			lblExtra.setText("Años de Experiencia:");
			txtExtra.setText(new String("" + ((Disenador) trabajador).getAniosExp()));
		}
		

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
