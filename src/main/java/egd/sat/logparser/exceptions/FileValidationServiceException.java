package egd.sat.logparser.exceptions;

public class FileValidationServiceException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6020431060175942532L;

    public FileValidationServiceException(String s) {
        super(s);
    }

    public FileValidationServiceException(Exception e) {
        super(e);
    }

}
