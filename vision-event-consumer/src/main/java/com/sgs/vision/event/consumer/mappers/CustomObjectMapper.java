package com.sgs.vision.event.consumer.mappers;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomObjectMapper extends ObjectMapper{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3336599609869139214L;

	public CustomObjectMapper() {
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
    }

	

}
