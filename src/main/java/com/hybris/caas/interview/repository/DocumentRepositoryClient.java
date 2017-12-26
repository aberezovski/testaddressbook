package com.hybris.caas.interview.repository;

import com.hybris.caas.interview.model.Customer;

/**
 * @author SAP Hybris YaaS
 */
public interface DocumentRepositoryClient {

    Customer getCustomer(String id);

    void putCustomer(Customer customer);

}
