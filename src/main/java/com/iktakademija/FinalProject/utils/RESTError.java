package com.iktakademija.FinalProject.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.securities.Views;

@JsonView(Views.Public.class)
public class RESTError {

	@JsonIgnore
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@JsonProperty(value = "ErrorCode")
	private ERESTErrorCodes code;

	public RESTError() {
		super();
	}

	public RESTError(ERESTErrorCodes code) {
		super();
		this.code = code;
	}

	public String getCode() {
		String message = String.format("REST Error E%d - %s", code.getValue(), code.getMessage());
		logger.warn(message);
		return String.format(message);
	}

}
