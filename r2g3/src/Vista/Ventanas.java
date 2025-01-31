package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class Ventanas extends JFrame{

	private static final long serialVersionUID = 1L;
	public static enum enumAcciones{
		CARGAR_LOGIN,
		BTN_LOGIN,
		CARGAR_MENU,
		DESCONECTAR,
		VER_HORARIO,
		OTROS_HORARIOS,
		SELECCIONAR_PROFE,
		REUNIONES,
		ACEPTAR_REUNION,
		RECHAZAR_REUNION
	}
	//paneles declaraciones
	private JPanel panelContenedor;
	private login panelLogin;
	private menu panelMenu;
	private verHorario panelHorario;
	private verOtrosHorarios panelOtrosHorarios;
	private verReuniones panelReuniones;
	
	
	//crear paneles
	public Ventanas() {
		mCrearPanelContenedor();
		mCrearPanelMenu();
		mCrearPanelHorario();
		mCrearPanelOtrosHorarios();
		mCrearPanelReuniones();
		mCrearPanelLogin();
	}
	
	private void mCrearPanelReuniones() {
		panelReuniones=new verReuniones();
		panelContenedor.add(panelReuniones);
	}

	private void mCrearPanelOtrosHorarios() {
		panelOtrosHorarios=new verOtrosHorarios();
		panelContenedor.add(panelOtrosHorarios);	
	}

	private void mCrearPanelHorario() {
		panelHorario=new verHorario();
		panelContenedor.add(panelHorario);	
	}

	private void mCrearPanelContenedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, 900, 700);
		panelContenedor = new JPanel();
		panelContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenedor);
		panelContenedor.setLayout(null);
	}

	private void mCrearPanelLogin() {
		panelLogin=new login();
		panelContenedor.add(panelLogin);
		mVisualizarPaneles(enumAcciones.CARGAR_LOGIN);
	}

	private void mCrearPanelMenu() {
		panelMenu=new menu();
		panelContenedor.add(panelMenu);
	}
	//visualizar paneles
	public void mVisualizarPaneles(enumAcciones panel) {
		panelLogin.setVisible(false);
		panelMenu.setVisible(false);
		panelHorario.setVisible(false);
		panelOtrosHorarios.setVisible(false);
		panelReuniones.setVisible(false);
		
		switch (panel) {
		case CARGAR_LOGIN:
			panelLogin.setVisible(true);
			break;
		case CARGAR_MENU:
			panelMenu.setVisible(true);
			break;
		case VER_HORARIO:
			panelHorario.setVisible(true);
			break;
		case OTROS_HORARIOS:
			panelOtrosHorarios.setVisible(true);
			break;
		case REUNIONES:
			panelReuniones.setVisible(true);
			break;
		default:
		}
	}
	//getters
	public login getPanelLogin() {
		return panelLogin;
	}
	
	public menu getPanelMenu() {
		return panelMenu;
	}
	
	public verHorario getPanelHorario() {
		return panelHorario;
	}
	
	public verOtrosHorarios getPanelOtrosHorarios() {
		return panelOtrosHorarios;
	}
	
	public verReuniones getPanelReuniones() {
		return panelReuniones;
	}
}
