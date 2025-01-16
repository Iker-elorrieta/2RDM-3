package Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Modelo.HibernateUtil;
import Modelo.Users;

public class Cliente {
	private DataOutputStream out;
	private DataInputStream in;
	Socket cli;
	public Cliente() {
		conectar();
	}

	public boolean login(String user,String con) {
		try {
			out=new DataOutputStream(cli.getOutputStream());
			in=new DataInputStream(cli.getInputStream());
			out.writeUTF(user);
			out.writeUTF(con);
			boolean result=in.readBoolean();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	public void conectar() {
		try {
		cli= new Socket("localhost", 4000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
