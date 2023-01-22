package egd.sat.logparser.exceptions;

import java.io.IOException;

public class FileValidationServiceException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6020431060175942532L;

    public FileValidationServiceException(String s) {
        super(s);
    }

    public FileValidationServiceException(IOException e) {
        super(e);
    }

}
