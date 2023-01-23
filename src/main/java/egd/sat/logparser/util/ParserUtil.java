package egd.sat.logparser.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserUtil {
    private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static final String DATE_TIME_REGEX = "\\d{2}\\/\\d{2}\\/\\d{4}\\s\\d{2}\\:\\d{2}\\:\\d{2}";

    public static boolean isBigDecimalParseable(String stringValue) {
        try {
            new BigDecimal(stringValue);
        } catch (java.lang.NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static BigDecimal parseBigDecimal(String stringValue) {
        return new BigDecimal(stringValue);
    }

    public static boolean isDateTimeParseable(String stringValue) {
        Pattern p = Pattern.compile(DATE_TIME_REGEX);
        Matcher m = p.matcher(stringValue);
        return m.matches();
    }

    public static Date parseDateTime(String stringValue) throws ParseException {
        DateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        return df.parse(stringValue);
    }
}
