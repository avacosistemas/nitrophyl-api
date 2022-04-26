
package ar.com.avaco.nitrophyl.ws.service;

import java.util.List;

import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDimensionListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;
import ar.com.avaco.ws.rest.service.CRUDEPService;

public interface MoldeEPService extends CRUDEPService<Long, MoldeDTO> {

	List<MoldeDTO> listMoldes();

	List<MoldeListadoDTO> listado();

	List<MoldeBocaListadoDTO> getMoldesBoca(Long idMolde);

	List<MoldeBocaListadoDTO> updateMoldesBoca(Long idMolde, List<MoldeBocaListadoDTO> moldeBocaListadoDTO);

	List<MoldeDimensionListadoDTO> getMoldesDimension(Long idMolde);

	List<MoldeDimensionListadoDTO> updateMoldeDimensiones(Long idMolde,
			List<MoldeDimensionListadoDTO> moldeDimensionListadoDTOs);

}
