package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Vista.VentanasR2;
import Modelo.HibernateUtil;
import Modelo.Tipos;
import Modelo.Users;

public class Controlador implements ActionListener {

	private VentanasR2 vista;
	Session ses = HibernateUtil.getSessionFactory().openSession();
	Transaction tr = ses.beginTransaction();
	private int tipo = -1;

	public Controlador(VentanasR2 ventanaPrincipal) {
		this.vista = ventanaPrincipal;
		this.inicializarControlador();
		String qry = "from Tipos where name='profesor'";
		Query q = ses.createQuery(qry);
		List<?> tipoQ = q.list();
		for (int i = 0; i < tipoQ.size(); i++) {
			Tipos temp = (Tipos) tipoQ.get(i);
			tipo = temp.getId();
		}
	}

	private void inicializarControlador() {
		this.vista.getPanelLogin().getBtnLogin().addActionListener(this);
		this.vista.getPanelLogin().getBtnLogin().setActionCommand(VentanasR2.enumAcciones.BTN_LOGIN.toString());
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		VentanasR2.enumAcciones accion = VentanasR2.enumAcciones.valueOf(e.getActionCommand());

		switch (accion) {
		case BTN_LOGIN:
			try {
				if(loginComprobar(this.vista.getPanelLogin().getUser().getText(),
						String.valueOf(this.vista.getPanelLogin().getContra().getPassword()))) {
					vista.mVisualizarPaneles(VentanasR2.enumAcciones.CARGAR_MENU);
				}
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			break;
		default:
		}
	}

	private boolean loginComprobar(String usuario, String contra) throws NoSuchAlgorithmException {
		String con = Resumir(contra);
		String qry = "from Users where tipo_id=" + tipo;
		Query q = ses.createQuery(qry);
		List<?> profesores = q.list();
		for (int i = 0; i < profesores.size(); i++) {
			Users temp = (Users) profesores.get(i);
			String conCorrecta = Resumir(temp.getPassword());

			if (temp.getUsername().equals(usuario)) {
				String qry2 = "from Users where username='" + temp.getUsername() + "'";
				Query q2 = ses.createQuery(qry2);
				List<?> comprobar = q2.list();
				for (int o = 0; o < comprobar.size(); o++) {
					if (conCorrecta.equals(con)) {
						//PASAR AL SIGUIENTE
						return true;
					}
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
				}
				return false;
			}
		}
		JOptionPane.showMessageDialog(null, "Usuario incorrecto.");
		return false;
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
