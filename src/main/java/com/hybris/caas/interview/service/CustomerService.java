package com.hybris.caas.interview.service;

import com.hybris.caas.interview.model.Customer;

/**
 * @author SAP Hybris YaaS
 */
public interface CustomerService {

    /**
     * Returns the specified customer
     */
    Customer getCustomerById(String id);

    /**
     * Updates the specified customer
     */
    void updateCustomer(Customer customer);

}
