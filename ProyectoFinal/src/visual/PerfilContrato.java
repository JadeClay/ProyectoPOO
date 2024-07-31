package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
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
import logico.Contrato;
import logico.Disenador;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class PerfilContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtHorasHombre;
	private JTextField txtFechaInicio;
	private JTextField txtEstado;
	private JTextField txtJefe;
	private JTextField txtCliente;
	private JTextField txtFechaFin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Contrato contrato = null;
			PerfilContrato dialog = new PerfilContrato(contrato);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public PerfilContrato(Contrato contrato) {
		setResizable(false);
		setModal(true);
		setTitle("Proyecto " + contrato.getProyecto().getNombre());
		setBounds(100, 100, 523, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new TitledBorder(null, "Trabajadores Asignados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setBounds(22, 158, 457, 204);
		contentPanel.add(scrollPane);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		{
			DefaultListModel<String> trabajadores = new DefaultListModel<String>();
			for(Trabajador t : contrato.getProyecto().getLosTrabajadores()) {
				if(t instanceof JefeProyecto) {
					
				} else if (t instanceof Programador) {
					trabajadores.addElement("Programador - " + t.getNombre() + " " + t.getApellidos());
				} else if(t instanceof Planificador) {
					trabajadores.addElement("Planificador - " + t.getNombre() + " " + t.getApellidos());
				} else {
					trabajadores.addElement("Disenador - " + t.getNombre() + " " + t.getApellidos());
				}
			}
			
			list.setModel(trabajadores);
		}
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(22, 13, 24, 16);
		contentPanel.add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(22, 42, 88, 16);
		contentPanel.add(lblNombre);
		
		JLabel lblHorasHombre = new JLabel("Horas hombre:");
		lblHorasHombre.setBounds(22, 71, 88, 16);
		contentPanel.add(lblHorasHombre);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio:");
		lblFechaInicio.setBounds(22, 100, 88, 16);
		contentPanel.add(lblFechaInicio);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(287, 42, 60, 16);
		contentPanel.add(lblEstado);
		
		JLabel lblJefeProyecto = new JLabel("Jefe:");
		lblJefeProyecto.setBounds(287, 71, 50, 16);
		contentPanel.add(lblJefeProyecto);
		
		JLabel lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(287, 100, 50, 16);
		contentPanel.add(lblCliente);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(127, 10, 137, 22);
		contentPanel.add(txtId);
		txtId.setColumns(10);
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setColumns(10);
		txtNombre.setBounds(127, 39, 137, 22);
		contentPanel.add(txtNombre);
		txtId.setText(contrato.getProyecto().getId());
		txtNombre.setText(contrato.getProyecto().getNombre());
		
		txtHorasHombre = new JTextField();
		txtHorasHombre.setEditable(false);
		txtHorasHombre.setColumns(10);
		txtHorasHombre.setBounds(127, 68, 137, 22);
		contentPanel.add(txtHorasHombre);
		txtHorasHombre.setText(""+contrato.getHoras());
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setEditable(false);
		txtFechaInicio.setColumns(10);
		txtFechaInicio.setBounds(127, 97, 137, 22);
		contentPanel.add(txtFechaInicio);
		txtFechaInicio.setText(contrato.getFechaInicio().toString());
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setBounds(349, 39, 130, 22);
		contentPanel.add(txtEstado);
		txtEstado.setColumns(10);
		if(contrato.getProyecto().getEstado()) {
			txtEstado.setText("ACTIVO");
		} else {
			txtEstado.setText("INACTIVO");
		}
		
		txtJefe = new JTextField();
		txtJefe.setEditable(false);
		txtJefe.setBounds(349, 68, 130, 22);
		contentPanel.add(txtJefe);
		txtJefe.setColumns(10);
		txtJefe.setText(contrato.getProyecto().getJefeProyectoAsignado().getNombre() + " " + contrato.getProyecto().getJefeProyectoAsignado().getApellidos());
		
		txtCliente = new JTextField();
		txtCliente.setEditable(false);
		txtCliente.setBounds(349, 97, 130, 22);
		contentPanel.add(txtCliente);
		txtCliente.setColumns(10);
		txtCliente.setText(contrato.getCliente().getNombre());
		
		JLabel lblFechaFin = new JLabel("Fecha Fin:");
		lblFechaFin.setBounds(22, 129, 88, 16);
		contentPanel.add(lblFechaFin);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setEditable(false);
		txtFechaFin.setBounds(127, 126, 137, 22);
		txtFechaFin.setText(contrato.getFechaEntrega().toString());
		contentPanel.add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
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
		Image img = new ImageIcon(this.getClass().getResource("/visual/img//trabajador.png")).getImage();
		this.setIconImage(img);
	}

}
