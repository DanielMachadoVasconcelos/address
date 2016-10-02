package br.com.fexco.address;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import br.com.fexco.address.model.Address;
import br.com.fexco.address.repo.IAddressRepository;

/**
 * Testing for endpoints. 
 * 
 * Checking if endpoints are working fine 
 * 
 * @author kinfa
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes=AddressApplication.class)
@ContextConfiguration(classes = {AddressApplication.class})
public class AddressEndPointTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));	

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext webApplicationContext;

	@Autowired
	private IAddressRepository repository;
	
	private Address address = new Address();
	
	@Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        this.repository.deleteAll();
        
        address.setSummaryline("Department of Communications, Energy and Natural Resources, Adelaide Road, Dublin 2, D02 X285");
        address.setCounty("Dublin");
        address.setPosttown("Dublin 2");
        address.setStreet("Adelaide Road");
        address.setOrganisation("Allies Computing Ltd");
        address.setPostcode("D02 X285");
        
    }

	/**
	 * Retriving address from rest service using poscode and eircode endpoint
	 *  
	 * @throws Exception
	 */
    @Test
	public void readSingleAddressPostCodeParam() throws Exception{
		 mockMvc.perform(get("/postcoder-web-api/address-lookup/eircode/"
                + address.getPostcode()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
//              .andExpect(matcher for every json property)
                .andDo(MockMvcResultHandlers.print());
	}
    
    /**
	 * Retriving address from rest service with fragment of address information hitting ericode endpoit. 
	 *  
	 * @throws Exception
	 */
    @Test
	public void readSingleAddressFragment() throws Exception{
		 mockMvc.perform(get("/postcoder-web-api/address-lookup/eircode/"
                + address.getPosttown()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
//              .andExpect(matcher for every json property)
                .andDo(MockMvcResultHandlers.print());
	}
    
	/**
	 * Retriving address from rest service with postcode using promise. 
	 *  
	 * @throws Exception 
	 */
    @Test
	public void readListOfAddressFromPostcode() throws Exception{
		 mockMvc.perform(get("/postcoder-web-api/address-lookup/premise/"
                + address.getPostcode()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
//              .andExpect(matcher for every json property, for every object retrived)
                .andDo(MockMvcResultHandlers.print());
	}
    
	/**
	 * Retriving address from rest service with fragment using premise endpoit. 
	 *  
	 * @throws Exception
	 */
    @Test
	public void readListOfAddressFromFragment() throws Exception{
		 mockMvc.perform(get("/postcoder-web-api/address-lookup/premise/"
                + address.getPostcode()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
//              .andExpect(matcher for every json property, for every object retrived)
                .andDo(MockMvcResultHandlers.print());
	}
}
