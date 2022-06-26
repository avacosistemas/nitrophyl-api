package ar.com.avaco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

import ar.com.avaco.nitrophyl.domain.entities.Pieza;
import ar.com.avaco.nitrophyl.domain.entities.PiezaCompuesta;
import ar.com.avaco.nitrophyl.domain.entities.PiezaSimple;

public class PiezaTest {

	private Map<Long, Pieza> piezas;
	
	@Before
	public void before() {
		piezas = new HashMap<>();
	}
	
	@Test
	public void crearPieza() {
		crearPiezaSimple(1L, "Pieza Simple 1");
		crearPiezaSimple(2L, "Pieza Simple 2");
		crearPiezaSimple(3L, "Pieza Simple 3");
		crearPiezaSimple(4L, "Pieza Simple 4");
		crearPiezaSimple(5L, "Pieza Simple 5");
		crearPiezaSimple(6L, "Pieza Simple 6");
		
		PiezaCompuesta pc2 = crearPiezaCompuesta(7L, "Pieza Compuesta 2");
		pc2.agrearPieza(getPieza(1L));
		pc2.agrearPieza(getPieza(2L));
		
		PiezaCompuesta pc1 = crearPiezaCompuesta(8L, "Pieza Compuesta 1");
		pc1.agrearPieza(getPieza(7L));
		pc1.agrearPieza(getPieza(3L));
		
		PiezaCompuesta pc3 = crearPiezaCompuesta(9L, "Pieza Compuesta 3");
		pc3.agrearPieza(getPieza(4L));
		pc3.agrearPieza(getPieza(5L));
		
		PiezaCompuesta piezaFinal = crearPiezaCompuesta(10L, "Pieza Final");
		piezaFinal.agrearPieza(getPieza(8L));
		piezaFinal.agrearPieza(getPieza(9L));
		piezaFinal.agrearPieza(getPieza(6L));
		
		describirPieza(piezaFinal, 0);
		
	}

	public void describirPieza(Pieza pieza, int espacios) {
		for (int i = 1 ; i <= espacios; i ++) {
			System.out.print("-");
		}
		System.out.println(pieza.getNombre());
		
		espacios++;
		espacios++;
		
		if (pieza.isCompuesta()) {
			for (Pieza p : pieza.getPiezas()) {
				describirPieza(p, espacios);
			}
		}
	}
	
	private PiezaCompuesta crearPiezaCompuesta(Long id, String nombre) {
		PiezaCompuesta pieza = new PiezaCompuesta();
		pieza.setId(id);
		pieza.setNombre(nombre);
		savePieza(pieza);
		return pieza;
	}

	private void crearPiezaSimple(Long id, String nombre) {
		PiezaSimple pieza = new PiezaSimple();
		pieza.setId(id);
		pieza.setNombre(nombre);
		savePieza(pieza);
	}
	
	private Pieza savePieza(Pieza pieza) {
		this.piezas.put(pieza.getId(), pieza);
		return pieza;
	}
	
	private List<Pieza> getPiezas() {
		return Lists.newArrayList(piezas.values());
	}
	
	private Pieza getPieza(Long id) {
		return piezas.get(id);
	}
	
	

}
