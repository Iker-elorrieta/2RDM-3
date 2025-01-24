package Modelo;

public class Centro {
	String idCentro;
	String nombre;
	String direccion;
	
	public Centro() {
		
	}

	public Centro(String id, String nombre2, String dir) {
		idCentro=id;
		nombre=nombre2;
		direccion=dir;
	}

	public String getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
