package Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Controlador.Controlador;
import Modelo.HibernateUtil;
import Modelo.Users;

public class Cliente {
	private static DataOutputStream out;
	private static DataInputStream in;
	private ObjectInputStream objin;
	Socket cli;
	Users profe;
	public Cliente() {
		conectar();
	}

	public boolean login(String user,String con) {
		try {
			out.writeInt(0);
			out.writeUTF(user);
			out.writeUTF(con);
			profe=(Users) objin.readObject();
			boolean result=in.readBoolean();
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
		cli= new Socket("localhost", 4000);
		out=new DataOutputStream(cli.getOutputStream());
		in=new DataInputStream(cli.getInputStream());
		objin=new ObjectInputStream(cli.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public DefaultTableModel getHorario() {
		try {
			out.writeInt(1);
			out.writeInt(profe.getId());
			DefaultTableModel modelo=(DefaultTableModel) objin.readObject();
			return modelo;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
