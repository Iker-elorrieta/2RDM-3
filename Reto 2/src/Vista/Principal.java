package Vista;

import Conexion.Cliente;
import Controlador.Controlador;
import Controlador.Servidor;

public class Principal {

	public static void main(String[] args) {
		try {

			// Creamos el objeto vista.
			VentanasR2 ventanaPrincipal = new VentanasR2();
			ventanaPrincipal.setVisible(true);

			// Creamos en controlador con acceso al modelo y la vista
			new Controlador(ventanaPrincipal);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
