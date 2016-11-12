package com.go.euro.challenge.test;

import com.go.euro.challenge.entity.exception.AppInvalidParametersException;
import com.go.euro.challenge.entity.exception.AppUnknownStationsException;
import com.go.euro.challenge.entity.response.DirectBusRoute;
import com.go.euro.challenge.service.DirectBusRouteExistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@Slf4j
@Test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class DirectBusRouteExistenceApiTest extends AbstractTestNGSpringContextTests {

    //String[] exampleArgs = new String[]{"tests\\docker\\example"};

    @Autowired
    @Qualifier("challenge")
    DirectBusRouteExistenceService service;

    @BeforeClass
    public void init() {
        mock(ApplicationArguments.class).getSourceArgs();
    }

    @Test(expectedExceptions = AppUnknownStationsException.class)
    void testDirectBusRouteDefault() {
        assertNotNull(service);
        DirectBusRoute res = service.getDirectBusRouteExistence(1, 2);
    }

    @Test(expectedExceptions = AppInvalidParametersException.class)
    void notValid() {
        assertNotNull(service);
        DirectBusRoute res = service.getDirectBusRouteExistence(106, 106);
    }

    @Test
    void exist() {
        assertNotNull(service);
        DirectBusRoute res = service.getDirectBusRouteExistence(106, 150);
        log.debug("DirectBusRoute: {}", res);
        assertNotNull(res);
        assertTrue(res.isDirect());
    }

    @Test
    void notExist() {
        DirectBusRoute res = service.getDirectBusRouteExistence(138, 17);
        log.info("DirectBusRoute: {}", res);
        assertNotNull(res);
        assertFalse(res.isDirect());
    }
}
