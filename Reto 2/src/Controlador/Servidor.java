package Controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Modelo.Ciclos;
import Modelo.HibernateUtil;
import Modelo.Horarios;
import Modelo.HorariosId;
import Modelo.Modulos;
import Modelo.Users;

public class Servidor {

	static Session ses = HibernateUtil.getSessionFactory().openSession();
	static Transaction tr;
	static DataOutputStream out;
		static ObjectOutputStream objout;
	static String txtNombre;
	static String txtContra;

	public static void main(String[] args) {	
		
		try {
			ServerSocket serv = new ServerSocket(4000);
			while (!serv.isClosed()) {
				Socket cli = serv.accept();
				DataInputStream recivido = new DataInputStream(cli.getInputStream());
				objout=new ObjectOutputStream(cli.getOutputStream());
				out = new DataOutputStream(cli.getOutputStream());
				while (cli.isConnected()) {
					int accion = -1;
					try {
						accion = recivido.readInt();
					} catch (Exception e) {
					}
					switch (accion) {
					case 0: //login
						txtNombre = recivido.readUTF();
						txtContra = recivido.readUTF();
						login();
						break;
					case 1: //ver horario
						int idprof=recivido.readInt();
						verHorario(idprof);
						break;
					default:
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void verHorario(int idprof) {
		String qry="from Horarios where profe_id="+idprof;
		Query q = ses.createQuery(qry);
		List<?> horario = q.list();
		String columnas[] = { "Lunes","Martes","Miércoles","Jueves","Viernes"};
		DefaultTableModel modelo=new DefaultTableModel(columnas,5);
		for (int i = 0; i < horario.size(); i++) {
			Horarios temp=(Horarios) horario.get(i);
			HorariosId h=temp.getId();
			int dia=horarioDia(h);
			int hora=Integer.valueOf(h.getHora())-1;
			String modulo=horarioModulo(h.getModuloId());
			modelo.setValueAt(modulo, hora, dia);
		}
		try {
			objout.writeObject(modelo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String horarioModulo(int moduloId) {
		String qry="from Modulos where id="+moduloId;
		Query q = ses.createQuery(qry);
		List<?> modulo = q.list();
		Modulos temp=(Modulos) modulo.get(0);
		return temp.getNombre();
	}

	private static int horarioDia(HorariosId h) {
		switch(h.getDia()) {
		case "L/A":
			return 0;
		case "M/A":
			return 1;
		case "X":
			return 2;
		case "J/O":
			return 3;
		case "V/O":
			return 4;
		default:
		}
		return 0;
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
							objout.writeObject(temp);
							out.writeBoolean(true);
							//guardado();
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
