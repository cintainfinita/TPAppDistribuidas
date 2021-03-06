package negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dto.IngredienteDTO;
import entities.*;

public class Ingrediente
{

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((materiaprima == null) ? 0 : materiaprima.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		if (materiaprima == null) {
			if (other.materiaprima != null)
				return false;
		} else if (!materiaprima.equals(other.materiaprima))
			return false;
		return true;
	}
	private Integer codigocomponente;
	private MateriaPrima materiaprima;
	private Integer cantidad;
	private SemiElaborado platosemielaborado;
	
	
	//Constructores
	
	public Ingrediente() {
		super();
	}
	public Ingrediente(Integer codigocomponente, MateriaPrima materiaprima, Integer cantidad,
			SemiElaborado platosemielaborado) {
		super();
		this.codigocomponente = codigocomponente;
		this.materiaprima = materiaprima;
		this.cantidad = cantidad;
		this.platosemielaborado = platosemielaborado;
	}


	public Ingrediente(MateriaPrima materiaprima, Integer cantidad) {
		super();
		this.materiaprima = materiaprima;
		this.cantidad = cantidad;
	}
	//Getters y Setters
	
	public Integer getCodigocomponente() {
		return codigocomponente;
	}
	public void setCodigocomponente(Integer codigocomponente) {
		this.codigocomponente = codigocomponente;
	}
	public MateriaPrima getMateriaprima() {
		return materiaprima;
	}
	public void setMateriaprima(MateriaPrima materiaprima) {
		this.materiaprima = materiaprima;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public SemiElaborado getPlatosemielaborado() {
		return platosemielaborado;
	}
	public void setPlatosemielaborado(SemiElaborado platosemielaborado) {
		this.platosemielaborado = platosemielaborado;
	}

	public IngredienteEntity toEntity() {
		IngredienteEntity i=new IngredienteEntity();
		i.setCantidad(cantidad);
		i.setCodigocomponente(codigocomponente);
		i.setMateriaprima(materiaprima.toEntity());
		i.setPlatosemielaborado(platosemielaborado.toEntitySemi());
		return i;
	}
	public IngredienteDTO toDTO() {
		IngredienteDTO i=new IngredienteDTO();
		i.setCantidad(cantidad);
		i.setCodigocomponente(codigocomponente);
		i.setMateriaprima(materiaprima.toDTO());
		i.setPlatosemielaborado(platosemielaborado.toDTO());
		return i;
	}


}
