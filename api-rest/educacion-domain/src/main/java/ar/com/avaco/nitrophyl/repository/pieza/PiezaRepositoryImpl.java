package ar.com.avaco.nitrophyl.repository.pieza;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import ar.com.avaco.arc.core.component.bean.repository.NJBaseRepository;
import ar.com.avaco.nitrophyl.domain.entities.pieza.Pieza;

@Repository("piezaRepository")
public class PiezaRepositoryImpl extends NJBaseRepository<Long, Pieza> implements PiezaRepositoryCustom {

	public PiezaRepositoryImpl(EntityManager entityManager) {
		super(Pieza.class, entityManager);
	}

}
