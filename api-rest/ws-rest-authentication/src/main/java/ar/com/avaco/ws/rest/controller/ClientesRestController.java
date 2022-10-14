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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.avaco.nitrophyl.ws.dto.ClienteDTO;
import ar.com.avaco.nitrophyl.ws.dto.ContactoDTO;
import ar.com.avaco.nitrophyl.ws.service.ClienteEPService;
import ar.com.avaco.nitrophyl.ws.service.filter.ClienteFilter;
import ar.com.avaco.ws.rest.dto.JSONResponse;

@RestController
public class ClientesRestController extends AbstractDTORestController<ClienteDTO, Long, ClienteEPService> {

	@Resource(name = "clienteEPService")
	public void setService(ClienteEPService clienteEPService) {
		super.service = clienteEPService;
	}

	/* EP Clientes */

	@RequestMapping(value = "/clientes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> listClientes(@RequestParam(name = "razonSocial", required = false) String razonSocial, @RequestParam(name = "cuit", required = false) String cuit) throws Exception {
		ClienteFilter filter = new ClienteFilter(razonSocial, cuit);
		List<ClienteDTO> listFilter = super.service.listFilter(filter);
		JSONResponse response = new JSONResponse();
		response.setData(listFilter);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addCliente(@RequestBody ClienteDTO clienteDTO) throws Exception {
		clienteDTO.setId(null);
		try {
			ClienteDTO saved = this.service.addCliente(clienteDTO);
			JSONResponse response = new JSONResponse();
			response.setData(saved);
			response.setStatus(JSONResponse.OK);
			return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException(
					"Violacion de integridad, verifique que no exista un Cliente con el mismo ID", e);
		}
	}

	@RequestMapping(value = "/cliente/{idCliente}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateCliente(@PathVariable("idCliente") Long idCliente,
			@RequestBody ClienteDTO clienteDTO) throws Exception {
		clienteDTO.setId(idCliente);
		ClienteDTO clienteDto = this.service.update(clienteDTO);
		JSONResponse response = new JSONResponse();
		response.setData(clienteDto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{idCliente}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getCliente(@PathVariable("idCliente") Long idCliente) throws Exception {
		ClienteDTO clienteDTO = this.service.getCliente(idCliente);
		JSONResponse response = new JSONResponse();
		response.setData(clienteDTO);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{idCliente}/contacto", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getContactoByCliente(@PathVariable("idCliente") Long idCliente)
			throws Exception {
		List<ContactoDTO> contactos = this.service.getContactosByCliente(idCliente);
		JSONResponse response = new JSONResponse();
		response.setData(contactos);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/contacto/{idContactoCliente}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> getContactoCliente(@PathVariable("idContactoCliente") Long idContactoCliente)
			throws Exception {
		ContactoDTO contactoDTO = this.service.getContacto(idContactoCliente);
		JSONResponse response = new JSONResponse();
		response.setData(contactoDTO);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = " /cliente/{idCliente}/contacto", method = RequestMethod.POST)
	public ResponseEntity<JSONResponse> addContactoCliente(@PathVariable("idCliente") Long idCliente,
			@RequestBody ContactoDTO contactoDTO) throws Exception {
		try {
			ContactoDTO saved = this.service.addContactoCliente(idCliente, contactoDTO);
			JSONResponse response = new JSONResponse();
			response.setData(saved);
			response.setStatus(JSONResponse.OK);
			return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException(
					"Violacion de integridad, verifique que no exista un Contacto con el mismo ID", e);
		}
	}

	@RequestMapping(value = "/cliente/contacto/{idContactoCliente}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONResponse> updateContactoCliente(@PathVariable("idContactoCliente") Long idContactoCliente,
			@RequestBody ContactoDTO contactoDTO) throws Exception {
		contactoDTO.setId(idContactoCliente);
		ContactoDTO contactoDto = this.service.updateContactoCliente(contactoDTO);
		JSONResponse response = new JSONResponse();
		response.setData(contactoDto);
		response.setStatus(JSONResponse.OK);
		return new ResponseEntity<JSONResponse>(response, HttpStatus.OK);
	}

}