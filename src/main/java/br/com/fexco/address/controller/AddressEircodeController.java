/**
 * 
 */
package br.com.fexco.address.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fexco.address.model.Address;
import br.com.fexco.address.service.AddressService;

/**
 * @author daniel.vasconcelos
 */
@RestController
@RequestMapping("/postcoder-web-api/address-lookup")
public class AddressEircodeController {

	private AddressService addressService;

	@RequestMapping(value = "/eircode/{postcode}")
	public Address findOne(@PathVariable String postcode) {
		return addressService.findOne(postcode);
	}
	
	@RequestMapping(value = "/premise/{postcode}")
	public List<Address> listFrom(@PathVariable String postcode) {
		return addressService.listFrom(postcode);
	}

	public AddressService getAddressService() {
		return addressService;
	}

	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}
	
	
	
}
