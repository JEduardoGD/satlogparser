package egd.sat.logparser.service;

import egd.sat.logparser.entity.AnalisisPagoDao;
import egd.sat.logparser.entity.TableEntityObj;

public interface PagosParser {

	AnalisisPagoDao parse(TableEntityObj tableEntityObj);
}
