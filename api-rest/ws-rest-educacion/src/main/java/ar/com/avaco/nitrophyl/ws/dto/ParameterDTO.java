package ar.com.avaco.nitrophyl.ws.dto;

import ar.com.avaco.ws.rest.dto.DTOEntity;

public class ParameterDTO extends DTOEntity<Integer> {

	private Integer id;

	private String key;

	private String value;
	
	private String description;

	public ParameterDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ParameterDTO(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
