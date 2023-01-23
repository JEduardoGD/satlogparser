package egd.sat.logparser.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import egd.sat.logparser.entity.TableDataObj;
import egd.sat.logparser.entity.TableEntityObj;
import egd.sat.logparser.exceptions.FileValidationServiceException;
import egd.sat.logparser.service.EntityObjectParserService;

@Service
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
            Path fileColumnsPath = Paths.get(COLUMN_NAMES_PATH + File.separator + fileColumns);
            try {
                File fileColumnsFile = fileColumnsPath.toFile();
                if (fileColumnsFile.exists() && fileColumnsFile.canRead()) {
                    columnNames = Files.readAllLines(Paths.get(fileColumnsFile.getPath()), StandardCharsets.UTF_8);
                }

            } catch (IOException e) {
                throw new FileValidationServiceException(e);
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
                tableDataObj.setFilenName(tableEntityObj.getFilename());
                tableDataObjList.add(tableDataObj);
            }
            rowNum++;
        }
        return tableDataObjList;
    }
}
