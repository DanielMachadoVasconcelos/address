package br.com.fexco.address.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.fexco.address.model.Address;
import br.com.fexco.address.util.AddressWrapper;

public class EircodeService implements IEricodeService {

	@Autowired
	private UriEircodeService uri;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Address> findEircode(AddressWrapper request) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Address[]> response = restTemplate
				.exchange(uri.addAddress(request.getAddress()).build(), 
								HttpMethod.GET, entity,	Address[].class);

		return Arrays.asList(response.getBody());
	}

	@Override
	public List<Address> findPremise(AddressWrapper request) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Address[]> response = restTemplate
				.exchange(uri
					.ukDataSet()
					.addAddress(request.getAddress())
					.build(), HttpMethod.GET, entity, Address[].class);

		return Arrays.asList(response.getBody());
	}

}
