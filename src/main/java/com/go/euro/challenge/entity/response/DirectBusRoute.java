package com.go.euro.challenge.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */

@Data
@ApiModel(value = "DirectBusRoute", description = "All properties are required")
public class DirectBusRoute implements Serializable {

	private static final long serialVersionUID = -1925135207411306084L;
	@JsonProperty(value = "dep_sid")
	@NonNull
	@ApiModelProperty(value = "Departure Identity (From `A`)", required = true)
	private Integer from;

	@JsonProperty(value = "arr_sid")
	@NonNull
	@ApiModelProperty(value = "Arrival Identity (To `B`)", required = true)
	private Integer to;

	@JsonProperty(value = "direct_bus_route")
	@NonNull
	@ApiModelProperty(value = "Existence of direct bus route between `A` and `B`", required = true)
	private boolean direct;

	private DirectBusRoute (Integer from, Integer to, Boolean direct) {
		setFrom(from);
		setTo(to);
		setDirect(direct);
	}

	public static DirectBusRoute of (Integer from, Integer to, Boolean direct) {return new DirectBusRoute(from, to, direct);}
}
