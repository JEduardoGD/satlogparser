/*

            PAGO.IDPAGO                            ||'|'||
            PAGO.NUMLINEA                          ||'|'||
            PAGO.NUMDOCTO                          ||'|'||
    TO_CHAR(PAGO.FECPAGO, 'DD/MM/YYYY HH24:MI:SS') ||'|'||
            PAGO.IMPORTEPAGADO                     ||'|'||
            PAGO.IMPORTEVIRTUAL                    ||'|'||
            PAGO.REMANENTE                         ||'|'||
            PAGO.IDREGISTROPAGO                    ||'|'||
            PAGO.IDESTATUS                         ||'|'||
            PAGO.IDTRANSCBAJA                      ||'|'||
            PAGO.IDLINEATIPO                       ||'|'||
            PAGO.IDBATCHCORRIDA                    ||'|'||
            PAGO.IDBATCHINCIDENCIA                 ||'|'||
            PAGO.IDTIPOPAGO                        ||'|'||
            PAGO.VERSION                           ||'|'||
            PAGO.ORIGENDYP                         ||'|'||
            PAGO.NOMBREARCHIVO                     PAGO
*/

package egd.sat.logparser.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PagoEntity {
	private Long idpago;
	private String numlinea;
	private BigDecimal numdocto;
	private Date fecpago;
	private BigDecimal importepagado;
	private BigDecimal importevirtual;
	private BigDecimal remanente;
	private Long idregistropago;
	private Long idestatus;
	private Long idtransacbaja;
	private Long idlineatipo;
	private Long idbatchcorrida;
	private Long idbatchincidencia;
	private Long idtipopago;
	private Integer version;
	private Integer origendyp;
	private String nombrearchivo;

	public Long getIdpago() {
		return idpago;
	}

	public String getNumlinea() {
		return numlinea;
	}

	public BigDecimal getNumdocto() {
		return numdocto;
	}

	public Date getFecpago() {
		return fecpago;
	}

	public BigDecimal getImportepagado() {
		return importepagado;
	}

	public BigDecimal getImportevirtual() {
		return importevirtual;
	}

	public BigDecimal getRemanente() {
		return remanente;
	}

	public Long getIdregistropago() {
		return idregistropago;
	}

	public Long getIdestatus() {
		return idestatus;
	}

	public Long getIdlineatipo() {
		return idlineatipo;
	}

	public Long getIdbatchcorrida() {
		return idbatchcorrida;
	}

	public Long getIdbatchincidencia() {
		return idbatchincidencia;
	}

	public Long getIdtipopago() {
		return idtipopago;
	}

	public Integer getVersion() {
		return version;
	}

	public Integer getOrigendyp() {
		return origendyp;
	}

	public String getNombrearchivo() {
		return nombrearchivo;
	}

	public void setIdpago(Long idpago) {
		this.idpago = idpago;
	}

	public void setNumlinea(String numlinea) {
		this.numlinea = numlinea;
	}

	public void setNumdocto(BigDecimal numdocto) {
		this.numdocto = numdocto;
	}

	public void setFecpago(Date fecpago) {
		this.fecpago = fecpago;
	}

	public void setImportepagado(BigDecimal importepagado) {
		this.importepagado = importepagado;
	}

	public void setImportevirtual(BigDecimal importevirtual) {
		this.importevirtual = importevirtual;
	}

	public void setRemanente(BigDecimal remanente) {
		this.remanente = remanente;
	}

	public void setIdregistropago(Long idregistropago) {
		this.idregistropago = idregistropago;
	}

	public void setIdestatus(Long idestatus) {
		this.idestatus = idestatus;
	}

	public void setIdlineatipo(Long idlineatipo) {
		this.idlineatipo = idlineatipo;
	}

	public void setIdbatchcorrida(Long idbatchcorrida) {
		this.idbatchcorrida = idbatchcorrida;
	}

	public void setIdbatchincidencia(Long idbatchincidencia) {
		this.idbatchincidencia = idbatchincidencia;
	}

	public void setIdtipopago(Long idtipopago) {
		this.idtipopago = idtipopago;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setOrigendyp(Integer origendyp) {
		this.origendyp = origendyp;
	}

	public void setNombrearchivo(String nombrearchivo) {
		this.nombrearchivo = nombrearchivo;
	}

	public Long getIdtransacbaja() {
		return idtransacbaja;
	}

	public void setIdtransacbaja(Long idtransacbaja) {
		this.idtransacbaja = idtransacbaja;
	}
	
	

}
