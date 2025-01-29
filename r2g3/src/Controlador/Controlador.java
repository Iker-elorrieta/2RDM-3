package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import Modelo.ButtonEditor;
import Modelo.Centro;
import Modelo.Cliente;
import Modelo.HiloServidor;
import Vista.Ventanas;

public class Controlador implements ActionListener {

	private Ventanas vista;
	static boolean get = false;
	static int dato = -1;
	static String nombre;
	static String id;
	static String dir;
	Cliente metodos;

	public Controlador(Ventanas ventanaPrincipal, Cliente cliente) {
		vista = ventanaPrincipal;
		this.inicializarControlador();
		metodos = cliente;
	}

	private void inicializarControlador() {
		// login
		this.vista.getPanelLogin().getBtnLogin().addActionListener(this);
		this.vista.getPanelLogin().getBtnLogin().setActionCommand(Ventanas.enumAcciones.BTN_LOGIN.toString());
		// menu
		this.vista.getPanelMenu().getBtnDesconectar().addActionListener(this);
		this.vista.getPanelMenu().getBtnDesconectar().setActionCommand(Ventanas.enumAcciones.DESCONECTAR.toString());
		this.vista.getPanelMenu().getBtnConsultarHorario().addActionListener(this);
		this.vista.getPanelMenu().getBtnConsultarHorario()
				.setActionCommand(Ventanas.enumAcciones.VER_HORARIO.toString());
		this.vista.getPanelMenu().getBtnOtrosHorarios().addActionListener(this);
		this.vista.getPanelMenu().getBtnOtrosHorarios()
				.setActionCommand(Ventanas.enumAcciones.OTROS_HORARIOS.toString());
		this.vista.getPanelMenu().getBtnReuniones().addActionListener(this);
		this.vista.getPanelMenu().getBtnReuniones().setActionCommand(Ventanas.enumAcciones.REUNIONES.toString());
		// panel horario
		this.vista.getPanelHorario().getBtnVolver().addActionListener(this);
		this.vista.getPanelHorario().getBtnVolver().setActionCommand(Ventanas.enumAcciones.CARGAR_MENU.toString());
		// panel otros horarios
		this.vista.getPanelOtrosHorarios().getBtnAtras().addActionListener(this);
		this.vista.getPanelOtrosHorarios().getBtnAtras().setActionCommand(Ventanas.enumAcciones.CARGAR_MENU.toString());

		this.vista.getPanelOtrosHorarios().getComboProfes().addActionListener(this);
		this.vista.getPanelOtrosHorarios().getComboProfes()
				.setActionCommand(Ventanas.enumAcciones.SELECCIONAR_PROFE.toString());
		// panel reuniones
		this.vista.getPanelReuniones().getBtnAtras().addActionListener(this);
		this.vista.getPanelReuniones().getBtnAtras().setActionCommand(Ventanas.enumAcciones.CARGAR_MENU.toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Ventanas.enumAcciones accion = Ventanas.enumAcciones.valueOf(e.getActionCommand());
		switch (accion) {
		case BTN_LOGIN:
			try {
				boolean resultado = loginComprobar(this.vista.getPanelLogin().getUser().getText(),
						String.valueOf(this.vista.getPanelLogin().getContra().getPassword()));
				if (resultado)
					vista.mVisualizarPaneles(Ventanas.enumAcciones.CARGAR_MENU);
			} catch (NoSuchAlgorithmException | IOException e1) {
				e1.printStackTrace();
			}
			break;
		case DESCONECTAR:
			desconectar();
			break;
		case VER_HORARIO:
			mostrarHorario();
			this.vista.mVisualizarPaneles(Ventanas.enumAcciones.VER_HORARIO);
			break;
		case CARGAR_MENU:
			this.vista.mVisualizarPaneles(accion);
			break;
		case OTROS_HORARIOS:
			this.vista.mVisualizarPaneles(Ventanas.enumAcciones.OTROS_HORARIOS);
			llenarComboProfes();
			break;
		case REUNIONES:
			mostrarReuniones();
			this.vista.mVisualizarPaneles(Ventanas.enumAcciones.REUNIONES);
			break;
		case SELECCIONAR_PROFE:
			selectProfe();
			break;
		case ACEPTAR_REUNION:
			aceptarReunion(ButtonEditor.getRow());
			break;
		case RECHAZAR_REUNION:
			rechazarReunion(ButtonEditor.getRow());
			break;
		default:
		}
	}

	private void rechazarReunion(int row) {
		metodos.rechazarReunion(vista.getPanelReuniones().getTablaPendientes().getValueAt(row, 1).toString(),vista.getPanelReuniones().getTablaPendientes().getValueAt(row, 0).toString());
		this.mostrarReuniones();
	}

	private void aceptarReunion(int row) {
		metodos.aceptarReunion(vista.getPanelReuniones().getTablaPendientes().getValueAt(row, 1).toString(),vista.getPanelReuniones().getTablaPendientes().getValueAt(row, 0).toString());
		this.mostrarReuniones();
	}

	private void mostrarReuniones() {
		this.vista.getPanelReuniones().getTablaPendientes().removeAll();
		this.vista.getPanelReuniones().getTablaPendientes().removeEditor();
		this.vista.getPanelReuniones().getTablaReuniones().removeAll();
		
		
		DefaultTableModel modelo = metodos.getReuniones();
		vista.getPanelReuniones().getTablaReuniones().setModel(modelo);

		DefaultTableModel pendientes = metodos.getPendientes();
		vista.getPanelReuniones().getTablaPendientes().setModel(pendientes);

		ButtonEditor aceptar=new ButtonEditor("Aceptar", this);
		ButtonEditor rechazar=new ButtonEditor("Rechazar", this);
		
		vista.getPanelReuniones().getTablaPendientes().getColumn("Aceptar")
				.setCellEditor(aceptar);
		vista.getPanelReuniones().getTablaPendientes().getColumn("Aceptar")
				.setCellRenderer(aceptar.getRenderer());

		vista.getPanelReuniones().getTablaPendientes().getColumn("Rechazar")
				.setCellEditor(rechazar);
		vista.getPanelReuniones().getTablaPendientes().getColumn("Rechazar")
				.setCellRenderer(rechazar.getRenderer());

	}

	@SuppressWarnings("unchecked")
	private void llenarComboProfes() {
		DefaultComboBoxModel<?> modelo = metodos.llenarComboProfes();
		vista.getPanelOtrosHorarios().getComboProfes().setModel((ComboBoxModel<String>) modelo);
	}

	private void selectProfe() {
		DefaultTableModel modelo = metodos
				.getOtroHorario(vista.getPanelOtrosHorarios().getComboProfes().getSelectedItem().toString());
		vista.getPanelOtrosHorarios().getTabla().setModel(modelo);
	}

	private void mostrarHorario() {
		DefaultTableModel modelo = metodos.getHorario();
		vista.getPanelHorario().getTabla().setModel(modelo);
	}

	private boolean loginComprobar(String usuario, String contra) throws NoSuchAlgorithmException, IOException {
		if (metodos.login(Resumir(usuario), Resumir(contra))) {
			return true;
		}
		return false;
	}

	private void desconectar() {
		this.vista.getPanelLogin().getUser().setText(null);
		this.vista.getPanelLogin().getContra().setText(null);
		this.vista.mVisualizarPaneles(Ventanas.enumAcciones.CARGAR_LOGIN);
	}

	public String Resumir(String frase) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA");
		byte bytes[] = frase.getBytes();
		md.update(bytes);
		byte resumen[] = md.digest();
		String resumenString = new String(resumen);
		return resumenString;
	}

	public static void parserDatos(JsonElement datos) {
		if (datos.isJsonArray()) {
			JsonArray array = datos.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				parserDatos(entrada);
			}

		} else if (datos.isJsonObject()) {
			JsonObject objeto = datos.getAsJsonObject();
			Iterator<Map.Entry<String, JsonElement>> iter2 = objeto.entrySet().iterator();
			while (iter2.hasNext()) {
				Map.Entry<String, JsonElement> ent = iter2.next();
				JsonElement valor = ent.getValue();
				if (ent.getKey().equals("CCEN")) {
					get = true;
					dato = 1;
				} else if (ent.getKey().equals("NOM")) {
					get = true;
					dato = 2;
				} else if (ent.getKey().equals("DOMI")) {
					get = true;
					dato = 3;
				}
				parserDatos(valor);
			}
		} else if (datos.isJsonPrimitive()) {
			JsonPrimitive primi = datos.getAsJsonPrimitive();
			if (primi.isString()) {
				if (get) {
					if (dato == 2) {
						nombre = primi.getAsString();
					} else if (dato == 3) {
						dir = primi.getAsString();
						HiloServidor.lista.add(new Centro(id, nombre, dir));
					}
					get = false;
				}
			} else if (primi.isNumber()) {
				if (dato == 1) {
					id = String.valueOf(primi.getAsNumber());
				}
			} else if (primi.isBoolean()) {
			}
		} else if (datos.isJsonNull()) {
		}
	}

}
