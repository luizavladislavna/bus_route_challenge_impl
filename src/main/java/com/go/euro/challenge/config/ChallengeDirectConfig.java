package com.go.euro.challenge.config;

import com.go.euro.challenge.entity.exception.AppMaxRouteStationsException;
import com.go.euro.challenge.entity.exception.AppMaxRoutesException;
import com.go.euro.challenge.entity.exception.AppMaxStationsException;
import com.go.euro.challenge.entity.exception.AppMinRouteStationsException;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@Configuration
@ConfigurationProperties(prefix = "challenge.direct.limit")
@Data
public class ChallengeDirectConfig {

	private int maxRoutes;
	private int maxStations;
	private int maxRouteStations;
	private int minRouteStations;

	public void checkRouteStationsCounter (int count) {
		if (count > maxRouteStations + 1) {
			throw new AppMaxRouteStationsException(maxRouteStations, count);
		}
		if (count < minRouteStations + 1) {
			throw new AppMinRouteStationsException(minRouteStations, count);
		}
	}

	public void checkStationsCounters (int count) {
		if (count > maxRoutes) {
			throw new AppMaxStationsException(maxRoutes, count);
		}
	}

	public void checkRoutesCounter (int count) {
		if (count > maxStations) {
			throw new AppMaxRoutesException(maxStations, count);
		}
	}

	public void checkMaxRoutes (int count) {
		if (count > maxRoutes) {
			throw new AppMaxRoutesException(maxRoutes, count);
		}
	}
}
