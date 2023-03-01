package egd.sat.logparser.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egd.sat.logparser.entity.TableDataObj;
import egd.sat.logparser.exceptions.FileValidationServiceException;
import egd.sat.logparser.service.TableDataObjListParserService;
import egd.sat.logparser.util.ParserUtil;

@Service
public class TableDataObjListParserServiceImpl implements TableDataObjListParserService {
    int k;

    @Override
    public void generateHojaCalculo(List<TableDataObj> tableDataObjList) throws FileValidationServiceException {
        List<String> listTableNames = tableDataObjList.stream().map(TableDataObj::getTableName).distinct()
                .collect(Collectors.toList());

        @SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFRow rowHeader = null;

        String filename = null;
        File parentPath = null;

        CreationHelper createHelper = workbook.getCreationHelper();
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));

        for (String tableName : listTableNames) {
            List<TableDataObj> objectsOfTable = tableDataObjList.stream()
                    .filter(p -> p.getTableName().equals(tableName)).collect(Collectors.toList());

            XSSFSheet sheet = workbook.createSheet(tableName);
			XSSFCell firstCell, lastCell;

            // getting all rows
            List<Integer> allRows = objectsOfTable.stream().map(p -> p.getRowNum()).distinct().sorted()
                    .collect(Collectors.toList());
            for (Integer rowIterator : allRows) {
                if (rowIterator == 0) {
                    rowHeader = sheet.createRow(0);
                }
                XSSFRow row = sheet.createRow(rowIterator + 1);
                List<TableDataObj> actualRow = objectsOfTable.stream().filter(p -> p.getRowNum() == rowIterator)
                        .collect(Collectors.toList());

                Integer maxCol = actualRow.stream().map(TableDataObj::getColCount).max(Integer::compare).get();

				for (k = 0; k <= maxCol; k++) {
                    TableDataObj actualObj = actualRow.stream().filter(p -> p.getColCount() == k).findFirst().get();
                    if (rowIterator == 0) {
                        XSSFCell cellHeader = rowHeader.createCell(k);
                        cellHeader.setCellValue(actualObj.getColumnName());
                    }
					XSSFCell cell = row.createCell(k);
					
					if (rowIterator == 0 && k == 0) {
						firstCell = cell;
					}
					if (rowIterator == allRows.get(allRows.size() - 1) && k == maxCol) {
						lastCell = cell;
					}
					
					
                    if (ParserUtil.isBigDecimalParseable(actualObj.getColumnValue())) {
                        cell.setCellType(CellType.NUMERIC);
                        cell.setCellValue(ParserUtil.parseBigDecimal(actualObj.getColumnValue()).doubleValue());
                    } else if (ParserUtil.isDateTimeParseable(actualObj.getColumnValue())) {
                        try {
                            cell.setCellValue(ParserUtil.parseDateTime(actualObj.getColumnValue()));
                            cell.setCellStyle(cellStyle);
                        } catch (ParseException e) {
                            throw new FileValidationServiceException(e);
                        }
                    } else {
                        cell.setCellValue(actualObj.getColumnValue());
                    }
                    filename = actualObj.getFileName();
                    parentPath = actualObj.getParentPath();
                }

            }
            //sheet.setAutoFilter(new CellRangeAddress(firstCell.getRow(), lastCell.getRow(), firstCell.getCol(), lastCell.getCol()));
        }

		File f = Paths.get(parentPath.getAbsolutePath() + File.separator + filename).toFile();
        if (f.exists() && !f.canWrite()) {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new FileValidationServiceException(e);
            }
            throw new FileValidationServiceException("El archivo destino existe pero no se puede borrar");
        }

        if (f.exists() && !f.delete()) {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new FileValidationServiceException(e);
            }
            throw new FileValidationServiceException("El archivo destino existe pero no se puede borrar");
        }

        try (OutputStream outputStream = new FileOutputStream(filename)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new FileValidationServiceException(e);
        }
    }
}
