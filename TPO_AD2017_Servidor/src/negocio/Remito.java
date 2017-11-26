package negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.ItemRemitoDTO;
import dto.RemitoDTO;
import entities.*;


public class Remito 
{
	private Integer codRemito;
	private Integer codigoProveedor;
	private Date fecha;
    private Deposito deposito;
	private List<ItemRemito> itemsRemito;
	
		
	public Remito(){}
		
	public Remito(Integer codigoProveedor, Date fecha,
			List<ItemRemito> itemsRemito
			) {
		super();
		this.codigoProveedor = codigoProveedor;
		this.fecha = fecha;
		this.itemsRemito = itemsRemito;
		//this.ordendeCompra = ordendeCompra;
	}
	
	public Deposito getDeposito() {
		return deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}
	public List<ItemRemito> getItemsRemito() {
		return itemsRemito;
	}

	public void setItemsRemito(List<ItemRemito> itemsRemito) {
		this.itemsRemito = itemsRemito;
	}

	public Integer getCodigoProveedor() {
		return codigoProveedor;
	}

	public void setCodigoProveedor(Integer codigoProveedor) {
		this.codigoProveedor = codigoProveedor;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCodRemito() {
		return codRemito;
	}

	public void setCodRemito(Integer codRemito) {
		this.codRemito = codRemito;
	}	
	
	public RemitoEntity toEntity() {
		List<ItemRemitoEntity> items = new ArrayList<ItemRemitoEntity>();
		
		for(ItemRemito itemsNegocio: this.itemsRemito) {
			items.add(itemsNegocio.toEntity());
		}
		RemitoEntity remit= new RemitoEntity(this.codigoProveedor, this.fecha, items);
		return remit;
	}
	
	public RemitoDTO toDTO() {
		
		List<ItemRemitoDTO> items= new ArrayList<ItemRemitoDTO>();
		for(ItemRemito i:this.itemsRemito) {
			items.add(i.toDTO());
		}
		
		RemitoDTO remit=new RemitoDTO(this.codigoProveedor, this.fecha, items);
		return remit;
	}
	
}
