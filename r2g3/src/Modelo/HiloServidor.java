package Modelo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import Controlador.Controlador;

public class HiloServidor extends Thread {
	private Socket cli;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	String txtNombre;
	String txtContra;
	Session ses = HibernateUtil.getSessionFactory().openSession();
	Transaction tr;
	static String url="Centros-Lat-Lon.json";
	public static ArrayList<Centro> lista=new ArrayList<Centro>();

	public HiloServidor(Socket cliente) {
		cli = cliente;

	}

	@SuppressWarnings({ "deprecation" })
	public void run() {
		try {
			out = new ObjectOutputStream(cli.getOutputStream());
			in = new ObjectInputStream(cli.getInputStream());
			
			JsonParser parser = new JsonParser();
			try {
				FileReader fr = new FileReader(url);
				JsonElement datos = parser.parse(fr);
				Controlador.parserDatos(datos);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//lista=Cliente.centros;
			
			while (cli.isConnected()) {
				int accion = -1;
				try {
					accion = in.readInt();
				} catch (Exception e) {
				}
				switch (accion) {
				case 0: // login
					txtNombre = in.readUTF();
					txtContra = in.readUTF();
					login();
					break;
				case 1: // ver horario
					Users idprof = (Users) in.readObject();
					verHorario(idprof.getId());
					break;
				case 2: // ver otro horario
					String nomprof = in.readUTF();
					verOtroHorario(nomprof);
					break;
				case 21:// llenar combo profes
					llenarCombo();
					break;
				case 3: //ver reuniones (json)
					Users id = (Users) in.readObject();
					verReuniones(id.getId());
					break;
				case 31: //ver reuniones pendientes
					Users idp = (Users) in.readObject();
					verPendientes(idp.getId());
					break;
				default:
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void verPendientes(int id) {
		String qry = "from Reuniones where profesor_id=" + id +" and estado='pendiente'";
		Query q = ses.createQuery(qry);
		List<?> reuniones = q.list();
		String columnas[] = { "Estado","Titulo","Asunto","Fecha","Aula","Centro","Alumno","Aceptar","Rechazar"};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
		for (int i = 0; i < reuniones.size(); i++) {
			Reuniones actual=(Reuniones) reuniones.get(i);
			String estado=actual.getEstado();
			String titulo=actual.getTitulo();
			String asunto=actual.getAsunto();
			Timestamp fecha=actual.getFecha();
			String aula=actual.getAula();
			String centro=getNombreCentro(actual.getIdCentro());
			String alumno=actual.getUsersByAlumnoId().getNombre()+" "+actual.getUsersByAlumnoId().getApellidos();
			
			
			modelo.addRow(new Object[] {estado,titulo,asunto,String.valueOf(fecha),aula,centro,alumno,"Aceptar","Rechazar"});
		}
		try {
			out.writeObject(modelo);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void verReuniones(int id) {
		String qry = "from Reuniones where profesor_id=" + id +" and estado!='pendiente'";
		Query q = ses.createQuery(qry);
		List<?> reuniones = q.list();
		String columnas[] = { "Estado","Titulo","Asunto","Fecha","Aula","Centro","Alumno"};
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
		for (int i = 0; i < reuniones.size(); i++) {
			Reuniones actual=(Reuniones) reuniones.get(i);
			String estado=actual.getEstado();
			String titulo=actual.getTitulo();
			String asunto=actual.getAsunto();
			Timestamp fecha=actual.getFecha();
			String aula=actual.getAula();		
			String centro=getNombreCentro(actual.getIdCentro());
			String alumno=actual.getUsersByAlumnoId().getNombre()+" "+actual.getUsersByAlumnoId().getApellidos();
			modelo.addRow(new String[]{estado, titulo, asunto, String.valueOf(fecha),aula,centro,alumno});
		}
		try {
			out.writeObject(modelo);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getNombreCentro(String idcen) {
		for(int i=0;i<lista.size();i++) {
			if(lista.get(i).getIdCentro().equals(idcen)) {
				return lista.get(i).getNombre();
			}
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void llenarCombo() throws IOException {
		String qry = "from Users where tipo_id=3";
		Query q = ses.createQuery(qry);
		List<?> profesores = q.list();
		ArrayList<String> listaprofes = new ArrayList<String>();
		DefaultComboBoxModel<?> modelo;
		for (int i = 0; i < profesores.size(); i++) {
			Users actual = (Users) profesores.get(i);
			listaprofes.add(actual.getNombre() + " " + actual.getApellidos());
		}
		modelo = new DefaultComboBoxModel(listaprofes.toArray());
		out.writeObject(modelo);
		out.flush();
	}

	private void verOtroHorario(String nomprof) {
		String nom = nomprof.split(" ")[0];
		String profeq = "from Users where nombre='" + nom + "'";
		Query q1 = ses.createQuery(profeq);
		List<?> profenom = q1.list();
		Users profeSelec = (Users) profenom.get(0);
		String qry = "from Horarios where profe_id=" + profeSelec.getId();
		Query q = ses.createQuery(qry);
		List<?> horario = q.list();
		String columnas[] = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" };
		DefaultTableModel modelo = new DefaultTableModel(columnas, 5);
		for (int i = 0; i < horario.size(); i++) {
			Horarios temp = (Horarios) horario.get(i);
			HorariosId h = temp.getId();
			int dia = horarioDia(h);
			int hora = Character.getNumericValue(h.getHora()) - 1;
			String modulo = horarioModulo(h.getModuloId());
			modelo.setValueAt(modulo, hora, dia);
		}
		try {
			out.writeObject(modelo);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void verHorario(int idprof) {
		String qry = "from Horarios where profe_id=" + idprof;
		Query q = ses.createQuery(qry);
		List<?> horario = q.list();
		String columnas[] = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes" };
		DefaultTableModel modelo = new DefaultTableModel(columnas, 5);
		for (int i = 0; i < horario.size(); i++) {
			Horarios temp = (Horarios) horario.get(i);
			HorariosId h = temp.getId();
			int dia = horarioDia(h);
			int hora = Character.getNumericValue(h.getHora()) - 1;
			String modulo = horarioModulo(h.getModuloId());
			modelo.setValueAt(modulo, hora, dia);
		}
		try {
			out.writeObject(modelo);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String horarioModulo(int moduloId) {
		String qry = "from Modulos where id=" + moduloId;
		Query q = ses.createQuery(qry);
		List<?> modulo = q.list();
		Modulos temp = (Modulos) modulo.get(0);
		return temp.getNombre();
	}

	private int horarioDia(HorariosId h) {
		switch (h.getDia()) {
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
			return 0;
		}
	}

	private boolean login() {
		try {
			String qry = "from Users where tipo_id=3";
			Query q = ses.createQuery(qry);
			List<?> profesores = q.list();
			for (int i = 0; i < profesores.size(); i++) {
				Users temp = (Users) profesores.get(i);
				String conCorrecta;
				conCorrecta = Resumir(temp.getPassword());
				if (Resumir(temp.getUsername()).equals(txtNombre)) {
					String qry2 = "from Users where username='" + temp.getUsername() + "'";
					Query q2 = ses.createQuery(qry2);
					List<?> comprobar = q2.list();
					for (int o = 0; o < comprobar.size(); o++) {
						if (conCorrecta.equals(txtContra)) {
							out.writeBoolean(true);
							out.writeObject(temp);
							out.flush();
							// guardado();
							return true;
						}
						JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
					}
					out.writeBoolean(false);
					out.flush();
					return false;
				}
			}
			JOptionPane.showMessageDialog(null, "Usuario incorrecto.");
			out.writeBoolean(false);
			out.flush();
			return false;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unused")
	private void guardado() {
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

	public String Resumir(String frase) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA");
		byte bytes[] = frase.getBytes();
		md.update(bytes);
		byte resumen[] = md.digest();
		String resumenString = new String(resumen);
		return resumenString;
	}
}
