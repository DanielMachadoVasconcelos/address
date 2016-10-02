/**
 * 
 */
package br.com.fexco.address;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.fexco.address.model.Address;
import br.com.fexco.address.repo.IAddressRepository;

/**
 * 
 * Test for address repo.
 * 
 * Testing for repo retrive mechanism, address must be retrived from fragments and also poscode index.
 * 
 * @author kinfall
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AddressApplication.class)
@ContextConfiguration(classes = { AddressApplication.class })
public class AddressRepoTests {

	@Autowired
	private IAddressRepository repository;

	private Address address = new Address();

	@Before
	public void setup() throws Exception {

		repository.deleteAll();
		
		address.setSummaryline(
				"Department of Communications, Energy and Natural Resources, Adelaide Road, Dublin 2, D02 X285");
		address.setCounty("Dublin");
		address.setPosttown("Dublin 2");
		address.setStreet("Adelaide Road");
		address.setOrganisation("Allies Computing Ltd");
		address.setPostcode("D02 X285");

		repository.save(address);
	}
	
	/**
	 * Checking if database can retrive address using posttown fragment. 
	 *  
	 * @throws Exception
	 */
    @Test
	public void readAddressFromDataBaseUsingPosttown() throws Exception{
				
    	Address probe = new Address();
    	probe.setPosttown(address.getPosttown());
    	Example<Address> example = Example.of(probe,
    			ExampleMatcher.matching()
						.withMatcher("posttown",
								GenericPropertyMatcher.of(
										StringMatcher.CONTAINING, Boolean.TRUE)));
		Address one = repository.findOne(example);
		
		assertNotNull(one);
	}
	
	/**
	 * Checking if database can retrive address using street fragment. 
	 *  
	 * @throws Exception
	 */
    @Test
	public void readAddressFromDataBaseUsingStreet() throws Exception{
				
    	Address probe = new Address();
    	probe.setStreet(address.getStreet());
    	Example<Address> example = Example.of(probe,
    			ExampleMatcher.matching()
						.withMatcher("street",
								GenericPropertyMatcher.of(
										StringMatcher.CONTAINING, Boolean.TRUE)));
		Address one = repository.findOne(example);
		
		assertNotNull(one);
	}

	/**
	 * Checking if database can retrive address using postcode index. 
	 *  
	 * @throws Exception
	 */
    @Test
	public void readAddressFromDataBaseUsingPostCode() throws Exception{
				
    	Example<Address> example = Example.of(
			new Address(address.getPostcode()),
    			ExampleMatcher.matching()
						.withMatcher("postcode",
								GenericPropertyMatcher.of(
										StringMatcher.CONTAINING, Boolean.TRUE)));
		Address one = repository.findOne(example);
		
		assertNotNull(one);
	}

}
