package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeBoca;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeDimension;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDimensionListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;
import ar.com.avaco.nitrophyl.ws.service.MoldeEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("moldeEPService")
public class MoldeEPServiceImpl extends CRUDEPBaseService<Long, MoldeDTO, Molde, MoldeService>
		implements MoldeEPService {

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
		Molde molde = this.service.getWithMoldesBoca(idMolde);
		if (molde == null) 
			return null;
					
		List<MoldeBoca> listado=molde.getMoldeBocas();
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
		Molde molde = this.service.get(idMolde);
		if (molde == null) 
			return null;
					

		if (moldeBocaListadoDTO != null) {
			List<MoldeBoca> listado=new ArrayList<MoldeBoca>();
			
			for (MoldeBocaListadoDTO dto: moldeBocaListadoDTO) {
				MoldeBoca moldeBoca = new MoldeBoca();
				moldeBoca.setEstado(dto.getEstado());
				moldeBoca.setNroBoca(dto.getNroBoca());
				moldeBoca.setMolde(molde);
				listado.add(moldeBoca);
			}
			
			molde.setBocas(moldeBocaListadoDTO.size());
			molde.setMoldeBocas(listado);
			this.service.save(molde);
			return moldeBocaListadoDTO;
		}		
		return null;
	}

	@Override
	public List<MoldeDimensionListadoDTO> getMoldesDimension(Long idMolde) {
		Molde molde = this.service.getWithMoldesDimension(idMolde);
		if (molde == null) 
			return null;
					
		List<MoldeDimension> listado=molde.getMoldesDimension();
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
		Molde molde = this.service.get(idMolde);
		if (molde == null) 
			return null;
					

		if (moldeDimensionListadoDTOs != null) {
			List<MoldeDimension> listado=new ArrayList<MoldeDimension>();
			
			for (MoldeDimensionListadoDTO dto: moldeDimensionListadoDTOs) {
				MoldeDimension moldeDimension = new MoldeDimension();
				moldeDimension.setMolde(molde);
				moldeDimension.setTipodimension(dto.getTipoDimension());
				moldeDimension.setValordimension(dto.getValor());
				listado.add(moldeDimension);
			}
			
			molde.setMoldesDimension(listado);
			this.service.save(molde);
			return moldeDimensionListadoDTOs;
		}		
		return null;
	}

}
