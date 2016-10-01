package br.com.fexco.address.service;

import org.springframework.stereotype.Service;

import br.com.fexco.address.model.Address;

@Service
public  class UriEircodeService {
	private String key 			= "PCW45-12345-12345-1234X"; 		// set this to a properties file healm, for default company key
	
	private String rootUri 		= "http://ws.postcoder.com/pcw/"; 	// set this to a properties file
	private String dataset 		= "/address/ie/";
	private String format		= "?lines=3&format=json";
	private Address address;
	
	
	public UriEircodeService(){
		super();
	}
	
	public UriEircodeService addKey(String key){
		this.key = key;
		return this;
	}
	
	public UriEircodeService addAddress(Address address){
		this.address = address;
		return this;
	}
	
	public UriEircodeService xmlFormat(){
		this.format = "?format=xml";
		return this;
	}
	
	public UriEircodeService jsonFormat(){
		this.format = "?format=json";
		return this;
	}
	
	public UriEircodeService ukDataSet(){
		this.dataset = "/address/uk/";
		return this;
	}
	
	public UriEircodeService iePostCode(){
		this.dataset = "/address/ie/";
		return this;
	}
	
	public String build(){
		return new StringBuilder().append(this.rootUri)
				  .append(this.key)
				  .append(this.dataset)
				  .append(this.address)
				  .append(this.format).toString();
	}
}
