package entities;

import java.util.Date;

import javax.persistence.*;

import enumns.EstadoSolicitud;
import negocio.SolicitudIndividual;

@Entity
@Table(name="solicitudIndividual")
public class SolicitudIndividualEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="codsolicitudIndividual")
	private Integer codsolicitudIndividual;
	
    @ManyToOne
    @JoinColumn(name="codArea")
	protected AreaRestaurantEntity area;
    private EstadoSolicitud estado;
	protected String responsable;
	protected Integer lote;
	protected Date fechaCompra;
	protected Date fechaVencimiento;
	protected String motivo;
	@OneToOne
	@JoinColumn(name="codigo")
	private  MateriaPrimaEntity materiaprima;
	

	private float cantidad;


	public SolicitudIndividualEntity(AreaRestaurantEntity area, String responsable,
			Integer lote, Date fechaCompra, Date fechaVencimiento, String motivo, MateriaPrimaEntity materiaprima, float cantidad, EstadoSolicitud estado) {
		super();
		this.area = area;
		this.estado=estado;
		this.responsable = responsable;
		this.lote = lote;
		this.fechaCompra = fechaCompra;
		this.fechaVencimiento = fechaVencimiento;
		this.motivo = motivo;
		this.materiaprima = materiaprima;
		this.cantidad = cantidad;
	}



	public SolicitudIndividualEntity() {
	}



	public EstadoSolicitud getEstado() {
		return estado;
	}



	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}



	public AreaRestaurantEntity getArea() {
		return area;
	}



	public void setArea(AreaRestaurantEntity area) {
		this.area = area;
	}
	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}



	public Integer getCodsolicitudIndividual() {
		return codsolicitudIndividual;
	}



	public void setCodsolicitudIndividual(Integer codsolicitudIndividual) {
		this.codsolicitudIndividual = codsolicitudIndividual;
	}


	public Integer getLote() {
		return lote;
	}



	public void setLote(Integer lote) {
		this.lote = lote;
	}



	public Date getFechaCompra() {
		return fechaCompra;
	}



	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}



	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}



	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}



	public String getMotivo() {
		return motivo;
	}



	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}



	public MateriaPrimaEntity getMateriaprima() {
		return materiaprima;
	}



	public void setMateriaprima(MateriaPrimaEntity materiaprima) {
		this.materiaprima = materiaprima;
	}



	public float getCantidad() {
		return cantidad;
	}



	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}



	public SolicitudIndividual toNegocio() {
		SolicitudIndividual s=new SolicitudIndividual();
		s.setArea(area.toNegocio());
		s.setCantidad(cantidad);
		s.setCodsolicitudIndividual(codsolicitudIndividual);
		s.setFechaCompra(fechaCompra);
		s.setFechaVencimiento(fechaVencimiento);
		s.setLote(lote);
		s.setMateriaprima(materiaprima.toNegocio());
		s.setMotivo(motivo);
		s.setResponsable(responsable);
		s.setEstado(estado);
		return s;
	}
	
	
}