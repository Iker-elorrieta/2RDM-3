package Vista;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

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
		setBounds(0, 0, 900, 700);
		setLayout(null);

		btnAtras = new JButton("Volver");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtras.setBounds(764, 11, 104, 35);
		add(btnAtras);

		String columnas[] = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" };
		modeloR = new DefaultTableModel(columnas, 5);

		String columnas2[] = { "Estado", "Titulo", "Asunto", "Fecha", "Aula", "Centro", "Alumno", "Aceptar",
				"Rechazar" };
		modeloP = new DefaultTableModel(columnas2, 0);

		scrollReuniones = new JScrollPane();
		scrollReuniones.setBounds(31, 125, 837, 181);
		add(scrollReuniones);

		tablaReuniones = new JTable(modeloR);

		tablaReuniones.getColumnModel().getColumn(0).setCellRenderer(new CellRenderer());
		tablaReuniones.getColumnModel().getColumn(1).setCellRenderer(new CellRenderer());
		tablaReuniones.getColumnModel().getColumn(2).setCellRenderer(new CellRenderer());
		tablaReuniones.getColumnModel().getColumn(3).setCellRenderer(new CellRenderer());
		tablaReuniones.getColumnModel().getColumn(4).setCellRenderer(new CellRenderer());
		
		
		tablaReuniones.setEnabled(false);
		tablaReuniones.setAutoCreateColumnsFromModel(false);
		tablaReuniones.setRowSelectionAllowed(false);
		tablaReuniones.setAutoCreateRowSorter(true);
		tablaReuniones.setColumnSelectionAllowed(false);
		tablaReuniones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaReuniones.getColumnModel().getColumn(0).setPreferredWidth(200);
		tablaReuniones.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablaReuniones.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablaReuniones.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablaReuniones.getColumnModel().getColumn(4).setPreferredWidth(200);
		scrollReuniones.setViewportView(tablaReuniones);

		scrollPendientes = new JScrollPane();
		scrollPendientes.setBounds(31, 377, 837, 167);
		add(scrollPendientes);

		tablaPendientes = new JTable(modeloP);
		tablaPendientes.setRowSelectionAllowed(false);
		tablaPendientes.setAutoCreateRowSorter(true);
		tablaPendientes.setColumnSelectionAllowed(false);
		tablaPendientes.setAutoCreateColumnsFromModel(false);
		tablaPendientes.getColumnModel().getColumn(0).setPreferredWidth(60);
		tablaPendientes.getColumnModel().getColumn(1).setPreferredWidth(150);
		tablaPendientes.getColumnModel().getColumn(3).setPreferredWidth(150);
		tablaPendientes.getColumnModel().getColumn(4).setPreferredWidth(60);
		tablaPendientes.getColumnModel().getColumn(5).setPreferredWidth(180);
		tablaPendientes.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablaPendientes.getColumnModel().getColumn(7).setPreferredWidth(90);
		tablaPendientes.getColumnModel().getColumn(8).setPreferredWidth(90);
		tablaPendientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPendientes.setViewportView(tablaPendientes);

		lblPendientes = new JLabel("Reuniones pendientes");
		lblPendientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblPendientes.setBounds(389, 352, 126, 14);
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

	public class CellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int col) {
			JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

			if (l.getText().toString().contains("aceptada")) {
				l.setBackground(Color.GREEN);
			} else if(l.getText().toString().contains("denegada")){
				l.setBackground(Color.RED);
			}else {
				l.setBackground(Color.WHITE);
			}
			return l;
		};
	}
}
