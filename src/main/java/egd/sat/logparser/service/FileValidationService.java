package egd.sat.logparser.service;

import java.io.File;

import egd.sat.logparser.exceptions.FileValidationServiceException;

public interface FileValidationService {

    File validate(String s) throws FileValidationServiceException;
}
