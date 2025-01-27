package Modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ServerSocket serv = new ServerSocket(4000);
			while (!serv.isClosed()) {
				Socket cli = serv.accept();
				// hilo
				new HiloServidor(cli).start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
