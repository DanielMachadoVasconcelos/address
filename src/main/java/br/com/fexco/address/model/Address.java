package br.com.fexco.address.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.fexco.address.util.PostCodeValidator;

@Document(collection = "address")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = 1L;
	
	@Id
	private String postcode;
    private String organisation;
	private String premise;
	private String dependentstreet;
	private String street;
	private String doubledependentlocality;
	private String dependentlocality;
	private String posttown;
	private String county;
	private String summaryline;
	
	public Address(String postcode) {
		this.postcode = postcode;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getPremise() {
		return premise;
	}

	public void setPremise(String premise) {
		this.premise = premise;
	}

	public String getDependentstreet() {
		return dependentstreet;
	}

	public void setDependentstreet(String dependentstreet) {
		this.dependentstreet = dependentstreet;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDoubledependentlocality() {
		return doubledependentlocality;
	}

	public void setDoubledependentlocality(String doubledependentlocality) {
		this.doubledependentlocality = doubledependentlocality;
	}

	public String getDependentlocality() {
		return dependentlocality;
	}

	public void setDependentlocality(String dependentlocality) {
		this.dependentlocality = dependentlocality;
	}

	public String getPosttown() {
		return posttown;
	}

	public void setPosttown(String posttown) {
		this.posttown = posttown;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getSummaryline() {
		return summaryline;
	}

	public void setSummaryline(String summaryline) {
		this.summaryline = summaryline;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(postcode).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Address other = (Address) obj;
		return new EqualsBuilder().append(other.postcode, this.postcode).isEquals();
	}

	/**
	 * <p>
	 * For indexed search database useing poscode address identifier rather
	 * summaryline.
	 * 
	 * @param fragment
	 *            postcode or address fragment
	 * @return Empty Address object with postcode or summary line filled.
	 */
	public static Address from(String fragment) {
		if (new PostCodeValidator().isValid(fragment, null)) {
			return new Address(fragment);
		}

		Address address = new Address();
		address.setSummaryline(fragment);
		return address;
	}
}
