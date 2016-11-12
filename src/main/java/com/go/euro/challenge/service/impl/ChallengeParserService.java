package com.go.euro.challenge.service.impl;

import com.go.euro.challenge.config.ChallengeDirectConfig;
import com.go.euro.challenge.entity.exception.AppReadFileException;
import com.go.euro.challenge.entity.exception.ApplicationDefaultException;
import com.go.euro.challenge.repository.RouteStationRepository;
import com.go.euro.challenge.service.ParserService;
import com.go.euro.challenge.util.FilesUtil;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@Component
@Slf4j
@Accessors(fluent = true)
@Setter
public class ChallengeParserService implements ParserService {

    @Autowired
    ChallengeDirectConfig cfg;

    @Autowired
    RouteStationRepository repository;


    @Override
    public boolean initDataFromFile(String fileName) {
        requireNonNull(fileName);
        Path filePath = Paths.get(fileName);
        return initDataFromFile(filePath);
    }

    @Override
    public boolean initDataFromFile(Path filePath) {
        requireNonNull(filePath);
        if (!Files.isReadable(filePath)) {
            throw new ApplicationDefaultException(
                    String.format("Not enough permissions for reading file: %s", filePath));
        }
        return fileProcessor(filePath);
    }

    private boolean fileProcessor(Path path) {
        boolean success = false;
        log.warn("read_from:\t{}", LocalDateTime.now());
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            FilesUtil.bufferedRead(br, this::bufferedReaderProcessor);
            success = true;
        } catch (IOException e) {
            throw new AppReadFileException(path.toString(), e);
        }
        log.warn("read_to:\t{}", LocalDateTime.now());
        return success;
    }

    private void processFileRow(String row) {
        if (!row.trim().isEmpty()) {
            String[] ids = row.trim().split(" ");
            cfg.checkRouteStationsCounter(ids.length - 1);

            Integer routeId = Integer.valueOf(ids[0]);
            processRoute(routeId, Arrays.stream(ids));
        } else {
            log.warn("Empty row");
            return;
        }
    }

    private void processRoute(Integer routeId, Stream<String> rowStream) {
        repository.routeStations().put(routeId, new HashSet<>());
        rowStream.skip(1)
                .mapToInt(Integer::valueOf)
                .forEach(stationId -> processStation(routeId, stationId));
    }

    private void processStation(Integer routeId, Integer stationId) {
        repository.routeStations().get(routeId).add(stationId);
        if (!repository.stationRoutes().containsKey(stationId)) {
            repository.stationRoutes().put(stationId, new HashSet<>());
        }
        repository.stationRoutes().get(stationId).add(routeId);
    }

    void bufferedReaderProcessor(BufferedReader br) {
        try {
            String firstLine = br.readLine();
            processFirstLine(firstLine);
            Stream<String> stream = br.lines().onClose(FilesUtil.asUncheckedRunnable(br));
            stream.parallel().forEach(s -> processFileRow(s));
            checkRepositoryAfterLoad();
        } catch (IOException e) {
            throw new ApplicationDefaultException(e.getMessage());
        }
    }

    private void checkRepositoryAfterLoad() {
        cfg.checkStationsCounters(repository.routeStations().size());
        cfg.checkRoutesCounter(repository.stationRoutes().size());
        repository.show();
    }

    private Integer processFirstLine(String first) {
        log.info("config: {}", cfg);
        Integer counter = Integer.valueOf(first.trim());
        log.debug("counter from first line = {}", counter);
        cfg.checkMaxRoutes(counter.intValue());
        return counter;
    }

}
