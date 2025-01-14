package Controlador;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Modelo.HibernateUtil;
import Vista.VentanasR2;

public class Servidor {

	Session ses = HibernateUtil.getSessionFactory().openSession();
	Transaction tr = ses.beginTransaction();

	public static void main(String[] args) throws IOException {
		ServerSocket serv = new ServerSocket(4000);
		Socket cli = serv.accept();
		DataInputStream in = new DataInputStream(cli.getInputStream());
		while (cli.isConnected()) {
			String op = "";
			try {
				op = in.readUTF();
			} catch (Exception e) {
			}
			switch (op) {
			case "":
				break;
			default:
			}

		}
	}

}
