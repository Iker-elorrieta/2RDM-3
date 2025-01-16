package Controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Modelo.Ciclos;
import Modelo.HibernateUtil;
import Modelo.Users;
import Vista.VentanasR2;

public class Servidor {

	static Session ses = HibernateUtil.getSessionFactory().openSession();
	static Transaction tr;
	private static DataOutputStream out;
	static String txtNombre;
	static String txtContra;

	public static void main(String[] args) {
		try {
			ServerSocket serv = new ServerSocket(4000);
			Socket cli = serv.accept();
			DataInputStream recivido = new DataInputStream(cli.getInputStream());
			out = new DataOutputStream(cli.getOutputStream());
			txtNombre = recivido.readUTF();
			txtContra = recivido.readUTF();
			login();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean login() {
		try {
			String con = Resumir(txtContra);
			String qry = "from Users where tipo_id=" + 3;
			Query q = ses.createQuery(qry);
			List<?> profesores = q.list();
			for (int i = 0; i < profesores.size(); i++) {
				Users temp = (Users) profesores.get(i);
				String conCorrecta;
				conCorrecta = Resumir(temp.getPassword());
				if (temp.getUsername().equals(txtNombre)) {
					String qry2 = "from Users where username='" + temp.getUsername() + "'";
					Query q2 = ses.createQuery(qry2);
					List<?> comprobar = q2.list();
					for (int o = 0; o < comprobar.size(); o++) {
						if (conCorrecta.equals(con)) {
							// enviar objeto profe
							// profeActual=temp;
							out.writeBoolean(true);
							guardado();
							return true;
						}
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
					}
					return false;
				}
			}
			JOptionPane.showMessageDialog(null, "Usuario incorrecto.");
			return false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void guardado() {
		try {
			tr = ses.beginTransaction();
			Ciclos nuevo = new Ciclos();
			nuevo.setId(6);
			nuevo.setNombre("CICLO");
			ses.save(nuevo);
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String Resumir(String frase) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA");
		byte bytes[] = frase.getBytes();
		md.update(bytes);
		byte resumen[] = md.digest();
		String resumenString = new String(resumen);
		return resumenString;
	}
}
