package com.iktakademija.FinalProject.controllers.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.controllers.utils.enums.ERESTMessageCodes;
import com.iktakademija.FinalProject.securities.Views;

@JsonView(Views.Public.class)
public class RESTMessage {

	@JsonIgnore
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@JsonProperty(value = "MessageCode")
	private ERESTMessageCodes code;

	public RESTMessage() {
		super();
	}

	public RESTMessage(ERESTMessageCodes code) {
		super();
		this.code = code;
	}

	public String getCode() {
		String message = String.format("REST Message M%d - %s", code.getValue(), code.getMessage());
		logger.info(message);
		return String.format(message);
	}

}
