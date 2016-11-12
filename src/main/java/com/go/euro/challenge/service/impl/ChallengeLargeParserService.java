package com.go.euro.challenge.service.impl;

import com.go.euro.challenge.entity.exception.AppReadFileException;
import com.go.euro.challenge.entity.exception.ApplicationDefaultException;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@Service(value = "challenge")
@Slf4j
@Accessors(fluent = true)
@Setter
public class ChallengeLargeParserService extends DefaultParserService {


    @Override
    protected boolean fileProcessor(Path path) {
        log.warn("read_from:\t{}", LocalDateTime.now());
        try {
            LineIterator iterator = FileUtils.lineIterator(path.toFile());
            if (iterator.hasNext()) {
                String firstLine = iterator.nextLine();
                Integer routesCounter = processFirstLine(firstLine);

                while (iterator.hasNext()) {
                    processFileRow(iterator.nextLine());
                }

                checkRepositoryAfterLoad(routesCounter);

                log.warn("read_to:\t{}", LocalDateTime.now());
                return true;
            } else {
                throw new ApplicationDefaultException("Empty file!");
            }
        } catch (IOException e) {
            throw new AppReadFileException(path.toString(), e);
        }
    }
}
