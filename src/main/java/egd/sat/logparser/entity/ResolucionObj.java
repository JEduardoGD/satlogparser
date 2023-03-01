package egd.sat.logparser.entity;

import java.io.Serializable;

public class ResolucionObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6922634032626131423L;
	private int idResolucion;
	private String numeroResolucion;
	private int idEstatus;
	private String nombreEstatus;
	private Integer idSituacionAlta;
	private Integer idEstatusSituacionAlta;
	private Integer idSituacionBaja;
	private Integer idEstatusSituacionBaja;

	public int getIdResolucion() {
		return idResolucion;
	}

	public void setIdResolucion(int idResolucion) {
		this.idResolucion = idResolucion;
	}
	
	public String getNumeroResolucion() {
		return numeroResolucion;
	}

	public void setNumeroResolucion(String numeroResolucion) {
		this.numeroResolucion = numeroResolucion;
	}

	public int getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}

	public String getNombreEstatus() {
		return nombreEstatus;
	}

	public void setNombreEstatus(String nombreEstatus) {
		this.nombreEstatus = nombreEstatus;
	}

	public Integer getIdSituacionAlta() {
		return idSituacionAlta;
	}

	public void setIdSituacionAlta(Integer idSituacionAlta) {
		this.idSituacionAlta = idSituacionAlta;
	}

	public Integer getIdEstatusSituacionAlta() {
		return idEstatusSituacionAlta;
	}

	public void setIdEstatusSituacionAlta(Integer idEstatusSituacionAlta) {
		this.idEstatusSituacionAlta = idEstatusSituacionAlta;
	}

	public Integer getIdSituacionBaja() {
		return idSituacionBaja;
	}

	public void setIdSituacionBaja(Integer idSituacionBaja) {
		this.idSituacionBaja = idSituacionBaja;
	}

	public Integer getIdEstatusSituacionBaja() {
		return idEstatusSituacionBaja;
	}

	public void setIdEstatusSituacionBaja(Integer idEstatusSituacionBaja) {
		this.idEstatusSituacionBaja = idEstatusSituacionBaja;
	}

}
