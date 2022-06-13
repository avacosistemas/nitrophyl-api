package ar.com.avaco.commons.domain;

import java.util.List;

import ar.com.avaco.arc.core.domain.Entity;

public abstract class Pieza extends Entity<Long> {

	private static final long serialVersionUID = -6870358670680621160L;

	private Long id;

	private String nombre;

	public abstract List<Pieza> getPiezas();
	
	public TipoPieza tipo;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public boolean isSimple() {
		return tipo.equals(TipoPieza.SIMPLE);
	}
	
	public boolean isCompuesta() {
		return !isSimple();
	}
	
}
