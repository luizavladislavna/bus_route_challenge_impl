package com.go.euro.challenge.service;

import java.nio.file.Path;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
public interface ParserService {
	boolean initDataFromFile (String fileName);
	boolean initDataFromFile (Path file);
}
