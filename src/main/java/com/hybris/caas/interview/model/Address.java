package com.hybris.caas.interview.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author SAP Hybris YaaS
 */
public class Address {
    String id;
    String street;
    String streetNumber;
    String zipCode;
    String city;
    String state;
    String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return new EqualsBuilder()
                .append(getId(), address.getId())
                .append(getStreet(), address.getStreet())
                .append(getStreetNumber(), address.getStreetNumber())
                .append(getZipCode(), address.getZipCode())
                .append(getCity(), address.getCity())
                .append(getState(), address.getState())
                .append(getCountry(), address.getCountry())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getStreet())
                .append(getStreetNumber())
                .append(getZipCode())
                .append(getCity())
                .append(getState())
                .append(getCountry())
                .toHashCode();
    }
}
