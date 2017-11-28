package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import dto.ElaboradoDTO;
import dto.PlatoDTO;
import entities.CartaEntity;
import entities.ElaboradoEntity;
import entities.PlatoEntity;
import enumns.CategoriaPlato;



public class Plato 
{
	private Integer codigo;
	private String nombre;
	private float precio;
	private List<Elaborado> productoPlato;
    private Carta carta;
    private CategoriaPlato categoria;
	
	
	public Plato(){}


	public Plato(Integer codigo, String nombre, float precio, List<Elaborado> productoPlato, Carta carta,
			CategoriaPlato categoria) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.productoPlato = productoPlato;
		this.carta = carta;
		this.categoria = categoria;
	}


	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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

	public List<Elaborado> getProductoPlato() {
		return productoPlato;
	}

	public void setProductoPlato(List<Elaborado> productoPlato) {
		this.productoPlato = productoPlato;
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	
	

	public CategoriaPlato getCategoria() {
		return categoria;
	}


	public void setCategoria(CategoriaPlato categoria) {
		this.categoria = categoria;
	}


	public PlatoEntity toEntity() 
	{
		PlatoEntity p=new PlatoEntity();
		p.setCodigo(codigo);
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setCategoria(categoria);
		p.setCarta(carta.toEntity());
		List<ElaboradoEntity> productos=new ArrayList<ElaboradoEntity>();
		for(Elaborado e:this.productoPlato ) 
		{
			productos.add(e.toEntity());
		}
		p.setProductoPlato(productos);
		return p;
	}
	
	public PlatoDTO toDTO() 
	{
		PlatoDTO p=new PlatoDTO();
		p.setCodigo(codigo);
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setCategoria(categoria);
		p.setCarta(carta.toDTO());
		List<ElaboradoDTO> productos=new ArrayList<ElaboradoDTO>();
		for(Elaborado e:this.productoPlato ) 
		{
			productos.add(e.toDTO());
		}
		p.setProductoPlato(productos);
		return p;
	}


}
