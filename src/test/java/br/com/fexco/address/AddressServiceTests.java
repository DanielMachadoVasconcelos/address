package br.com.fexco.address;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=AddressApplication.class)
@ContextConfiguration(classes = {AddressApplication.class})
public class AddressServiceTests {

	@Test
	public void findByEircode() {
		
	}
}
