package entities;
import javax.persistence.*;

import dto.ItemFacturaDTO;
import negocio.Comanda;
import negocio.ItemFactura;

@Entity
@Table(name="itemsfactura")
public class ItemFacturaEntity 
{
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer codItemFactura; 
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="codItemComanda")
	private ItemComandaEntity itemcomanda; 
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="codFactura")
	private FacturaEntity factura;
	private double subtotal;
	
	public ItemFacturaEntity() {
		super();
	}
	
	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public ItemFacturaEntity(ItemComandaEntity itemcomanda, FacturaEntity factura) {
		super();
		this.itemcomanda = itemcomanda;
		this.factura=factura;
	}

	public Integer getCodItemFactura() {
		return codItemFactura;
	}

	public void setCodItemFactura(Integer codItemFactura) {
		this.codItemFactura = codItemFactura;
	}
	public ItemComandaEntity getItemcomanda() {
		return itemcomanda;
	}

	public void setItemcomanda(ItemComandaEntity itemcomanda) {
		this.itemcomanda = itemcomanda;
	}

	public FacturaEntity getFactura() {
		return factura;
	}

	public void setFactura(FacturaEntity factura) {
		this.factura = factura;
	}

	public ItemFactura tonegocio()
	{ 
		ItemFactura itemnetocio=new ItemFactura();
		itemnetocio.setCodItemFactura(codItemFactura);
		itemnetocio.setFactura(factura.toNegocio());
		itemnetocio.setItemcomanda(itemcomanda.toNegocio());
		itemnetocio.setSubtotal(subtotal);
		return itemnetocio;
	}
	
	
	
}
