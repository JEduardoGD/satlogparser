package egd.sat.logparser.service;

import java.util.List;

import egd.sat.logparser.entity.AnalisisPagoDao;
import egd.sat.logparser.entity.TableDataObj;
import egd.sat.logparser.exceptions.FileValidationServiceException;

public interface TableDataObjListParserService {

	void generateHojaCalculo(List<TableDataObj> tableDataObjList, AnalisisPagoDao analisisPago)
			throws FileValidationServiceException;
}
