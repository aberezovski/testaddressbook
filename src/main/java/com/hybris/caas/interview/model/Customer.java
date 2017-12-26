package com.hybris.caas.interview.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collections;
import java.util.List;

/**
 * @author SAP Hybris YaaS
 */
public class Customer {

    String id;
    String name;
    List<Address> addressBook;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddressBook() {
        return addressBook == null ? Collections.emptyList() : addressBook;
    }

    public void setAddressBook(List<Address> addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return new EqualsBuilder()
                .append(getId(), customer.getId())
                .append(getName(), customer.getName())
                .append(getAddressBook(), customer.getAddressBook())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .append(getAddressBook())
                .toHashCode();
    }
}
