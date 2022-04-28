package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeBoca;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeDimension;
import ar.com.avaco.nitrophyl.service.molde.MoldeBocaService;
import ar.com.avaco.nitrophyl.service.molde.MoldeDimensionService;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDimensionListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;
import ar.com.avaco.nitrophyl.ws.service.MoldeEPService;
import ar.com.avaco.ws.rest.security.service.ProfileService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("moldeEPService")
public class MoldeEPServiceImpl extends CRUDEPBaseService<Long, MoldeDTO, Molde, MoldeService>
		implements MoldeEPService {

	@Resource(name = "moldeDimensionService")
	private MoldeDimensionService moldeDimensionService;
	
	@Resource(name = "moldeBocaService")
	private MoldeBocaService moldeBocaService;
	
	@Override
	protected Molde convertToEntity(MoldeDTO dto) {
		Molde molde = new Molde();
		molde.setCodigo(dto.getCodigo());
		molde.setEstado(dto.getEstado());
		molde.setId(dto.getId());
		molde.setNombre(dto.getNombre());
		molde.setObservaciones(dto.getObservaciones());
		molde.setUbicacion(dto.getUbicacion());
		return molde;
	}

	@Override
	protected MoldeDTO convertToDto(Molde entity) {
		MoldeDTO moldeDto = new MoldeDTO();
		moldeDto.setCodigo(entity.getCodigo());
		moldeDto.setEstado(entity.getEstado());
		moldeDto.setId(entity.getId());
		moldeDto.setNombre(entity.getNombre());
		moldeDto.setObservaciones(entity.getObservaciones());
		moldeDto.setUbicacion(entity.getUbicacion());
		return moldeDto;
	}

	@Override
	@Resource(name = "moldeService")
	protected void setService(MoldeService service) {
		this.service = service;
	}

	@Override
	public List<MoldeDTO> listMoldes() {
		return convertToDtos(this.service.list());
	}

	@Override
	public List<MoldeListadoDTO> listado() {
		List<Molde> listado = this.service.list();
		if (listado != null) {
			List<MoldeListadoDTO> returnedList = new ArrayList<MoldeListadoDTO>();
			for (Molde molde : listado) {
				MoldeListadoDTO dto = new MoldeListadoDTO();
				dto.setCodigo(molde.getCodigo());
				dto.setEstado(molde.getEstado());
				dto.setNombre(molde.getNombre());
				dto.setId(molde.getId());
				returnedList.add(dto);
			}
			return returnedList;
		}
		return null;
	}

	@Override
	public List<MoldeBocaListadoDTO> getMoldesBoca(Long idMolde) {
		List<MoldeBoca> listado=moldeBocaService.getByMolde(idMolde);
		if (listado != null) {
			List<MoldeBocaListadoDTO> returnedListado=new ArrayList<MoldeBocaListadoDTO>();	
			for (MoldeBoca moldeBoca: listado) {
				MoldeBocaListadoDTO moldeBocaListadoDTO = new MoldeBocaListadoDTO();
				moldeBocaListadoDTO.setEstado(moldeBoca.getEstado());
				moldeBocaListadoDTO.setNroBoca(moldeBoca.getNroBoca());
				returnedListado.add(moldeBocaListadoDTO);								
			}
			return returnedListado;
		}		
		return null;
	}

	@Override
	public List<MoldeBocaListadoDTO> updateMoldesBoca(Long idMolde, List<MoldeBocaListadoDTO> moldeBocaListadoDTO) {
		if (moldeBocaListadoDTO != null) {
			List<MoldeBoca> listado=new ArrayList<MoldeBoca>();
			
			Molde molde = service.get(idMolde);
			
			for (MoldeBocaListadoDTO dto: moldeBocaListadoDTO) {
				MoldeBoca moldeBoca = new MoldeBoca();
				moldeBoca.setEstado(dto.getEstado());
				moldeBoca.setNroBoca(dto.getNroBoca());
				moldeBoca.setIdMolde(idMolde);
				listado.add(moldeBoca);
			}
			
			moldeBocaService.removeByMolde(idMolde);
			moldeBocaService.save(listado);
			
			molde.setBocas(listado.size());
			service.save(molde);
			
			return moldeBocaListadoDTO;
		}		
		return null;
	}

	@Override
	public List<MoldeDimensionListadoDTO> getMoldesDimension(Long idMolde) {			
		List<MoldeDimension> listado=moldeDimensionService.getByMolde(idMolde);
		if (listado != null) {
			List<MoldeDimensionListadoDTO> returnedListado=new ArrayList<MoldeDimensionListadoDTO>();	
			for (MoldeDimension moldeDimension: listado) {
				MoldeDimensionListadoDTO dto = new MoldeDimensionListadoDTO();
				dto.setTipoDimension(moldeDimension.getTipodimension());
				dto.setValor(moldeDimension.getValordimension());
				returnedListado.add(dto);								
			}
			return returnedListado;
		}		
		return null;
	}

	@Override
	public List<MoldeDimensionListadoDTO> updateMoldeDimensiones(Long idMolde,
			List<MoldeDimensionListadoDTO> moldeDimensionListadoDTOs) {

		if (moldeDimensionListadoDTOs != null) {
			List<MoldeDimension> listado=new ArrayList<MoldeDimension>();
			
			moldeDimensionService.removeByMolde(idMolde);
			
 			for (MoldeDimensionListadoDTO dto: moldeDimensionListadoDTOs) {
				MoldeDimension moldeDimension = new MoldeDimension();
				moldeDimension.setIdMolde(idMolde);
				moldeDimension.setTipodimension(dto.getTipoDimension());
				moldeDimension.setValordimension(dto.getValor());
				listado.add(moldeDimension);
			}
			
			
			this.moldeDimensionService.save(listado);
			return moldeDimensionListadoDTOs;
		}		
		return null;
	}

}
