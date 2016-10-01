package br.com.fexco.address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;

import br.com.fexco.address.model.Address;
import br.com.fexco.address.repo.IAddressRepository;
import br.com.fexco.address.util.AddressWrapper;

/**
 * <p>Address service for EirCode (Irish Post Code) using a third party API.
 * 
 * <p> 
 * 
 * @author daniel.vasconcelos
 *
 */
public class AddressService {

	@Autowired
	private IAddressRepository addressRepository;

	private IEricodeService apiService;

	/**
	 * <p>
	 * Retrive address from eircode service.
	 * 
	 * <p>
	 * For minimize cost every address is persisted on database and retrieved
	 * from there on first try.<p>
	 * If not found on database, then the third-party API is requested. If Found this new address is persisted on database.
	 * 
	 * <p>
	 * <b>Required</b>: "Each call to the third party API has a cost of <b>4.5 credits
	 * per request</b>. We expect this API being called by multiple services
	 * that all together add up to one million requests per month...."
	 * 
	 * 
	 * @param address filter object for address service.
	 * @param params additional options for the service.
	 * @return List of address
	 */
	@Cacheable(value = "address-ie")
	public List<Address> findEircode(AddressWrapper request) {
		List<Address> addresses = searchInDataBase(request);
		 
		if (CollectionUtils.isEmpty(addresses)) {
			addresses = apiService.findEircode(request);
			if (!CollectionUtils.isEmpty(addresses)) {
				addressRepository.save(addresses);
			}
		}
		return addresses;
	}
	
	/**
	 * Look in Data Base first, to avoid rest lookup in third-party api. Saving money.
	 * 
	 * @param request 
	 * @return
	 */
	private List<Address> searchInDataBase(AddressWrapper request){

		PageRequest pageRequest = new PageRequest(request.getPage(), 100);
		Page<Address> page = addressRepository.findAll(
				Example.of(request.getAddress(), ExampleMatcher.matching()
						.withMatcher("summaryline", GenericPropertyMatcher.of(StringMatcher.CONTAINING, Boolean.TRUE))), pageRequest);
		
		return page.getContent();
	}

	/**
	 * <p>
	 * Retrive address from uk post code service.
	 * 
	 * <p>
	 * For minimize cost every address is persisted on database and retrieved
	 * from there on first try.<p>
	 * If not found on database, then the third-party API is requested. If Found this new address is persisted on database.
	 * 
	 * <p>
	 * <b>Required</b>: "Each call to the third party API has a cost of <b>4.5 credits
	 * per request</b>. We expect this API being called by multiple services
	 * that all together add up to one million requests per month...."
	 * 
	 * 
	 * @param address filter object for address service.
	 * @param params additional options for the service.
	 * @return List of address
	 */
//	@Cacheable(value = "address-uk")
	public List<Address> findPromise(AddressWrapper request) {
		List<Address> addresses = searchInDataBase(request);
		 
		if (CollectionUtils.isEmpty(addresses)) {
			addresses = apiService.findPremise(request);
			if (!CollectionUtils.isEmpty(addresses)) {
				addressRepository.save(addresses);
			}
		}
		return addresses;
	}

	
	
	public IAddressRepository getAddressRepository() {
		return addressRepository;
	}

	public void setAddressRepository(IAddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	public IEricodeService getApiService() {
		return apiService;
	}

	public void setApiService(IEricodeService apiService) {
		this.apiService = apiService;
	}

}
