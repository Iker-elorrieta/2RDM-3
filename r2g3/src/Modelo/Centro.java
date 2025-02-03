package Modelo;

public class Centro {
	String idCentro;
	String nombre;
	String municipio;
	
	public Centro() {
		
	}

	public Centro(String id, String nombre2, String dir) {
		idCentro=id;
		nombre=nombre2;
		municipio=dir;
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

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String direccion) {
		this.municipio = direccion;
	}
}
