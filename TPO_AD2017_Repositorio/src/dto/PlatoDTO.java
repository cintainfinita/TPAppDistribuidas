package dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import enumns.AreaRest;
import enumns.CategoriaPlato;




public class PlatoDTO implements Serializable
{

	private Integer codPlato;
	private String nombre;
	private float precio;
	private List<ElaboradoDTO> productoPlato;
	private CartaDTO carta;
	private CategoriaPlato categoria;
	private AreaRest area;
	
	public PlatoDTO() {}

	/*
	public PlatoDTO(Integer codigo, String nombre, AreaRest area,float precio, List<ElaboradoDTO> productoPlato, CartaDTO carta,
			CategoriaPlato categoria) {
		super();
		this.codPlato = codigo;
		this.area=area;
		this.nombre = nombre;
		this.precio = precio;
		this.productoPlato = productoPlato;
		this.carta = carta;
		this.categoria = categoria;
	}
	
*/
	public PlatoDTO(String nombre, float precio, List<ElaboradoDTO> productoPlato,
			CategoriaPlato categoria, AreaRest area, CartaDTO carta) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.productoPlato = productoPlato;
		this.categoria = categoria;
		this.area = area;
		this.carta = carta;
	}

	public AreaRest getArea() {
		return area;
	}

	public void setArea(AreaRest area) {
		this.area = area;
	}

	public CategoriaPlato getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaPlato categoria) {
		this.categoria = categoria;
	}

	public Integer getCodigo() {
		return codPlato;
	}

	public void setCodigo(Integer codigo) {
		this.codPlato = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public List<ElaboradoDTO> getProductoPlato() {
		return productoPlato;
	}

	public void setProductoPlato(List<ElaboradoDTO> productoPlato) {
		this.productoPlato = productoPlato;
	}

	public CartaDTO getCarta() {
		return carta;
	}

	public void setCarta(CartaDTO carta) {
		this.carta = carta;
	}

	@Override
	public String toString() {
		return "PlatoDTO [codigo=" + codPlato + ", nombre=" + nombre + ", precio=" + precio +"]";
	}


	
}
