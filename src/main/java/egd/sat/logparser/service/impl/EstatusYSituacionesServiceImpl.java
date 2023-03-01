package egd.sat.logparser.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import egd.sat.logparser.entity.ResolucionObj;
import egd.sat.logparser.helper.GetValueHelper;
import egd.sat.logparser.service.EstatusYSituacionesService;

@Service
public class EstatusYSituacionesServiceImpl implements EstatusYSituacionesService {

	private static final String TBL_RESOLUCION_NAME = "RESOLUCION";

	private static final int POS_IDRESOLUCION = 1;
	private static final int POS_NUMRESOLUCION = 4;

	@Override
	public void generateInformeEstatusYSituaciones(File file) {
		InputStream fis;
		XSSFWorkbook myWorkBook;
		try {
			fis = new FileInputStream(file);

			// Finds the workbook instance for XLSX file
			myWorkBook = new XSSFWorkbook(fis);

			// Return first sheet from the XLSX workbook
			XSSFSheet mySheet = myWorkBook.getSheet(TBL_RESOLUCION_NAME);

			// Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = mySheet.iterator();

			// Traversing over each row of XLSX file
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				ResolucionObj resolucion = new ResolucionObj();
				resolucion.setIdResolucion(GetValueHelper.getIntegerValueOf(row, POS_IDRESOLUCION));
				resolucion.setNumeroResolucion(GetValueHelper.getStringValueOf(row,POS_NUMRESOLUCION ));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
