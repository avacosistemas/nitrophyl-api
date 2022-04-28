package ar.com.avaco.nitrophyl.service.molde;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.repository.molde.MoldeRepository;

@Transactional
@Service("moldeService")
public class MoldeServiceImpl extends NJBaseService<Long, Molde, MoldeRepository>
		implements MoldeService {

	private Logger logger = Logger.getLogger(getClass());		

	@Resource(name = "moldeRepository")
	void setClienteRepository(MoldeRepository moldeRepository) {
		this.repository = moldeRepository;
	}	

}
