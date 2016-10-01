package br.com.fexco.address.service;

import java.util.List;

import br.com.fexco.address.model.Address;
import br.com.fexco.address.util.AddressWrapper;

public interface IEricodeService {
	
	/**
	 * 
	 * @param params 
	 * @param zipcode
	 * @return
	 */
	List<Address> findEircode(AddressWrapper request);
	
	/**
	 * 
	 * @param params 
	 * @param zipcode
	 * @return
	 */
	List<Address> findPremise(AddressWrapper request);
	
}
