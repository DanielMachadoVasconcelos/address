package br.com.fexco.address.service;

import java.util.Arrays;
import java.util.List;

import br.com.fexco.address.model.Address;

public class EircodeServiceMock implements IEricodeService {

	@Override
	public Address findOne(String postcode) {
		return new Address(postcode);
	}

	@Override
	public List<Address> listFrom(String postcode) {
		return Arrays.asList(new Address(postcode));
	}

}
