package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Modelo.Cliente;
import Vista.Ventanas;

public class Controlador implements ActionListener{

	private Ventanas vista;
	
	public Controlador(Ventanas ventanaPrincipal) {
		vista=ventanaPrincipal;
		this.inicializarControlador();
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
		this.vista.getPanelOtrosHorarios().getBtnAtras()
				.setActionCommand(Ventanas.enumAcciones.CARGAR_MENU.toString());

		this.vista.getPanelOtrosHorarios().getComboProfes().addActionListener(this);
		this.vista.getPanelOtrosHorarios().getComboProfes()
				.setActionCommand(Ventanas.enumAcciones.SELECCIONAR_PROFE.toString());
		// panel reuniones
		this.vista.getPanelReuniones().getBtnAtras().addActionListener(this);
		this.vista.getPanelReuniones().getBtnAtras().setActionCommand(Ventanas.enumAcciones.CARGAR_MENU.toString());
		
		//botones rechazar/aceptar
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
			this.vista.mVisualizarPaneles(Ventanas.enumAcciones.REUNIONES);
			break;
		case SELECCIONAR_PROFE:
			selectProfe();
			break;
		default:
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void llenarComboProfes() {
		DefaultComboBoxModel<?> modelo = Cliente.llenarComboProfes();
		vista.getPanelOtrosHorarios().getComboProfes().setModel((ComboBoxModel<String>) modelo);
	}

	private void selectProfe() {
		DefaultTableModel modelo = Cliente
				.getOtroHorario(vista.getPanelOtrosHorarios().getComboProfes().getSelectedItem().toString());
		vista.getPanelOtrosHorarios().getTabla().setModel(modelo);
	}

	private void mostrarHorario() {
		DefaultTableModel modelo = Cliente.getHorario();
		vista.getPanelHorario().getTabla().setModel(modelo);
	}

	private boolean loginComprobar(String usuario, String contra) throws NoSuchAlgorithmException, IOException {
		if (Cliente.login(Resumir(usuario), Resumir(contra))) {
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

}
