package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public class Cliente {
	private ObjectOutputStream out;
	private ObjectInputStream in;
	Socket cli;
	Users profe;

	public Cliente() {
		conectar();
	}

	public Socket getCli() {
		return cli;
	}

	public boolean login(String user, String con) {
		try {
			out.writeInt(0);
			out.writeUTF(user);
			out.writeUTF(con);
			out.flush();
			boolean result = in.readBoolean();
			if (result) {
				profe = (Users) in.readObject();
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;

	}

	public void conectar() {
		try {
			cli = new Socket("localhost", 4000);
			out = new ObjectOutputStream(cli.getOutputStream());
			in = new ObjectInputStream(cli.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public DefaultTableModel getHorario() {
		try {
			out.writeInt(1);
			out.writeObject(profe);
			out.flush();
			DefaultTableModel modelo = (DefaultTableModel) in.readObject();
			return modelo;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DefaultTableModel getOtroHorario(String nombre) {
		try {
			out.writeInt(2);
			out.writeUTF(nombre);
			out.flush();
			DefaultTableModel modelo = (DefaultTableModel) in.readObject();
			return modelo;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DefaultComboBoxModel<?> llenarComboProfes() {
		try {
			out.writeInt(21);
			out.flush();
			DefaultComboBoxModel<?> modelo = (DefaultComboBoxModel<?>) in.readObject();
			return modelo;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
