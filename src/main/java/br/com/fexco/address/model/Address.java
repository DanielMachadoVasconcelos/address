package br.com.fexco.address.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="address")
public class Address {

	
	@Id
	private String postcode;
	private String addressline1;
	private String addressline2;
	private String summaryline;
	private String organisation;
	private String street;
	private String posttown;
	private String county;

	
	
	public Address(String postcode) {
		super();
		this.postcode = postcode;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getSummaryline() {
		return summaryline;
	}

	public void setSummaryline(String summaryline) {
		this.summaryline = summaryline;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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
}
