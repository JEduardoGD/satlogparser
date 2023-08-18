package egd.sat.logparser.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.Resources;
import org.springframework.stereotype.Service;

import egd.sat.logparser.entity.TableDataObj;
import egd.sat.logparser.entity.TableEntityObj;
import egd.sat.logparser.exceptions.FileValidationServiceException;
import egd.sat.logparser.service.EntityObjectParserService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EntityObjectParserServiceImpl implements EntityObjectParserService {
    private static final String COLUMN_NAMES_PATH = "column_names";

    @Override
    public List<TableDataObj> parseEntityObject(TableEntityObj tableEntityObj) throws FileValidationServiceException {
        List<TableDataObj> tableDataObjList = new ArrayList<>();
        
        String tableName = tableEntityObj.getTableName();
        List<String> columnNames = null;

        String fileColumns;
        if (tableName.length() > 0) {
            fileColumns = tableName.trim().toUpperCase() + ".TXT";
        } else {
            fileColumns = null;
        }

		if (fileColumns != null) {
			InputStream inputStream = null;
			BufferedReader reader = null;
			try {
				columnNames = new ArrayList<>();
				inputStream = Resources.getInputStream(COLUMN_NAMES_PATH + File.separator + fileColumns);
				reader = new BufferedReader(new InputStreamReader(inputStream));
				while (reader.ready()) {
					columnNames.add(reader.readLine());
				}
			} catch (IOException  e1) {
				throw new FileValidationServiceException(e1);
			}catch (IllegalArgumentException e1) {
				log.error("No existe el archivo de nombres de columnas {}", COLUMN_NAMES_PATH + File.separator + fileColumns);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
					}
				}
			}
		}

        int rowNum = 0;
        for (String s : tableEntityObj.getStrings()) {
            String[] tokens = s.split("\\|");
            for (int i = 0; i < tokens.length; i++) {
                String columnName = "COL_" + i;
                if (columnNames != null && columnNames.size() >= (i + 1)) {
                    columnName = columnNames.get(i);
                }
                String columnValue = tokens[i];
                
                TableDataObj tableDataObj= new TableDataObj();
                tableDataObj.setRowNum(rowNum);
                tableDataObj.setColumnName(columnName);
                tableDataObj.setTableName(tableName);
                tableDataObj.setColumnValue(columnValue);
                tableDataObj.setColCount(i);
                tableDataObj.setFileName(tableEntityObj.getFilename());
                tableDataObj.setParentPath(tableEntityObj.getRoute());
                tableDataObjList.add(tableDataObj);
            }
            rowNum++;
        }
        return tableDataObjList;
    }
}
