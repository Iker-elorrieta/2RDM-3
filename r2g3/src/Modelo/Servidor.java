package Modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		try {
			ServerSocket serv = new ServerSocket(4000);
			while (!serv.isClosed()) {
				Socket cli = serv.accept();
				new HiloServidor(cli).start();
			}
			serv.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
