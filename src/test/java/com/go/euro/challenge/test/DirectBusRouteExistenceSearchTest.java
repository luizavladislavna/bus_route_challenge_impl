package com.go.euro.challenge.test;

import com.go.euro.challenge.entity.exception.AppInvalidParametersException;
import com.go.euro.challenge.entity.exception.AppUnknownStationsException;
import com.go.euro.challenge.entity.response.DirectBusRoute;
import com.go.euro.challenge.repository.RouteStationRepository;
import com.go.euro.challenge.service.DirectBusRouteExistenceService;
import com.go.euro.challenge.service.impl.ChallengeDirectBusRouteExistenceService;
import com.go.euro.challenge.test.util.TestUtil;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;


/**
 * Created by Luiza on 12.11.2016.
 */
public class DirectBusRouteExistenceSearchTest {
    RouteStationRepository repository;
    DirectBusRouteExistenceService service;

    @BeforeTest
    public void initRepo() {
        repository = TestUtil.prepareRepo();
        service = new ChallengeDirectBusRouteExistenceService(repository);
    }

    private DirectBusRoute check(Integer from, Integer to) {
        return service.getDirectBusRouteExistence(from, to);
    }

    //@Test(expectedExceptions = AppUnknownStationsException.class)
    public void notExistStation() {
        check(1, 2);
    }

    //@Test(expectedExceptions = AppUnknownStationsException.class)
    public void notExistStations() {
        check(13, 2);
    }

    @Test(expectedExceptions = AppInvalidParametersException.class)
    public void theSameStations() {
        check(1, 1);
    }

    @Test(expectedExceptions = AppInvalidParametersException.class)
    public void nullInput() {
        check(null, null);
    }

    @Test
    public void directRouteAvailability() {
        // Bidirectional routes support:
        assertTrue(check(1, 3).isDirect());
        assertTrue(check(3, 1).isDirect());

        assertTrue(check(17, 6).isDirect());
        assertTrue(check(15, 11).isDirect());

        assertFalse(check(15, 17).isDirect());
        assertFalse(check(17, 15).isDirect());
    }
}
