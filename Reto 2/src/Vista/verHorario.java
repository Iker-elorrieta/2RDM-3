package Vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class verHorario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private JButton btnVolver;
	private DefaultTableModel modelo;
	/**
	 * Create the panel.
	 */
	public verHorario() {
		setBounds(0, 0, 490, 400);
		setLayout(null);
		
		String columnas[] = { "Lunes","Martes","Miércoles","Jueves","Viernes"};
		modelo=new DefaultTableModel(columnas,0);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(383, 11, 89, 23);
		add(btnVolver);
		
		tabla = new JTable(modelo);
		tabla.setRowSelectionAllowed(false);
		tabla.setAutoCreateRowSorter(true);
		tabla.setColumnSelectionAllowed(false);
		tabla.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 83, 460, 232);
		add(scrollPane);
		scrollPane.setViewportView(tabla);
		
	}
	public JButton getBtnVolver() {
		return btnVolver;
	}
	public JTable getTabla() {
		return tabla;
	}
	public DefaultTableModel getModelo() {
		return modelo;
	}
}
