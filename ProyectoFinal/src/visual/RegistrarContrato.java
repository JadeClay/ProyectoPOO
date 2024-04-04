package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;

import logico.Cliente;
import logico.Contrato;
import logico.Empresa;
import logico.Proyecto;
import logico.Trabajador;

import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RegistrarContrato extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JTextField txtIdCliente;
	private JTextField txtNomCli;
	private JTextField txtDireccion;
	private JTextField txtIdProyecto;
	private JTextField txtNomPro;
	private JButton cancelButton;
	private JComboBox comboBoxPoyectos;
	private JSpinner spnDias;
	private JSpinner spnHoras;
	private JLabel lblIdContrato;
	private JList listEncargados;
	private ArrayList<String> dataEncargados = new ArrayList<>(); 
	private Cliente client = null;
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
		setTitle("Registro De Contrato");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 607, 500);
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
				panel_CLient.setBounds(10, 16, 564, 119);
				panel.add(panel_CLient);
				panel_CLient.setLayout(null);
				
				JLabel label = new JLabel("Id:");
				label.setBounds(10, 20, 46, 14);
				panel_CLient.add(label);
				
				txtIdCliente = new JTextField();
				
				txtIdCliente.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						 client = Empresa.getInstance().getClienteById(txtIdCliente.getText());
						
						if (client != null) {
							txtNomCli.setText(client.getNombre());
							txtDireccion.setText(client.getDireccion());
							comboBoxPoyectos.setEnabled(true);
							comboBoxPoyectos = new JComboBox<>(client.getLosProyectos().toArray());

						}else {
							txtNomCli.setText("");
							txtDireccion.setText("");
							txtNomPro.setText("");
							txtIdProyecto.setText("P-");
							comboBoxPoyectos.setEnabled(false);
							
							if (dataEncargados.size() > 0) {
								dataEncargados.clear();
								listEncargados.setListData(dataEncargados.toArray());
							}
							
						}
					}
				});
				

				txtIdCliente.setText("C-");
				txtIdCliente.setColumns(10);
				txtIdCliente.setBounds(83, 17, 109, 20);
				panel_CLient.add(txtIdCliente);
				
				JLabel label_1 = new JLabel("Nombre:");
				label_1.setBounds(10, 51, 71, 14);
				panel_CLient.add(label_1);
				
				txtNomCli = new JTextField();
				txtNomCli.setEditable(false);
				txtNomCli.setColumns(10);
				txtNomCli.setBounds(83, 48, 318, 20);
				panel_CLient.add(txtNomCli);
				
				JLabel label_2 = new JLabel("Direccion:");
				label_2.setBounds(10, 82, 71, 14);
				panel_CLient.add(label_2);
				
				txtDireccion = new JTextField();
				txtDireccion.setEditable(false);
				txtDireccion.setColumns(10);
				txtDireccion.setBounds(83, 79, 441, 20);
				panel_CLient.add(txtDireccion);
			}
			{
				JPanel panel_Pr = new JPanel();
				panel_Pr.setBorder(new TitledBorder(null, "Proyecto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_Pr.setBounds(10, 146, 564, 93);
				panel.add(panel_Pr);
				panel_Pr.setLayout(null);
				
				comboBoxPoyectos = new JComboBox();
				comboBoxPoyectos.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						
						
						txtIdProyecto.setText(client.getLosProyectos().get(comboBoxPoyectos.getSelectedIndex()).getId());
						txtNomPro.setText(client.getLosProyectos().get(comboBoxPoyectos.getSelectedIndex()).getNombre());					
						plotEncargados();
						
					}
				});
				comboBoxPoyectos.setEnabled(false);
				comboBoxPoyectos.setBounds(437, 11, 117, 20);
				panel_Pr.add(comboBoxPoyectos);
				
				JLabel label = new JLabel("Id:");
				label.setBounds(10, 23, 46, 14);
				panel_Pr.add(label);
				
				JLabel label_1 = new JLabel("Nombre:");
				label_1.setBounds(10, 54, 71, 14);
				panel_Pr.add(label_1);
				
				txtIdProyecto = new JTextField();
				txtIdProyecto.setEditable(false);
				txtIdProyecto.setText("P-");
				txtIdProyecto.setColumns(10);
				txtIdProyecto.setBounds(83, 20, 109, 20);
				panel_Pr.add(txtIdProyecto);
				
				txtNomPro = new JTextField();
				txtNomPro.setEditable(false);
				txtNomPro.setColumns(10);
				txtNomPro.setBounds(83, 51, 318, 20);
				panel_Pr.add(txtNomPro);
			}
			
			JPanel panel_1 = new JPanel();
			panel_1.setBounds(397, 259, 177, 135);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, BorderLayout.CENTER);
			
			listEncargados = new JList();
			scrollPane.setViewportView(listEncargados);
			
			JLabel lblNewLabel = new JLabel("Encargados Del Proyecto:");
			lblNewLabel.setBounds(398, 239, 164, 14);
			panel.add(lblNewLabel);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Ajuste De Horas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(10, 250, 377, 147);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			spnDias = new JSpinner();
			spnDias.addChangeListener(new ChangeListener() {
				
				public void stateChanged(ChangeEvent e) {
					spnHoras.setValue((float) spnDias.getValue()*6);
				}
			});
			
			spnDias.setBounds(83, 35, 109, 20);
			panel_2.add(spnDias);
			spnDias.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(1)));
			
			JLabel lblNewLabel_1 = new JLabel("Dias:");
			lblNewLabel_1.setBounds(10, 38, 80, 14);
			panel_2.add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Hrs Hombre:");
			lblNewLabel_2.setBounds(10, 86, 80, 14);
			panel_2.add(lblNewLabel_2);
			
			spnHoras = new JSpinner();
			spnHoras.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					spnDias.setValue((float) spnHoras.getValue()/6);
					
				}
			});
			spnHoras.setBounds(83, 83, 109, 20);
			panel_2.add(spnHoras);
			spnHoras.setModel(new SpinnerNumberModel(new Float(0), new Float(0), null, new Float(6)));
			
			lblIdContrato = new JLabel("FA-"+Empresa.idContratos);
			lblIdContrato.setBounds(265, 2, 46, 14);
			panel.add(lblIdContrato);
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
						
						
						Contrato contrato = new Contrato(lblIdContrato.getText(), client, client.getLosProyectos().get(comboBoxPoyectos.getSelectedIndex()), (int)spnDias.getValue());
						Empresa.getInstance().getLoscontratos().add(contrato);
						clearContract();
						JOptionPane.showMessageDialog(null,"Registro Satisfactorio" ,"Información", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
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
	private void plotEncargados() {
		
		for (Trabajador t : client.getLosProyectos().get(comboBoxPoyectos.getSelectedIndex()).getLosTrabajadores()) {
			
			String item = new String(Empresa.getInstance().getPosiDeTrabajador(t)+" : "+t.getId()+" : "+t.getNombre()); //get posi esta en trabajador too
			
			dataEncargados.add(item);
					
		}
		listEncargados.setListData(dataEncargados.toArray());
		
	}	
	
private void clearContract() {
	Empresa.idContratos++;
	txtDireccion.setText("");
	txtIdCliente.setText("C-");
	txtNomCli.setText("");
	txtNomPro.setText("P-");
	lblIdContrato.setText("FA-"+Empresa.idContratos);
	spnDias.setValue(new Float(0));
	spnHoras.setValue(new Float(0));
	dataEncargados.clear();
	listEncargados.setListData(dataEncargados.toArray());
		
	}
	
	
	
}
