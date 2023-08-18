package egd.sat.logparser.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import egd.sat.logparser.entity.AnalisisPagoDao;
import egd.sat.logparser.entity.PagoEntity;
import egd.sat.logparser.entity.TableEntityObj;
import egd.sat.logparser.helper.ParserUtil;
import egd.sat.logparser.service.PagosParser;

@Service
public class PagosParserImpl implements PagosParser {

	private static final String BLANK_SPACE = " ";
	private static final String PIPE_REGEX = "(\\|)";
	private static final String CUARENTAYCUATRO = "44";
	private static final Long APLICADO = 50003l;
	private static final Long NO_APLICADO = 50002l;
	private static final Long REGISTRADO =50001l;
	private static final BigDecimal CIEN_BD = new BigDecimal(100);

	@Override
	public AnalisisPagoDao parse(TableEntityObj tableEntityObj) {
		
		AnalisisPagoDao analisisPagoDao = new AnalisisPagoDao();
		
		List<PagoEntity> listPagos;
		listPagos = new ArrayList<>();

		for (String s : tableEntityObj.getStrings()) {
			s = s.concat(BLANK_SPACE);
			String[] a = s.split(PIPE_REGEX);
			PagoEntity p = new PagoEntity();

			p.setIdpago(ParserUtil.parseLong(a[0]));
			p.setNumlinea(ParserUtil.parseString(a[1]));
			p.setNumdocto(ParserUtil.parseBigDecimal(a[2]));
			p.setFecpago(ParserUtil.parseDate(a[3]));
			p.setImportepagado(ParserUtil.parseBigDecimal(a[4]));
			p.setImportevirtual(ParserUtil.parseBigDecimal(a[5]));
			p.setRemanente(ParserUtil.parseBigDecimal(a[6]));
			p.setIdregistropago(ParserUtil.parseLong(a[7]));
			p.setIdestatus(ParserUtil.parseLong(a[8]));
			p.setIdtransacbaja(ParserUtil.parseLong(a[9]));
			p.setIdlineatipo(ParserUtil.parseLong(a[10]));
			p.setIdbatchcorrida(ParserUtil.parseLong(a[11]));
			p.setIdbatchincidencia(ParserUtil.parseLong(a[12]));
			p.setIdpago(ParserUtil.parseLong(a[13]));
			p.setVersion(ParserUtil.parseInt(a[14]));
			p.setOrigendyp(ParserUtil.parseInt(a[15]));
			p.setNombrearchivo(ParserUtil.parseString(a[16]));
			listPagos.add(p);
		}

		for (PagoEntity p : listPagos) {

			boolean esFisico = false;
			boolean esVirtual = false;
			boolean esAplicado = false;
			boolean esNoAplicado = false;
			boolean esRegistrado = false;

			if (esCuarentaycuatro(p)) {
				esFisico = true;
			}

			if (esVirtual(p)) {
				esVirtual = true;
			}

			if (aplicado(p)) {
				esAplicado = true;
			}

			if (noAplicado(p)) {
				esNoAplicado = true;
			}

			if (registrado(p)) {
				esRegistrado = true;
			}

			analisisPagoDao.getTotales().setTotales(analisisPagoDao.getTotales().getTotales() + 1);
			if (esFisico) {
				analisisPagoDao.getFisicos().setTotales(analisisPagoDao.getFisicos().getTotales() + 1);
				analisisPagoDao.getReclamados().setTotales(analisisPagoDao.getReclamados().getTotales() + 1);
				if(esRegistrado) {
					analisisPagoDao.getFisicos().setRegistrados(analisisPagoDao.getFisicos().getRegistrados() + 1);
					analisisPagoDao.getReclamados().setRegistrados(analisisPagoDao.getReclamados().getRegistrados() + 1);
					analisisPagoDao.getTotales().setRegistrados(analisisPagoDao.getTotales().getRegistrados() + 1);
				}
				if(esNoAplicado) {
					analisisPagoDao.getFisicos().setNoAplicado(analisisPagoDao.getFisicos().getNoAplicado()+ 1);
					analisisPagoDao.getReclamados().setNoAplicado(analisisPagoDao.getReclamados().getNoAplicado() + 1);
					analisisPagoDao.getTotales().setNoAplicado(analisisPagoDao.getTotales().getNoAplicado() + 1);
				}
				if(esAplicado) {
					analisisPagoDao.getFisicos().setAplicados(analisisPagoDao.getFisicos().getAplicados()+ 1);
					analisisPagoDao.getReclamados().setAplicados(analisisPagoDao.getReclamados().getAplicados() + 1);
					analisisPagoDao.getTotales().setAplicados(analisisPagoDao.getTotales().getAplicados() + 1);
				}
			}
			if (esVirtual) {
				analisisPagoDao.getVirtuales().setTotales(analisisPagoDao.getVirtuales().getTotales() + 1);
				analisisPagoDao.getReclamados().setTotales(analisisPagoDao.getReclamados().getTotales() + 1);
				if(esRegistrado) {
					analisisPagoDao.getVirtuales().setRegistrados(analisisPagoDao.getVirtuales().getRegistrados() + 1);
					analisisPagoDao.getReclamados().setRegistrados(analisisPagoDao.getReclamados().getRegistrados() + 1);
					analisisPagoDao.getTotales().setRegistrados(analisisPagoDao.getTotales().getRegistrados() + 1);
				}
				if(esNoAplicado) {
					analisisPagoDao.getVirtuales().setNoAplicado(analisisPagoDao.getVirtuales().getNoAplicado()+ 1);
					analisisPagoDao.getReclamados().setNoAplicado(analisisPagoDao.getReclamados().getNoAplicado() + 1);
					analisisPagoDao.getTotales().setNoAplicado(analisisPagoDao.getTotales().getNoAplicado() + 1);
				}
				if(esAplicado) {
					analisisPagoDao.getVirtuales().setAplicados(analisisPagoDao.getVirtuales().getAplicados()+ 1);
					analisisPagoDao.getReclamados().setAplicados(analisisPagoDao.getReclamados().getAplicados() + 1);
					analisisPagoDao.getTotales().setAplicados(analisisPagoDao.getTotales().getAplicados() + 1);
				}
			}
			if(!esFisico && !esVirtual) {
				analisisPagoDao.getOtros().setTotales(analisisPagoDao.getOtros().getTotales() + 1);
				if(esRegistrado) {
					analisisPagoDao.getOtros().setRegistrados(analisisPagoDao.getOtros().getRegistrados() + 1);
					analisisPagoDao.getTotales().setRegistrados(analisisPagoDao.getTotales().getRegistrados() + 1);
				}
				if(esNoAplicado) {
					analisisPagoDao.getOtros().setNoAplicado(analisisPagoDao.getOtros().getNoAplicado() + 1);
					analisisPagoDao.getTotales().setNoAplicado(analisisPagoDao.getTotales().getNoAplicado() + 1);
				}
				if(esAplicado) {
					analisisPagoDao.getOtros().setAplicados(analisisPagoDao.getOtros().getAplicados() + 1);
					
					analisisPagoDao.getTotales().setAplicados(analisisPagoDao.getTotales().getAplicados() + 1);
				}
			}
		}
		
		BigDecimal porcentajeAplicacion;
		
		if(BigDecimal.valueOf(analisisPagoDao.getFisicos().getTotales()).compareTo(BigDecimal.ZERO) != 0) {
			porcentajeAplicacion = BigDecimal.valueOf(analisisPagoDao.getFisicos().getAplicados()).divide(BigDecimal.valueOf(analisisPagoDao.getFisicos().getTotales()), 4, RoundingMode.CEILING).multiply(CIEN_BD);
			analisisPagoDao.getFisicos().setPorcentajeAplicacion(porcentajeAplicacion);
		} else {
			analisisPagoDao.getFisicos().setPorcentajeAplicacion(null);
		}
		
		if(BigDecimal.valueOf(analisisPagoDao.getVirtuales().getTotales()).compareTo(BigDecimal.ZERO) != 0) {
			porcentajeAplicacion = BigDecimal.valueOf(analisisPagoDao.getVirtuales().getAplicados()).divide(BigDecimal.valueOf(analisisPagoDao.getVirtuales().getTotales()), 4, RoundingMode.CEILING).multiply(CIEN_BD);
			analisisPagoDao.getVirtuales().setPorcentajeAplicacion(porcentajeAplicacion);
		} else {
			analisisPagoDao.getVirtuales().setPorcentajeAplicacion(null);
		}
		
		if(BigDecimal.valueOf(analisisPagoDao.getReclamados().getTotales()).compareTo(BigDecimal.ZERO) != 0) {
			porcentajeAplicacion = BigDecimal.valueOf(analisisPagoDao.getReclamados().getAplicados()).divide(BigDecimal.valueOf(analisisPagoDao.getReclamados().getTotales()), 4, RoundingMode.CEILING).multiply(CIEN_BD);
			analisisPagoDao.getReclamados().setPorcentajeAplicacion(porcentajeAplicacion);
		} else {
			analisisPagoDao.getReclamados().setPorcentajeAplicacion(null);
		}
		
		if(BigDecimal.valueOf(analisisPagoDao.getOtros().getTotales()).compareTo(BigDecimal.ZERO) != 0) {
			porcentajeAplicacion = BigDecimal.valueOf(analisisPagoDao.getOtros().getAplicados()).divide(BigDecimal.valueOf(analisisPagoDao.getOtros().getTotales()), 4, RoundingMode.CEILING).multiply(CIEN_BD);
			analisisPagoDao.getOtros().setPorcentajeAplicacion(porcentajeAplicacion);
		} else {
			analisisPagoDao.getOtros().setPorcentajeAplicacion(null);
		}
		
		if(BigDecimal.valueOf(analisisPagoDao.getTotales().getTotales()).compareTo(BigDecimal.ZERO) != 0) {
			porcentajeAplicacion = BigDecimal.valueOf(analisisPagoDao.getTotales().getAplicados()).divide(BigDecimal.valueOf(analisisPagoDao.getTotales().getTotales()), 4, RoundingMode.CEILING).multiply(CIEN_BD);
			analisisPagoDao.getTotales().setPorcentajeAplicacion(porcentajeAplicacion);
		} else {
			analisisPagoDao.getTotales().setPorcentajeAplicacion(null);
		}
		
		
		
		return analisisPagoDao;
	}
	
	private boolean esCuarentaycuatro(PagoEntity p) {
		return p.getNumlinea() != null && p.getNumlinea().length() > 12 && CUARENTAYCUATRO.equals(p.getNumlinea().substring(10, 12));
	}
	
	private boolean esVirtual(PagoEntity p) {
		return p.getNumlinea() == null && p.getImportevirtual() != null;
	}
	
	private boolean aplicado(PagoEntity p) {
		if (p == null || p.getIdestatus() == null) {
			return false;
		}
		return p.getIdestatus().compareTo(APLICADO) == 0;
	}
	
	private boolean noAplicado(PagoEntity p) {
		if (p == null || p.getIdestatus() == null) {
			return false;
		}
		return p.getIdestatus().compareTo(NO_APLICADO) == 0;
	}
	
	private boolean registrado(PagoEntity p) {
		if (p == null || p.getIdestatus() == null) {
			return false;
		}
		return p.getIdestatus().compareTo(REGISTRADO) == 0;
	}
}