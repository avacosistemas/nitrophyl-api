package ar.com.avaco.nitrophyl.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.com.avaco.nitrophyl.domain.entities.moldes.EstadoBoca;
import ar.com.avaco.nitrophyl.domain.entities.moldes.EstadoMolde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.Molde;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeBoca;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeDimension;
import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeRegistro;
import ar.com.avaco.nitrophyl.domain.entities.moldes.TipoRegistroMolde;
import ar.com.avaco.nitrophyl.service.molde.MoldeBocaService;
import ar.com.avaco.nitrophyl.service.molde.MoldeDimensionService;
import ar.com.avaco.nitrophyl.service.molde.MoldeService;
import ar.com.avaco.nitrophyl.service.molde.MoldeRegistroService;
import ar.com.avaco.nitrophyl.ws.dto.MoldeBocaListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeDimensionListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeListadoDTO;
import ar.com.avaco.nitrophyl.ws.dto.MoldeRegistroDTO;
import ar.com.avaco.nitrophyl.ws.service.MoldeEPService;
import ar.com.avaco.ws.rest.service.CRUDEPBaseService;

@Service("moldeEPService")
public class MoldeEPServiceImpl extends CRUDEPBaseService<Long, MoldeDTO, Molde, MoldeService>
		implements MoldeEPService {

	@Resource(name = "moldeDimensionService")
	private MoldeDimensionService moldeDimensionService;
	
	@Resource(name = "moldeBocaService")
	private MoldeBocaService moldeBocaService;
	
	@Resource(name = "moldeRegistroService")
	private MoldeRegistroService moldeRegistroService;
	
	@Override
	protected Molde convertToEntity(MoldeDTO dto) {
		Molde molde = new Molde();
		molde.setCodigo(dto.getCodigo());
		molde.setEstado(EstadoMolde.valueOf(dto.getEstado()));
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
		moldeDto.setEstado(entity.getEstado().toString());
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
				dto.setEstado(molde.getEstado().toString());
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
				moldeBocaListadoDTO.setEstado(moldeBoca.getEstado().toString());
				moldeBocaListadoDTO.setNroBoca(moldeBoca.getNroBoca());
				moldeBocaListadoDTO.setDescripcion(moldeBoca.getDescripcion());
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
				moldeBoca.setEstado(EstadoBoca.valueOf(dto.getEstado()));
				moldeBoca.setNroBoca(dto.getNroBoca());
				moldeBoca.setIdMolde(idMolde);
				moldeBoca.setDescripcion(dto.getDescripcion());
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

	@Override
	public List<MoldeRegistroDTO> getMoldesRegistro(Long idMolde) {
		List<MoldeRegistro> list = moldeRegistroService.listByMoldeId(idMolde);
		List<MoldeRegistroDTO> ret = new ArrayList<>();
		list.forEach(mr -> ret.add(new MoldeRegistroDTO(mr)));
		return ret;
	}

	@Override
	public MoldeRegistroDTO saveMoldeRegistro(MoldeRegistroDTO moldeRegistroDTO) {
		
		MoldeRegistro mr = moldeRegistroService.getUltimoRegistro(moldeRegistroDTO.getIdMolde());
		MoldeRegistro nmr; 
		if (mr == null || (mr != null && mr.getTipoRegistro().equals(TipoRegistroMolde.EGRESO))) {
			nmr = moldeRegistroService.registrarIngreso(moldeRegistroDTO.getComentarios(), moldeRegistroDTO.getIdMolde());	
		} else {
			nmr = moldeRegistroService.registrarEgreso(moldeRegistroDTO.getComentarios(), moldeRegistroDTO.getIdMolde());
		}
		return new MoldeRegistroDTO(nmr);
	}

	
	
}
