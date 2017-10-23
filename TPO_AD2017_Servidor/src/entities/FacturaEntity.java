package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import dto.ComandaDTO;
import dto.MesaDTO;
import dto.MozoDTO;
import dto.ItemFacturaDTO;
import enumns.MedioDePago;


@Entity
@Table(name="facturas")
public class FacturaEntity 
{
	@Id
	@Column(name="codFactura")
	private Integer codFactura;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="codComanda")
	private List<ComandaEntity> comandas;
	private Date fecha;
	private float importe;
	@Enumerated(EnumType.STRING)
	private MedioDePago medioPago;
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name="codItemFactura")
	private List<ItemFacturaEntity> itemFactura;
	@OneToOne
	@JoinColumn(name="codMesa")
	private MesaEntity mesa;
	@OneToOne
	@JoinColumn(name="dni")
	private MozoEntity mozo;
	
	public FacturaEntity(){}

	

	public FacturaEntity(Integer codFactura, List<ComandaEntity> comandas, Date fecha, float importe,
			MedioDePago medioPago, List<ItemFacturaEntity> itemFactura, MesaEntity mesa, MozoEntity mozo) {
		super();
		this.codFactura = codFactura;
		this.comandas = comandas;
		this.fecha = fecha;
		this.importe = importe;
		this.medioPago = medioPago;
		this.itemFactura = itemFactura;
		this.mesa = mesa;
		this.mozo = mozo;
	}

	public Integer getCodFactura() {
		return codFactura;
	}

	public void setCodFactura(Integer codFactura) {
		this.codFactura = codFactura;
	}

	public List<ComandaEntity> getComandas() {
		return comandas;
	}

	public void setComandas(List<ComandaEntity> comandas) {
		this.comandas = comandas;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getImporte() {
		return importe;
	}

	public void setImporte(float importe) {
		this.importe = importe;
	}

	public MedioDePago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioDePago medioPago) {
		this.medioPago = medioPago;
	}

	public List<ItemFacturaEntity> getItemFactura() {
		return itemFactura;
	}

	public void setItemFactura(List<ItemFacturaEntity> itemFactura) {
		this.itemFactura = itemFactura;
	}

	public MesaEntity getMesa() {
		return mesa;
	}

	public void setMesa(MesaEntity mesa) {
		this.mesa = mesa;
	}

	public MozoEntity getMozo() {
		return mozo;
	}

	public void setMozo(MozoEntity mozo) {
		this.mozo = mozo;
	}
}
