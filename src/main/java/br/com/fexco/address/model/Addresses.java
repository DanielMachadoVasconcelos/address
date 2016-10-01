/**
 * 
 */
package br.com.fexco.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author daniel.vasconcelos
 *
 */
@XmlRootElement(name="Addresses") 
@XmlAccessorType(XmlAccessType.FIELD)  
@JacksonXmlRootElement(namespace = "Addresses")
public class Addresses {
    // 
    @XmlElement(name="Address")
    public List<Address> address;

    
	public Addresses() {
		super();
	}

	public Addresses(List<Address> address) {
		this.address = address;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public boolean isEmpty() {
		return CollectionUtils.isEmpty(this.address);
	}
    
}    