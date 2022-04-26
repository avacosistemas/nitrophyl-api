package ar.com.avaco.nitrophyl.service.molde;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeBoca;
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

	@Override
	public Molde getWithMoldesBoca(Long idMolde) {
		Molde getted=this.get(idMolde);
		if (getted!=null) {
			getted.getMoldeBocas().size(); //Doing the fetch
			return getted;		
		}
		
		return null;
	}
	
	@Override
	public Molde getWithMoldesDimension(Long idMolde) {
		Molde getted=this.get(idMolde);
		if (getted!=null) {
			getted.getMoldesDimension().size(); //Doing the fetch
			return getted;		
		}
		
		return null;
	}

}
