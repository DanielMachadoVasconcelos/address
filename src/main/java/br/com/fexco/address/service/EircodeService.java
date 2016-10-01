package br.com.fexco.address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import br.com.fexco.address.model.Address;

public class EircodeService  implements IEricodeService{

	String rootUri = "http://ws.postcoder.com/pcw/"; // set this to a properties file
	private String key ="PCW45-12345-12345-1234X"; // set this to a properties file healm
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public Address findOne(String postcode) {
		StringBuilder sb = new StringBuilder().append(rootUri).append(key).append("/address/ie/").append(postcode).append("?lines=3&format=json");
		return restTemplate.getForObject(sb.toString() , Address.class);
	}

	@Override
	public List<Address> listFrom(String postcode) {
		// TODO Auto-generated method stub
		return null;
	}

}
