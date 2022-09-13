package ar.com.avaco.nitrophyl.domain.entities.moldes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MOLDES")
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "MOLDES_SEQ", sequenceName = "MOLDES_SEQ", allocationSize = 1)
public class Molde extends ar.com.avaco.arc.core.domain.Entity<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOLDES_SEQ")
	@Column(name = "ID_MOLDE", unique = true, nullable = false)
	private Long id;

	@Column(name = "CODIGO", unique = true, nullable = false)
	private String codigo;

	@Column(name = "ESTADO", unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private EstadoMolde estado;

	@Column(name = "NOMBRE", unique = false, nullable = false)
	private String nombre;

	@Column(name = "UBICACION")
	private String ubicacion;

	@Column(name = "BOCAS")
	private Integer bocas;

	@Column(name = "OBSERVACIONES")
	private String observaciones;

	public Molde() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public EstadoMolde getEstado() {
		return estado;
	}

	public void setEstado(EstadoMolde estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Integer getBocas() {
		return bocas;
	}

	public void setBocas(Integer bocas) {
		this.bocas = bocas;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
