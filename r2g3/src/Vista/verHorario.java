package Vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Font;

public class verHorario extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private JButton btnVolver;
	private DefaultTableModel modelo;
	/**
	 * Create the panel.
	 */
	public verHorario() {
		setBounds(0, 0, 900, 700);
		setLayout(null);
		
		String columnas[] = { "Lunes","Martes","Miércoles","Jueves","Viernes"};
		modelo=new DefaultTableModel(columnas,0);
		
		btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(760, 30, 103, 33);
		add(btnVolver);
		
		tabla = new JTable(modelo);
		tabla.setRowSelectionAllowed(false);
		tabla.setAutoCreateRowSorter(true);
		tabla.setColumnSelectionAllowed(false);
		tabla.setAutoCreateColumnsFromModel(false);
		tabla.setEnabled(false);
		tabla.getColumnModel().getColumn(0).setPreferredWidth(300);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(300);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 189, 853, 286);
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
