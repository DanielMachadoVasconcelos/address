package br.com.fexco.address.util;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ResponseFormatter {
	
	
	public static final String toXMLQuietly(Object obj){
		try {
			return new XmlMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return StringUtils.EMPTY;
		}
	}

	public static final String toJsonQuietly(Object obj){
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return StringUtils.EMPTY;
		}
	}
}
