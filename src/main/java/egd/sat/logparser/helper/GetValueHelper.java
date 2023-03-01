package egd.sat.logparser.helper;

import org.apache.poi.ss.usermodel.Row;

public class GetValueHelper {
	public static int getIntegerValueOf(Row Row, Integer position) {
		return Double.valueOf(Row.getCell(position).getNumericCellValue()).intValue();
	}
	public static String getStringValueOf(Row Row, Integer position) {
		return Row.getCell(position).getStringCellValue();
	}
}
