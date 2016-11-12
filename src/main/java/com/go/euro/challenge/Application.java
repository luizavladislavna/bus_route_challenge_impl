package com.go.euro.challenge;

import com.go.euro.challenge.entity.exception.ApplicationDefaultException;
import com.go.euro.challenge.entity.exception.ExitException;
import com.go.euro.challenge.service.ParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Arrays;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@Configuration
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	ParserService parser;

	public static void main (String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run (String... args)  {

		log.info("CommandLineRunner arguments :{}", Arrays.toString(args));
		if (args.length != 1) {
			throw new ExitException("There might exist data-source file path as args[0]. Go to README.");
		}
		try {
			parser.initDataFromFile(args[0]);
			log.info(" ~~~~~~ READY TO GO ~~~~~~ ");
		} catch (ApplicationDefaultException e) {
			throw new ExitException(e.getMessage());
		}
	}
}
