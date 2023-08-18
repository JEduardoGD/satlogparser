package egd.sat.logparser.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egd.sat.logparser.entity.AnalisisPagoDao;
import egd.sat.logparser.entity.TableDataObj;
import egd.sat.logparser.exceptions.FileValidationServiceException;
import egd.sat.logparser.service.TableDataObjListParserService;
import egd.sat.logparser.util.ParserUtil;
import egd.sat.logparser.util.TimmerUtil;

@Service
public class TableDataObjListParserServiceImpl implements TableDataObjListParserService {
    int k;
    
    @Override
    public void generateHojaCalculo(List<TableDataObj> tableDataObjList, AnalisisPagoDao analisisPago) throws FileValidationServiceException {
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
        
        LocalDateTime startTime = LocalDateTime.now();
        int count = 1;
        
        Integer maxCol = null;

        for (String tableName : listTableNames) {
            List<TableDataObj> objectsOfTable = tableDataObjList.stream()
                    .filter(p -> p.getTableName().equals(tableName)).collect(Collectors.toList());

            XSSFSheet sheet = workbook.createSheet(tableName);

            // getting all rows
            List<Integer> allRows = objectsOfTable.stream().map(p -> p.getRowNum()).distinct().sorted()
                    .collect(Collectors.toList());
            for (Integer rowIterator : allRows) {
            	startTime = TimmerUtil.timmer(startTime, count++, allRows.size());
            	
                if (rowIterator == 0) {
                    rowHeader = sheet.createRow(0);
                }
                XSSFRow row = sheet.createRow(rowIterator + 1);
                List<TableDataObj> actualRow = objectsOfTable.stream().filter(p -> p.getRowNum() == rowIterator)
                        .collect(Collectors.toList());

                maxCol = actualRow.stream().map(TableDataObj::getColCount).max(Integer::compare).get();

				for (k = 0; k <= maxCol; k++) {
                    TableDataObj actualObj = actualRow.stream().filter(p -> p.getColCount() == k).findFirst().get();
                    if (rowIterator == 0) {
                        XSSFCell cellHeader = rowHeader.createCell(k);
                        cellHeader.setCellValue(actualObj.getColumnName());
                    }
					XSSFCell cell = row.createCell(k);
					
					
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
            
			if (maxCol != null) {
				sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, maxCol));
			}
            
			if ("PAGO".equals(tableName)) {
				
				XSSFRow rowx;
				XSSFCell cellx;
				XSSFSheet sheetx;
				
				sheetx = workbook.createSheet("PAGO_DETALLE");
				{
					rowx = sheetx.createRow(0);

					cellx = rowx.createCell(1);
					cellx.setCellValue("Registrados");

					cellx = rowx.createCell(2);
					cellx.setCellValue("No Aplicados");

					cellx = rowx.createCell(3);
					cellx.setCellValue("Aplicados");

					cellx = rowx.createCell(4);
					cellx.setCellValue("Totales");

					cellx = rowx.createCell(5);
					cellx.setCellValue("% aplicacion");
				}
				
				//Fisicos
				{
					rowx = sheetx.createRow(1);
					
					cellx = rowx.createCell(0);
					cellx.setCellValue("Pagos fisicos");
					
					cellx = rowx.createCell(1);
					cellx.setCellValue(analisisPago.getFisicos().getRegistrados());

					cellx = rowx.createCell(2);
					cellx.setCellValue(analisisPago.getFisicos().getNoAplicado());

					cellx = rowx.createCell(3);
					cellx.setCellValue(analisisPago.getFisicos().getAplicados());

					cellx = rowx.createCell(4);
					cellx.setCellValue(analisisPago.getFisicos().getTotales());

					if (analisisPago.getFisicos().getPorcentajeAplicacion() != null) {
						cellx = rowx.createCell(5);
						cellx.setCellValue(analisisPago.getFisicos().getPorcentajeAplicacion().doubleValue());
					}
				}
				
				//virtuales
				{
					rowx = sheetx.createRow(2);
					
					cellx = rowx.createCell(0);
					cellx.setCellValue("Pagos Virtuales");
					
					cellx = rowx.createCell(1);
					cellx.setCellValue(analisisPago.getVirtuales().getRegistrados());

					cellx = rowx.createCell(2);
					cellx.setCellValue(analisisPago.getVirtuales().getNoAplicado());

					cellx = rowx.createCell(3);
					cellx.setCellValue(analisisPago.getVirtuales().getAplicados());

					cellx = rowx.createCell(4);
					cellx.setCellValue(analisisPago.getVirtuales().getTotales());

					if (analisisPago.getVirtuales().getPorcentajeAplicacion() != null) {
						cellx = rowx.createCell(5);
						cellx.setCellValue(analisisPago.getVirtuales().getPorcentajeAplicacion().doubleValue());
					}
				}
				
				//reclamados
				{
					rowx = sheetx.createRow(3);
					
					cellx = rowx.createCell(0);
					cellx.setCellValue("Reclamados (fisicos + virtuales)");
					
					cellx = rowx.createCell(1);
					cellx.setCellValue(analisisPago.getReclamados().getRegistrados());

					cellx = rowx.createCell(2);
					cellx.setCellValue(analisisPago.getReclamados().getNoAplicado());

					cellx = rowx.createCell(3);
					cellx.setCellValue(analisisPago.getReclamados().getAplicados());

					cellx = rowx.createCell(4);
					cellx.setCellValue(analisisPago.getReclamados().getTotales());

					if (analisisPago.getReclamados().getPorcentajeAplicacion() != null) {
						cellx = rowx.createCell(5);
						cellx.setCellValue(analisisPago.getReclamados().getPorcentajeAplicacion().doubleValue());
					}
				}
				
				//otros
				{
					rowx = sheetx.createRow(4);
					
					cellx = rowx.createCell(0);
					cellx.setCellValue("Otros");
					
					cellx = rowx.createCell(1);
					cellx.setCellValue(analisisPago.getOtros().getRegistrados());

					cellx = rowx.createCell(2);
					cellx.setCellValue(analisisPago.getOtros().getNoAplicado());

					cellx = rowx.createCell(3);
					cellx.setCellValue(analisisPago.getOtros().getAplicados());

					cellx = rowx.createCell(4);
					cellx.setCellValue(analisisPago.getOtros().getTotales());

					if (analisisPago.getOtros().getPorcentajeAplicacion() != null) {
						cellx = rowx.createCell(5);
						cellx.setCellValue(analisisPago.getOtros().getPorcentajeAplicacion().doubleValue());
					}
				}
				
				//totales
				{
					rowx = sheetx.createRow(5);
					
					cellx = rowx.createCell(0);
					cellx.setCellValue("Totales");
					
					cellx = rowx.createCell(1);
					cellx.setCellValue(analisisPago.getTotales().getRegistrados());

					cellx = rowx.createCell(2);
					cellx.setCellValue(analisisPago.getTotales().getNoAplicado());

					cellx = rowx.createCell(3);
					cellx.setCellValue(analisisPago.getTotales().getAplicados());

					cellx = rowx.createCell(4);
					cellx.setCellValue(analisisPago.getTotales().getTotales());

					if (analisisPago.getTotales().getPorcentajeAplicacion() != null) {
						cellx = rowx.createCell(5);
						cellx.setCellValue(analisisPago.getTotales().getPorcentajeAplicacion().doubleValue());
					}
				}
			}
        }

		File f = Paths.get(parentPath.getAbsolutePath() + File.separator + filename).toFile();
        if (f.exists() && !f.delete()) {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new FileValidationServiceException(e);
            }
            throw new FileValidationServiceException("El archivo destino existe pero no se puede borrar");
        }

        try (OutputStream outputStream = new FileOutputStream(f)) {
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            throw new FileValidationServiceException(e);
        }
    }
}
