package egd.sat.logparser.exceptions;

import java.text.ParseException;

public class UtilParserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3296398135004795710L;

	public UtilParserException(ParseException e) {
		super(e);
	}

}
