package com.go.euro.challenge.entity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static java.util.Objects.nonNull;

/**
 * Created by tymoshenkol on 10-Nov-16.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ExceptionResponse", description = "Exception Response")
public class ExceptionResponse implements Serializable {

	private static final long serialVersionUID = -1455697708325593458L;
	private String error;
	private String errorDescription;
	private String errorCauseLocalized;

	public ExceptionResponse (Exception ex) {
		setError(ex.getClass().getSimpleName());
		setErrorDescription(ex.getLocalizedMessage());
		setErrorCauseLocalized(nonNull(ex.getCause()) ? ex.getCause().getLocalizedMessage() : null);
	}
}
