package br.com.fexco.address.service;

import java.util.List;

import br.com.fexco.address.model.Address;

public interface IEricodeService {
	
	/**
	 * 
	 * @param zipcode
	 * @return
	 */
	Address findOne(String postcode);
	
	/**
	 * 
	 * @param zipcode
	 * @return
	 */
	List<Address> listFrom(String postcode);
	
}
