package ar.com.avaco.nitrophyl.service.formula;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.avaco.arc.core.component.bean.service.NJBaseService;
import ar.com.avaco.nitrophyl.domain.entities.formula.Formula;
import ar.com.avaco.nitrophyl.repository.material.FormulaRepository;

@Transactional
@Service("formulaService")
public class FormulaServiceImpl extends NJBaseService<Long, Formula, FormulaRepository> implements FormulaService {

	@Resource(name = "formulaRepository")
	public void setFormulaRepository(FormulaRepository formulaRepository) {
		this.repository = formulaRepository;
	}

}
