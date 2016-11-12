package com.go.euro.challenge.controller;

import com.go.euro.challenge.entity.exception.ApplicationDefaultException;
import com.go.euro.challenge.entity.response.DirectBusRoute;
import com.go.euro.challenge.service.DirectBusRouteExistenceService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;


/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@RestController
@Slf4j
@RequestMapping("/")
public class IndexController {

	@Autowired
	@Qualifier("challenge")
	DirectBusRouteExistenceService directRouteService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response index () {
		throw new ApplicationDefaultException();
	}

}
