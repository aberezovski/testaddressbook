package com.hybris.caas.interview.exceptions;

/**
 * @author SAP Hybris YaaS
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
