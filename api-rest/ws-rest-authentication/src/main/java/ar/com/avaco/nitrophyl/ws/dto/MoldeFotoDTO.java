package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.nitrophyl.domain.entities.moldes.MoldeFoto;

public class MoldeFotoDTO {

	private Long idMolde;
	private String nombreArchivo;
	private byte[] archivo;
	
	public MoldeFotoDTO() {
		super();
	}

	public MoldeFotoDTO(MoldeFoto mf) {
		super();
		this.idMolde = mf.getIdMolde();
		this.nombreArchivo = mf.getNombreArchivo();
		this.archivo = mf.getArchivo();
	}

	public Long getIdMolde() {
		return idMolde;
	}

	public void setIdMolde(Long idMolde) {
		this.idMolde = idMolde;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

}
