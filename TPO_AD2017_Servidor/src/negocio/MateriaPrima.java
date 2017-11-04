package negocio;

import dto.MateriaPrimaDTO;
import dto.UnidadDTO;

public class MateriaPrima 
{
	private Integer codigo;
	private String descripcion;
	private Unidad unidadUso;

	public MateriaPrima(){}

	public MateriaPrima(String descripcion, Unidad unidadUso) {
		super();
		
		this.descripcion = descripcion;
		this.unidadUso = unidadUso;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Unidad getUnidadUso() {
		return unidadUso;
	}

	public void setUnidadUso(Unidad unidadUso) {
		this.unidadUso = unidadUso;
	}

	public MateriaPrimaDTO toDTO()
	{
		MateriaPrimaDTO m=new MateriaPrimaDTO();
		m.setCodigo(codigo);
		m.setDescripcion(descripcion);
		m.setUnidadUso(unidadUso.toDTO());
		return m;
	}
}
