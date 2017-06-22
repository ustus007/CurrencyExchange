package test.kapital.merit.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomMapper extends ObjectMapper{

	
	public CustomMapper() {
	    JavaTimeModule javaTimeModule = new JavaTimeModule();
	    registerModule(javaTimeModule);
	    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}
	
	
}
