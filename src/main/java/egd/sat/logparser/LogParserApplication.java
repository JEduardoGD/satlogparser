package egd.sat.logparser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import egd.sat.logparser.service.MainFileParserService;

@SpringBootApplication
public class LogParserApplication implements CommandLineRunner {
    
    @Autowired MainFileParserService mainFileParserService;

    public static void main(String[] args) {
        SpringApplication.run(LogParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainFileParserService.parse(args);
    }
}
