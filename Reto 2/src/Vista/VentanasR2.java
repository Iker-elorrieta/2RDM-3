package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanasR2 extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static enum enumAcciones{
		CARGAR_LOGIN,
		BTN_LOGIN,
		CARGAR_MENU,
		DESCONECTAR,
		VER_HORARIO,
		OTROS_HORARIOS,
		REUNIONES
	}
	
	private JPanel panelContenedor;
	private login panelLogin;
	private menu panelMenu;
	
	public VentanasR2() {
		mCrearPanelContenedor();
		mCrearPanelMenu();
		mCrearPanelLogin();
	}
	
	

	private void mCrearPanelContenedor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 150, 500, 400);
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
	
	public void mVisualizarPaneles(enumAcciones panel) {
		panelLogin.setVisible(false);
		panelMenu.setVisible(false);
		
		switch (panel) {
		case CARGAR_LOGIN:
			panelLogin.setVisible(true);
			break;
		case CARGAR_MENU:
			panelMenu.setVisible(true);
		default:
		}
	}


	public login getPanelLogin() {
		return panelLogin;
	}
	
	public menu getPanelMenu() {
		return panelMenu;
	}
	
}
