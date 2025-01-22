package Vista;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class verReuniones extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnAtras;
	private JTable tablaPendientes;
	private JTable tablaReuniones;
	private JScrollPane scrollReuniones;
	private JScrollPane scrollPendientes;
	private JLabel lblPendientes;
	private DefaultTableModel modeloR;
	private DefaultTableModel modeloP;
	/**
	 * Create the panel.
	 */
	public verReuniones() {
		setBounds(0, 0, 500, 400);
		setLayout(null);
		
		btnAtras = new JButton("Volver");
		btnAtras.setBounds(383, 11, 89, 23);
		add(btnAtras);
		
		String columnas[] = { "Estado","Titulo","Asunto","Fecha","Aula","Centro","Alumno"};
		modeloR=new DefaultTableModel(columnas,0);
		
		String columnas2[] = { "Estado","Titulo","Asunto","Fecha","Aula","Centro","Alumno","Aceptar","Rechazar"};
		modeloP=new DefaultTableModel(columnas2,0);
		
		scrollReuniones = new JScrollPane();
		scrollReuniones.setBounds(10, 45, 462, 181);
		add(scrollReuniones);
		
		tablaReuniones = new JTable(modeloR);
		scrollReuniones.setViewportView(tablaReuniones);
		
		scrollPendientes = new JScrollPane();
		scrollPendientes.setBounds(10, 262, 462, 107);
		add(scrollPendientes);
		
		tablaPendientes = new JTable(modeloP);
		scrollPendientes.setViewportView(tablaPendientes);
		
		lblPendientes = new JLabel("Reuniones pendientes");
		lblPendientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPendientes.setBounds(175, 237, 126, 14);
		add(lblPendientes);
	}
	public JButton getBtnAtras() {
		return btnAtras;
	}
	public JTable getTablaPendientes() {
		return tablaPendientes;
	}
	public JTable getTablaReuniones() {
		return tablaReuniones;
	}
	public DefaultTableModel getModeloR() {
		return modeloR;
	}
	public DefaultTableModel getModeloP() {
		return modeloP;
	}
}
