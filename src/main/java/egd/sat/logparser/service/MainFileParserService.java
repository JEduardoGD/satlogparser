package egd.sat.logparser.service;

import egd.sat.logparser.exceptions.FileValidationServiceException;

public interface MainFileParserService {

    void parse(String... strings) throws FileValidationServiceException;
}
