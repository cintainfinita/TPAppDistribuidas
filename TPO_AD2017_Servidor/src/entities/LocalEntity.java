package entities;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import negocio.Local;

@Entity
@Table(name="locales")
public class LocalEntity 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codLocal")
	private Integer codigoLocal;
	private String direccion;
	private String barrio;
	
	@OneToOne
	@JoinColumn(name="codDeposito")
	private DepositoEntity deposito;
	
	//Un local tiene varias areas
	
	/*@OneToMany(cascade= CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="areasResto")
	private List<AreaRestaurantEntity> areas;
	NO funciona ya que la tabla AreaRestaurante
	 no existe en base por ser interface
	
	*/
	public LocalEntity(){}


	public LocalEntity(String direccion, String barrio, DepositoEntity deposito) {
		super();
		this.direccion = direccion;
		this.barrio = barrio;
		this.deposito = deposito;

	}


	public Integer getCodigoLocal() {
		return codigoLocal;
	}


	public void setCodigoLocal(Integer codigoLocal) {
		this.codigoLocal = codigoLocal;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getBarrio() {
		return barrio;
	}


	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public DepositoEntity getDeposito() {
		return deposito;
	}


	public void setDeposito(DepositoEntity deposito) {
		this.deposito = deposito;
	}

	public Local toNegocio() 
	{
		Local l=new Local();
		l.setCodigoLocal(codigoLocal);
		l.setBarrio(barrio);
		l.setDireccion(direccion);
		l.setDeposito(deposito.toNegocio());
		return l;
	}


	

	

	
}
