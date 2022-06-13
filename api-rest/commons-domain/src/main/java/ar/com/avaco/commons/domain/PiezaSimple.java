package ar.com.avaco.commons.domain;

import java.util.List;

public class PiezaSimple extends Pieza {

	public PiezaSimple() {
		super.tipo = TipoPieza.SIMPLE;
	}
	
	@Override
	public List<Pieza> getPiezas() {
		throw new RuntimeException("Las piezas simles no contienen otras piezas");
	}

}
