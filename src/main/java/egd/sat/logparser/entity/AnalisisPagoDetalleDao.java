package egd.sat.logparser.entity;

import java.math.BigDecimal;

public class AnalisisPagoDetalleDao {
	private int registrados;
	private int aplicados;
	private int noAplicado;
	private int totales;
	private BigDecimal porcentajeAplicacion;

	public int getRegistrados() {
		return registrados;
	}

	public int getAplicados() {
		return aplicados;
	}

	public int getNoAplicado() {
		return noAplicado;
	}

	public int getTotales() {
		return totales;
	}

	public BigDecimal getPorcentajeAplicacion() {
		return porcentajeAplicacion;
	}

	public void setRegistrados(int registrados) {
		this.registrados = registrados;
	}

	public void setAplicados(int aplicados) {
		this.aplicados = aplicados;
	}

	public void setNoAplicado(int noAplicado) {
		this.noAplicado = noAplicado;
	}

	public void setTotales(int totales) {
		this.totales = totales;
	}

	public void setPorcentajeAplicacion(BigDecimal porcentajeAplicacion) {
		this.porcentajeAplicacion = porcentajeAplicacion;
	}

}
