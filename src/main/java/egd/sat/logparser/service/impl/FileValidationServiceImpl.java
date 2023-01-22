package egd.sat.logparser.service.impl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import egd.sat.logparser.exceptions.FileValidationServiceException;
import egd.sat.logparser.service.FileValidationService;

@Service
public class FileValidationServiceImpl implements FileValidationService {
    @Override
    public File validate(String s) throws FileValidationServiceException {
        if (s == null) {
            throw new FileValidationServiceException("File is null");
        }

        Path path = Paths.get(s);
        File file = path.toFile();

        if (!file.exists()) {
            throw new FileValidationServiceException("File does not exists");
        }

        if (!file.canRead()) {
            throw new FileValidationServiceException("File can't read");
        }
        
        return file;
    }
}
