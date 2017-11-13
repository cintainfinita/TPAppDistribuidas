package negocio;

import java.util.List;


public class Sector 
{
	private Integer codSector;
	private String descripcion;
	private Salon salon;
	
	
	public Sector(){}

	public Sector(String descripcion,Salon salon) {
		super();
		
		this.descripcion = descripcion;
		this.salon=salon;
		//this.mozo = mozo;
		//this.mesa = mesas;
	}

	public Integer getCodSector() {
		return codSector;
	}

	public void setCodSector(Integer codSector) {
		this.codSector = codSector;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}
}
