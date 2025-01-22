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
	static ObjectOutputStream out;
	static ObjectInputStream in;
	static Users profe;

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			Socket cli = new Socket("localhost", 4000);
			out = new ObjectOutputStream(cli.getOutputStream());
			in = new ObjectInputStream(cli.getInputStream());
			
			
			Ventanas ventanaPrincipal = new Ventanas();
			ventanaPrincipal.setVisible(true);
			new Controlador(ventanaPrincipal);
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean login(String user, String con) {
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
	
	public static DefaultTableModel getHorario() {
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

	public static DefaultTableModel getOtroHorario(String nombre) {
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

	public static DefaultComboBoxModel<?> llenarComboProfes() {
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
