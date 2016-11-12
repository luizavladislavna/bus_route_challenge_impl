package com.go.euro.challenge.repository;

import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@Repository
@Getter
@Accessors(fluent = true)
@Scope("singleton")
@Slf4j
public class RouteStationRepository {

	private ConcurrentHashMap<Integer, HashSet<Integer>> stationRoutes = new ConcurrentHashMap<>();
	private ConcurrentHashMap<Integer, HashSet<Integer>> routeStations = new ConcurrentHashMap<>();

	public void show () {
		log.info("Unique routes:\t{}", routeStations().size());
		log.info("Unique stations:\t{}", stationRoutes().size());
		if (log.isDebugEnabled()) {
			routeStations().forEach((x, y) -> {
				log.debug("Route# {} [{}]\tStations: {}", x, y.size(), y);
			});
			stationRoutes().forEach((x, y) -> {
				log.debug("Station# {} [{}]\tRouts: {}", x, y.size(), y);
			});
		}
	}
}
