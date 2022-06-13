package ar.com.avaco.commons.domain;

import java.util.ArrayList;
import java.util.List;

public class PiezaCompuesta extends Pieza {

	// Debe ser un manytomany, ya que las piezas no tiene "padre"
	// son todas piezas que pueden ser parte de otras, sean simples o compuestas 
	private List<Pieza> piezas;

	public PiezaCompuesta() {
		super.tipo = TipoPieza.COMPUESTA;
	}
	
	public void agrearPieza(Pieza pieza) {
		if (piezas == null) {
			piezas = new ArrayList<>();
		}
		piezas.add(pieza);
	}
	
	public List<Pieza> getPiezas() {
		return piezas;
	}
	
}
