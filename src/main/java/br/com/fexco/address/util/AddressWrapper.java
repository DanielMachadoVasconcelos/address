package br.com.fexco.address.util;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import br.com.fexco.address.model.Address;

public class AddressWrapper implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer lines;
	private String include;
	private String exclude;
	private String format;
	private String addtags;
	private String identifier;
	private String callback;
	private int page;
	private String postcodeonly;
	private Address address;

	public Integer getLines() {
		return lines;
	}

	public void setLines(String lines) {
		if(StringUtils.isNumeric(lines)){
			this.lines = Integer.valueOf(lines);
		}
	}

	public String getInclude() {
		return include;
	}

	public void setInclude(String include) {
		this.include = include;
	}

	public String getExclude() {
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		if(StringUtils.isNotEmpty(format)){
			this.format = format;
		}
	}

	public String getAddtags() {
		return addtags;
	}

	public void setAddtags(String addtags) {
		this.addtags = addtags;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public int getPage() {
		return page;
	}

	public void setPage(String page) {
		if(StringUtils.isNumeric(page)){
			this.page = Integer.valueOf(page);
		}
	}

	public String getPostcodeonly() {
		return postcodeonly;
	}

	public void setPostcodeonly(String postcodeonly) {
		this.postcodeonly = postcodeonly;
	}
	
	private void setAddress(Address address) {
		this.address = address;
	}
	
	public Address getAddress() {
		return address;
	}
	

	public static AddressWrapper instanceOf(Address address){
		AddressWrapper rp = new AddressWrapper();
		rp.setAddress(address);
		return rp;
	}

	public AddressWrapper with(Map<String, String> params) {
		this.setLines(params.get("lines"));
		this.setInclude(params.get("include"));
		this.setExclude(params.get("exclude"));
		this.setFormat(params.get("format"));
		this.setAddtags(params.get("addtags"));
		this.setIdentifier(params.get("identifier"));
		this.setCallback(params.get("callback"));
		this.setPage(params.get("page"));
		this.setPostcodeonly(params.get("postcodeonly"));
		return this;
	}

}
