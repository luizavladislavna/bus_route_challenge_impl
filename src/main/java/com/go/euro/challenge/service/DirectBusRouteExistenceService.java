package com.go.euro.challenge.service;

import com.go.euro.challenge.entity.response.DirectBusRoute;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
public interface DirectBusRouteExistenceService {
	DirectBusRoute getDirectBusRouteExistence (Integer from, Integer to);
	boolean vilidateStations(Integer from, Integer to);
}
