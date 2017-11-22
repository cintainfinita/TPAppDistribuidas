package entities;

import java.util.Date;
import javax.persistence.*;

import negocio.Reserva;

@Entity
@Table(name="reservas")
public class ReservaEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codReserva;
	private String nombre;
	private Date fechayHora;
	//private List<MesaDTO> mesas;
	
	public ReservaEntity() {}
	
	public ReservaEntity(Integer codReserva, String nombre, Date fechayHora) {
		super();
		this.codReserva = codReserva;
		this.nombre = nombre;
		this.fechayHora = fechayHora;
	}

	public Integer getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(Integer codReserva) {
		this.codReserva = codReserva;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechayHora() {
		return fechayHora;
	}

	public void setFechayHora(Date fechayHora) {
		this.fechayHora = fechayHora;
	}

	public Reserva toNegocio() 
	{
		Reserva r=new Reserva();
		r.setCodReserva(codReserva);
		r.setFechayHora(fechayHora);
		r.setNombre(nombre);
		return r;
	}
}