package ar.com.avaco.nitrophyl.ws.service;

import ar.com.avaco.commons.exception.BusinessException;
import ar.com.avaco.nitrophyl.ws.dto.ParameterDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface ParameterEPService extends CRUDEPService<Integer, ParameterDTO> {

	ParameterDTO getByKey(String key) throws BusinessException;

}
