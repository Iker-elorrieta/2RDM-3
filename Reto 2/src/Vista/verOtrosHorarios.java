package Vista;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class verOtrosHorarios extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabla;
	private JButton btnAtras;
	private JComboBox<String> comboProfes;
	private DefaultTableModel modelo;

	/**
	 * Create the panel.
	 */
	public verOtrosHorarios() {
		setBounds(0, 0, 500, 400);
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 462, 266);
		add(scrollPane);
		
		String columnas[] = { "Lunes","Martes","Miércoles","Jueves","Viernes"};
		modelo=new DefaultTableModel(columnas,0);
		
		tabla = new JTable(modelo);
		tabla.setRowSelectionAllowed(false);
		tabla.setAutoCreateRowSorter(true);
		tabla.setColumnSelectionAllowed(false);
		scrollPane.setViewportView(tabla);
		
		btnAtras = new JButton("Volver");
		btnAtras.setBounds(383, 11, 89, 23);
		add(btnAtras);
		
		comboProfes = new JComboBox<String>();
		comboProfes.setBounds(10, 46, 150, 22);
		add(comboProfes);
	}

	public JTable getTabla() {
		return tabla;
	}

	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}

	public JComboBox<String> getComboProfes() {
		return comboProfes;
	}

	public void setComboProfes(JComboBox<String> comboProfes) {
		this.comboProfes = comboProfes;
	}

	public DefaultTableModel getModelo() {
		return modelo;
	}

	public void setModelo(DefaultTableModel modelo) {
		this.modelo = modelo;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}
}
