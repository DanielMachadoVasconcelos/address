/**
 * 
 */
package br.com.fexco.address.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fexco.address.model.Address;
import br.com.fexco.address.service.AddressService;
import br.com.fexco.address.util.AddressWrapper;

/**
 * 
 * <P>
 * Required: We want to install your API service, query it with some Irish
 * Eircodes and receive JSON response with the address details. There is a third
 * party API available (free for limited use) with the information you need.
 * 
 * <p>
 * These are the two endpoints that require implementation.
 * 
 * <li>-
 * https://developers.alliescomputing.com/postcoder-web-api/address-lookup/premise
 * <li>-
 * https://developers.alliescomputing.com/postcoder-web-api/address-lookup/eircode
 * 
 * @author daniel.vasconcelos
 */
@RestController
@RequestMapping("/postcoder-web-api/address-lookup")
public class AddressController {

	private AddressService addressService;

	/**
	 * <p>
	 * End point for Iresh Post Code or Addresses <?>
	 * 
	 * <li>-
	 * https://developers.alliescomputing.com/postcoder-web-api/address-lookup/premise
	 * 
	 * @param fragment
	 *            postcode or address fragment
	 * @param params
	 *            optional parameters. ps: "... uses the third-party API.
	 *            <b>(same API options)</b>"
	 * @return list of address objects
	 */
	@RequestMapping(value = "/eircode/{address}")
	public List<Address> eircode(@PathVariable("address") String fragment,
			@RequestParam(required = false) Map<String, String> params) {

		List<Address> addresses = addressService
				.findEircode(AddressWrapper.instanceOf(Address.from(fragment)).with(params));
		
		return addresses;
	}

	/**
	 * 
	 * <p>
	 * End point for UK Post Code or Addresses <?>
	 * 
	 * <li>-
	 * https://developers.alliescomputing.com/postcoder-web-api/address-lookup/premise
	 * 
	 * @param fragment
	 *            postcode or address fragment
	 * @param params
	 *            optional parameters. ps: "... uses the third-party API. (same
	 *            API options)"
	 * @return list of address objects
	 */
	@RequestMapping(value = "/premise/{address}")
	public List<Address> premisse(@PathVariable("address") String fragment,
			@RequestParam(required = false) Map<String, String> params) {
		
		List<Address> addresses = addressService
				.findPromise(AddressWrapper.instanceOf(Address.from(fragment)).with(params));
		
		return addresses;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

}
