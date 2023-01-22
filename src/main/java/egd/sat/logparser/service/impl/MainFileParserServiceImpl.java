package egd.sat.logparser.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egd.sat.logparser.exceptions.FileValidationServiceException;
import egd.sat.logparser.service.FileParserSerivice;
import egd.sat.logparser.service.FileValidationService;
import egd.sat.logparser.service.MainFileParserService;

@Service
public class MainFileParserServiceImpl implements MainFileParserService {

    @Autowired
    FileValidationService fileValidationService;
    @Autowired
    FileParserSerivice fileParserSerivice;

    @Override
    public void parse(String... strings) throws FileValidationServiceException {

        File logFile = null;
        List<String> lineas;

        if (strings.length != 1) {
            throw new FileValidationServiceException("More than one arguments");
        }
        if (strings.length == 1) {
            logFile = fileValidationService.validate(strings[0]);
        }

        String unaLinea;
        try {
            unaLinea = FileUtils.readFileToString(logFile, StandardCharsets.UTF_8);
            lineas = Arrays.asList(unaLinea.split("\n"));
        } catch (IOException e) {
            throw new FileValidationServiceException(e);
        }

        fileParserSerivice.analize(lineas);
    }
}
