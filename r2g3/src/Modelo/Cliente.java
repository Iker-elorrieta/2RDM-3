package Modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Controlador.Controlador;
import Vista.Ventanas;

public class Cliente {
	ObjectOutputStream out;
	ObjectInputStream in;
	Users profe;
	Socket cli;

	public Cliente() {
		try {
			cli = new Socket("localhost", 4000);
			out = new ObjectOutputStream(cli.getOutputStream());
			in = new ObjectInputStream(cli.getInputStream());

			Ventanas ventanaPrincipal = new Ventanas();
			ventanaPrincipal.setVisible(true);
			new Controlador(ventanaPrincipal, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			out.writeUTF(profe.getNombre());
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

	public DefaultTableModel getReuniones() {
		try {
			out.writeInt(3);
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

	public DefaultTableModel getPendientes() {
		try {
			out.writeInt(31);
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

	public void aceptarReunion(String titulo, String tipo) {
		try {
			out.writeInt(41);
			out.writeUTF(titulo);
			out.writeUTF(tipo);
			out.writeInt(profe.getId());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void rechazarReunion(String string, String tipo) {
		try {
			out.writeInt(42);
			out.writeUTF(string);
			out.writeUTF(tipo);
			out.writeInt(profe.getId());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
