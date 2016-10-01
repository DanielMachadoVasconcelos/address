package br.com.fexco.address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;

import br.com.fexco.address.model.Address;
import br.com.fexco.address.repo.AddressRepository;


public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	private IEricodeService ericodeService;
	
	@Cacheable(value = "address")
	public Address findOne(String postcode) {
		Address address = addressRepository.findOne(postcode);
		if(address == null){
			address = ericodeService.findOne(postcode);
		}
		return address;
	}

	public List<Address> listFrom(String postcode) {
		List<Address> addresses = addressRepository.findAll(Example.of(new Address(postcode)));
		if(addresses == null){
			addresses = ericodeService.listFrom(postcode);
		}
		return addresses;
	}

	public AddressRepository getAddressRepository() {
		return addressRepository;
	}

	public void setAddressRepository(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public IEricodeService getEricodeService() {
		return ericodeService;
	}

	public void setEricodeService(IEricodeService ericodeService) {
		this.ericodeService = ericodeService;
	}
	
	
	
}
