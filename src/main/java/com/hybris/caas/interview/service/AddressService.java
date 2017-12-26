package com.hybris.caas.interview.service;

import com.hybris.caas.interview.model.Address;

import java.util.List;

/**
 * @author SAP Hybris YaaS
 */
public interface AddressService {

    /**
     * Returns the address book for the customer
     */
    List<Address> getAddressBook(String customerId);

    /**
     * Deletes the address from the customer's address book
     */
    void deleteAddress(String customerId, String addressId);

}
