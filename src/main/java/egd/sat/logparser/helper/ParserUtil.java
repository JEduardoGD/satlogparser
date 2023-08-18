package egd.sat.logparser.helper;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import egd.sat.logparser.exceptions.UtilParserException;

public abstract class ParserUtil {

	private static final String EMPTY = "";
	private static final DateFormat DF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public static final Long parseLong(String s) {
		if (s == null || EMPTY.equals(s)) {
			return null;
		}
		return Long.parseLong(s);
	}

	public static final String parseString(String s) {
		if (s == null || EMPTY.equals(s)) {
			return null;
		}
		return s;
	}

	public static final Date parseDate(String s) {
		if (s == null || EMPTY.equals(s)) {
			return null;
		}
		Date d = null;
		try {
			d = DF.parse(s);
		} catch (ParseException e) {
			new UtilParserException(e);
		}
		return d;
	}

	public static final BigDecimal parseBigDecimal(String s) {
		if (s == null || EMPTY.equals(s)) {
			return null;
		}
		return new BigDecimal(s);
	}

	public static final Integer parseInt(String s) {
		if (s == null || EMPTY.equals(s)) {
			return null;
		}
		return Integer.getInteger(s);
	}
}
