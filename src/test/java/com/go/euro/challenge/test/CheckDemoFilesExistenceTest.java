package com.go.euro.challenge.test;


import com.go.euro.challenge.test.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */

@Slf4j
public class CheckDemoFilesExistenceTest {

    @Test
    public void small() {
        Path tempFile = tmpFileGenerator(1000, 10, 100);
        requireNonNull(tempFile);
    }

    //@Test //File is too large for GitHub(
    public void demoExist() {
        Path target = Paths.get("tests/demo/f_1000000_38712_1000");
        requireNonNull(target);
    }

    Path tmpFileGenerator(int stationsN, int routesN, int routeStationsN) {
        String file = String.format("tests/demo/f_%d_%d_%d", stationsN, routesN, routeStationsN);
        Path path = TestUtil.tmpFileGenerator(file, stationsN, routesN, routeStationsN);
        return path;
    }
}