package com.go.euro.challenge.service.impl;

import com.go.euro.challenge.entity.exception.AppInvalidParametersException;
import com.go.euro.challenge.entity.exception.AppUnknownStationsException;
import com.go.euro.challenge.entity.response.DirectBusRoute;
import com.go.euro.challenge.repository.RouteStationRepository;
import com.go.euro.challenge.service.DirectBusRouteExistenceService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@Service(value = "challenge")
@Slf4j
@AllArgsConstructor
@Accessors(fluent = true)
public class ChallengeDirectBusRouteExistenceService implements DirectBusRouteExistenceService {

	@Autowired @Getter
	RouteStationRepository repository;

	@Override
	public DirectBusRoute getDirectBusRouteExistence (Integer from, Integer to) {
		vilidateStations(from, to);
		boolean direct_route =
				repository.stationRoutes().get(from)
						.parallelStream()
						.filter(routeID -> repository.routeStations().get(routeID).contains(to))
						.findFirst()
						.isPresent();
		log.debug("direct_route: {};", direct_route);
		return DirectBusRoute.of(from, to, direct_route);
	}

	@Override
	public boolean vilidateStations (Integer from, Integer to) {

		if (nonNull(from) && nonNull(to)) {

			if(from.compareTo(to)==0){
				throw new AppInvalidParametersException();
			}

			boolean fromExist = repository.stationRoutes().containsKey(from);
			boolean toExist = repository.stationRoutes().containsKey(to);
			log.debug("fromExist: {}; toExist: {}", fromExist, toExist);

			if (!fromExist && !toExist) {
				throw new AppUnknownStationsException(from, to);
			} else if (!fromExist) {
				throw new AppUnknownStationsException(from);
			} else if (!toExist) {
				throw new AppUnknownStationsException(to);
			}
			return true;

		} else {
			throw new AppInvalidParametersException();
		}
	}
}
