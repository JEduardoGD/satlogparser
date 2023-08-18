package egd.sat.logparser.entity;

public class AnalisisPagoDao {
	private AnalisisPagoDetalleDao fisicos = new AnalisisPagoDetalleDao();
	private AnalisisPagoDetalleDao virtuales = new AnalisisPagoDetalleDao();
	private AnalisisPagoDetalleDao reclamados = new AnalisisPagoDetalleDao();
	private AnalisisPagoDetalleDao otros = new AnalisisPagoDetalleDao();
	private AnalisisPagoDetalleDao totales = new AnalisisPagoDetalleDao();

	public AnalisisPagoDetalleDao getFisicos() {
		return fisicos;
	}

	public AnalisisPagoDetalleDao getVirtuales() {
		return virtuales;
	}

	public AnalisisPagoDetalleDao getReclamados() {
		return reclamados;
	}

	public AnalisisPagoDetalleDao getOtros() {
		return otros;
	}

	public AnalisisPagoDetalleDao getTotales() {
		return totales;
	}

	public void setFisicos(AnalisisPagoDetalleDao fisicos) {
		this.fisicos = fisicos;
	}

	public void setVirtuales(AnalisisPagoDetalleDao virtuales) {
		this.virtuales = virtuales;
	}

	public void setReclamados(AnalisisPagoDetalleDao reclamados) {
		this.reclamados = reclamados;
	}

	public void setOtros(AnalisisPagoDetalleDao otros) {
		this.otros = otros;
	}

	public void setTotales(AnalisisPagoDetalleDao totales) {
		this.totales = totales;
	}

}
