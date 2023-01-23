package egd.sat.logparser.service;

import java.util.List;

import egd.sat.logparser.entity.TableDataObj;
import egd.sat.logparser.entity.TableEntityObj;
import egd.sat.logparser.exceptions.FileValidationServiceException;

public interface EntityObjectParserService {

    List<TableDataObj> parseEntityObject(TableEntityObj tableEntityObj) throws FileValidationServiceException;

}
