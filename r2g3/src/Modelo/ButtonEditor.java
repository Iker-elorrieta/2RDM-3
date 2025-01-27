package Modelo;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import Vista.Ventanas;

public class ButtonEditor implements TableCellRenderer,TableCellEditor {
/** The cell's row. */
protected static int row;

/** The cell's column. */
protected int column;

/** The cell's column. */
protected JTable table;

/** The button we are editing. */
public JButton button;

/** The panel used when editing. */
protected JPanel panel = new JPanel(new GridBagLayout());

ActionListener listener;

/** Constructor 
 * @param controlador */
public ButtonEditor(String tipo, ActionListener controlador) {
	if(tipo.contains("Acep")) {
		button=new JButton("Aceptar");
	}else {
		button=new JButton("Rechazar");
	}
	listener=controlador;
}
@Override
public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 
    ButtonEditor.row = row;
    this.column = column;
    this.table = table;
    button = (JButton) value;

    if(button.getText().contains("Acep")) {
		button.setActionCommand(Ventanas.enumAcciones.ACEPTAR_REUNION.toString());
	}else {
		button.setActionCommand(Ventanas.enumAcciones.RECHAZAR_REUNION.toString());
	}
    	
    button.addActionListener(listener);
    panel.add(button);
    panel.setBackground(table.getGridColor());
    return panel;
}

public static TableCellRenderer getRenderer() {
    return new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
          
            
            if(value instanceof Component)
                return (Component)value;
                return this.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    };
}
@Override
public Object getCellEditorValue() {
	return null;
}
@Override
public boolean isCellEditable(EventObject anEvent) {
	return true;
}
@Override
public boolean shouldSelectCell(EventObject anEvent) {
	return false;
}
@Override
public boolean stopCellEditing() {
	return false;
}
@Override
public void cancelCellEditing() {
	
}
@Override
public void addCellEditorListener(CellEditorListener l) {
	
}
@Override
public void removeCellEditorListener(CellEditorListener l) {
	
}
@Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
		int row, int column) {
	return button;
}
public static int getRow() {
	return row;
}
}