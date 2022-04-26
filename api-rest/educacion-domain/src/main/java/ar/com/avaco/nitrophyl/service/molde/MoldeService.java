package ar.com.avaco.nitrophyl.service.molde;

import java.util.List
;

import ar.com.avaco.arc.core.component.bean.service.NJService;
import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.commons.exception.ErrorValidationException;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;
import ar.com.avaco.nitrophyl.domain.entities.cliente.Contacto;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeBoca;

public interface MoldeService extends NJService<Long, Molde> {

	Molde getWithMoldesBoca(Long idMolde);
	
	Molde getWithMoldesDimension(Long idMolde);

}
