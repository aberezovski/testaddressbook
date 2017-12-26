package com.hybris.caas.interview.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.hybris.caas.interview.exceptions.ResourceNotFoundException;
import com.hybris.caas.interview.model.Address;
import com.hybris.caas.interview.model.Customer;

/**
 * @author SAP Hybris YaaS
 */
public class DefaultAddressService implements AddressService {

    private CustomerService customerService;

    public DefaultAddressService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /*
    public List<Address> getAddressBook(String customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        List<Address> addresses = new ArrayList<>(customer.getAddressBook());
        return addresses;
    }*/
    
    public List<Address> getAddressBook(String customerId) {
        Customer customer = customerService.getCustomerById(customerId);       
        return customer.getAddressBook();
    }

    /**
     * Deletes the address from the customer's address book
     */
    public void deleteAddress(String customerId, String addressId) {
	if (StringUtils.isEmpty(addressId)) {
	    throw new IllegalArgumentException("The provided addressId value '" + addressId + "' is not valid");
	}
	
        Customer customer = customerService.getCustomerById(customerId);
        Address address = findAddressInAddressBook(customer.getAddressBook(), addressId);
        customer.getAddressBook().remove(address);
        customerService.updateCustomer(customer);
    }


    /*
    private Address findAddressInAddressBook(List<Address> addressBook, String addressId) {
        final List<Address> filteredAddresses = new ArrayList<>();
        for (final Address a : addressBook){
            if (a.getId().equals(addressId)){
                filteredAddresses.add(a);
            }
        }

        if (filteredAddresses.size() == 1) {
            return filteredAddresses.get(0);
        } else if (filteredAddresses.size() > 1) {
            throw new IllegalStateException("More than one address of id '" + addressId + "' exist.");
        } else {
            throw new ResourceNotFoundException("Address of id '" + addressId + "' not found.");
        }
    }*/
    
    /**
     * Finds the address in the customer's address book
     */
    private Address findAddressInAddressBook(List<Address> addressBook, String addressId) {
	final List<Address> filteredAddresses = addressBook.stream()
		.filter(a -> StringUtils.equals(a.getId(), addressId))
		.collect(Collectors.toList());

	if (filteredAddresses.size() > 1) {
	    throw new IllegalStateException("More than one address of id '" + addressId + "' exist.");
	} else if (filteredAddresses.isEmpty()) {
	    throw new ResourceNotFoundException("Address of id '" + addressId + "' not found.");
	}

	return filteredAddresses.get(0);
    }
}
