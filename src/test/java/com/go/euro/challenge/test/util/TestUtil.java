package com.go.euro.challenge.test.util;

import com.go.euro.challenge.repository.RouteStationRepository;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Luiza on 12.11.2016.
 */
@Slf4j
public class TestUtil {
    public static RouteStationRepository prepareRepo(){
        RouteStationRepository repository = new RouteStationRepository();
        repository.routeStations().put(1, new HashSet(Arrays.asList(new Integer[]{1, 3, 8})));
        repository.routeStations().put(2, new HashSet(Arrays.asList(new Integer[]{3, 5, 17, 6})));
        repository.routeStations().put(3, new HashSet(Arrays.asList(new Integer[]{6, 11, 15})));
        repository.routeStations().forEach((routeID, stations) -> {
            stations.forEach(stationID -> {
                if (!repository.stationRoutes().containsKey(stationID)) {
                    repository.stationRoutes().put(stationID, new HashSet<>());
                }
                repository.stationRoutes().get(stationID).add(routeID);
            });
        });
        repository.show();
        return repository;
    }

    @Deprecated
    public static Path tmpFileGenerator (String fileName, int stationsN, int routesN, int routeStationsN) {
        log.debug("Start to generate file: {}", fileName);
        Path target = Paths.get(fileName);
        if (Files.exists(target)) return target;
        try (BufferedWriter writer = Files.newBufferedWriter(target)) {
            List<Integer> stations = IntStream.range(0, stationsN).boxed().collect(Collectors.toList());
            List<Integer> routest = IntStream.range(0, routesN).boxed().collect(Collectors.toList());
            Collections.shuffle(routest);

            writer.write(String.valueOf(routest.size()));
            routest.parallelStream().forEach(routeID -> {
                        Collections.shuffle(stations);
                        String s = "\n" + routeID + " " + Joiner.on(' ').join(stations.subList(0, routeStationsN));
                        try {
                            writer.append(s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            writer.flush();
            writer.close();
            log.debug("Generation completed!");
            return target;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String resourceToPath(String srcPath) throws URISyntaxException {
        URI uri =  ClassLoader.getSystemResource(srcPath).toURI();
        return new File(uri).getAbsolutePath();
    }
}
