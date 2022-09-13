package ar.com.avaco.ws.rest.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDimensionListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeRegistroDTO;
import ar.com.avaco.nitrophyl.ws.service.MoldeEPService;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class MoldesRestController extends AbstractDTORestController<MoldeDTO, Long, MoldeEPService> {

	@Resource(name = "moldeEPService")
	public void setService(MoldeEPService moldeEPService) {
		super.service = moldeEPService;
	}

	/* EP Molde */

	@RequestMapping(value = "/molde", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listMoldes() throws Exception {
		List<MoldeListadoDTO> listMoldeListado = this.service.listado();
		JSONResponse response = new JSONResponse();
		response.setData(listMoldeListado);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMolde(@PathVariable("id") Long id) throws Exception {
		MoldeDTO moldeDto = this.service.get(id);
		JSONResponse response = new JSONResponse();
		response.setData(moldeDto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addMolde(@RequestBody MoldeDTO moldeDTO) throws Exception {
		moldeDTO.setId(null);
		try {
			MoldeDTO saved = this.service.save(moldeDTO);
			JSONResponse response = new JSONResponse();
			response.setData(saved);
			response.setStatus(JSONResponse.OK);
			return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException(
					"Violacion de integridad, verifique que no exista un Molde con el mismo Codigo",e);
		}
	}

	@RequestMapping(value = "/molde/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateMolde(@PathVariable("id") Long id, @RequestBody MoldeDTO moldeDTO)
			throws Exception {
		moldeDTO.setId(id);
		MoldeDTO moldeDto = this.service.update(moldeDTO);
		JSONResponse response = new JSONResponse();
		response.setData(moldeDto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	/* EP Molde Boca */
	@RequestMapping(value = "/molde/boca/{idMolde}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMoldeBocas(@PathVariable("idMolde") Long idMolde) throws Exception {
		List<MoldeBocaListadoDTO> listMoldeBocaListado = this.service.getMoldesBoca(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(listMoldeBocaListado);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/boca/{idMolde}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateMoldeBocas(@PathVariable("idMolde") Long idMolde,
			@RequestBody List<MoldeBocaListadoDTO> moldeBocaListadoDTO) throws Exception {
		List<MoldeBocaListadoDTO> moldesBocaDto = this.service.updateMoldesBoca(idMolde, moldeBocaListadoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(moldesBocaDto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	/* EP MoldeDimension */
	@RequestMapping(value = "/molde/dimensiones/{idMolde}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMoldeDimensiones(@PathVariable("idMolde") Long idMolde) throws Exception {
		List<MoldeDimensionListadoDTO> moldeDimensionListadoDTOs = this.service.getMoldesDimension(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(moldeDimensionListadoDTOs);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/molde/dimensiones/{idMolde}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateMoldeDimensiones(@PathVariable("idMolde") Long idMolde,
			@RequestBody List<MoldeDimensionListadoDTO> moldeDimensionListadoDTOs) throws Exception {
		List<MoldeDimensionListadoDTO> result = this.service.updateMoldeDimensiones(idMolde, moldeDimensionListadoDTOs);
		JSONResponse response = new JSONResponse();
		response.setData(result);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/molde/registro/{idMolde}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getMoldeRegistro(@PathVariable("idMolde") Long idMolde) throws Exception {
		List<MoldeRegistroDTO> moldeRegistroDTOs = this.service.getMoldesRegistro(idMolde);
		JSONResponse response = new JSONResponse();
		response.setData(moldeRegistroDTOs);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/molde/registro/{idMolde}", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addMoldeRegistro(@RequestBody MoldeRegistroDTO moldeRegistroDTO) throws Exception {
		MoldeRegistroDTO saved = this.service.saveMoldeRegistro(moldeRegistroDTO);
		JSONResponse response = new JSONResponse();
		response.setData(saved);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}
	
}