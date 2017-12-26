package com.hybris.caas.interview.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit5.extension.MockitoExtension;
import org.mockito.Mock;

import com.hybris.caas.interview.exceptions.ResourceNotFoundException;
import com.hybris.caas.interview.model.Address;
import com.hybris.caas.interview.model.Customer;
import com.hybris.caas.interview.repository.DocumentRepositoryClient;

/**
 * @author alexandru.berezovski
 */
@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("Testing DefaultAddressService")
class DefaultAddressServiceTest {

    private static final String CUSTOMER_ID = "CustID#1";
    private static final String ADDRESS_ID = "AdrID#1";

    @Mock
    private DocumentRepositoryClient documentClient;

    private AddressService service;

    private Customer customer;

    @BeforeEach
    public void setup() {
	service = new DefaultAddressService(new DefaultCustomerService(documentClient));
	customer = mockCustomer();
	when(documentClient.getCustomer(CUSTOMER_ID)).thenReturn(customer);
    }
    
    @Test
    @DisplayName("throws IllegalArgumentException when try to retrieve address book using a not valid customer's id")
    public void getAddressBookForNotValidCustomerId() {
	String nonValidCustomerId = null;

	Throwable exception = assertThrows(IllegalArgumentException.class,
		() -> service.getAddressBook(nonValidCustomerId),
		String.format("The test must fail due the not valid customer id '%s'", nonValidCustomerId));

	assertEquals("The provided customer id value '" + nonValidCustomerId + "' is not valid", exception.getMessage());
    }

    @Test
    @DisplayName("throws ResourceNotFoundException when try to retrieve address book for not found customer")
    public void getAddressBookForNotFoundCustomer() {
	String nonExistingCustomerId = "-1";

	Throwable exception = assertThrows(ResourceNotFoundException.class,
		() -> service.getAddressBook(nonExistingCustomerId),
		String.format("The test must fail due the non existing customer of id '%s'", nonExistingCustomerId));

	assertEquals("Customer of id '" + nonExistingCustomerId + "' not found.", exception.getMessage());
    }

    @Test
    @DisplayName("Successful retrieve address from the address book")
    public void getAddressBookHappyFlow() {
	List<Address> expectedAddressBook = mockAddressBook();
	List<Address> actualAddressBook = service.getAddressBook(CUSTOMER_ID);

	assertEquals(expectedAddressBook, actualAddressBook,
		"The actual address book must be the same as the expected one.");
    }
    
    @Test
    @DisplayName("throws IllegalArgumentException when try to delete address by using not valid address id")
    public void deleteAddressUsingNotValidAddressId() {
	String notValidAddressId = null;
	
	Throwable exception = assertThrows(IllegalArgumentException.class,
		() -> service.deleteAddress(CUSTOMER_ID, notValidAddressId),
		String.format("The test must fail due not valid address id '%s'.", notValidAddressId));

	assertEquals("The provided addressId value '" + notValidAddressId + "' is not valid", exception.getMessage());
    }

    @Test
    @DisplayName("Successful delete address from the address book")
    public void deleteAddressHappyFlow() {
	List<Address> actualAddressBook = service.getAddressBook(CUSTOMER_ID);
	assertEquals(1, actualAddressBook.size(), "The customer's address book must contain only one address");

	service.deleteAddress(CUSTOMER_ID, ADDRESS_ID);

	actualAddressBook = service.getAddressBook(CUSTOMER_ID);
	assertEquals(0, actualAddressBook.size(), "The customer's address book must be empty");
    }

    @Test
    @DisplayName("throws IllegalStateException when try to delete redundant address (2 ore more addresses with same id)")
    public void deleteRedundantAddress() {
	// add duplicate addresses to the address book
	customer.getAddressBook().addAll(mockAddressBook());

	Throwable exception = assertThrows(IllegalStateException.class,
		() -> service.deleteAddress(CUSTOMER_ID, ADDRESS_ID),
		String.format("The test must fail due more than one address of same id '%s' exist", ADDRESS_ID));

	assertEquals("More than one address of id '" + ADDRESS_ID + "' exist.", exception.getMessage());
    }

    @Test
    @DisplayName("throws ResourceNotFoundException when try to delete the not found address")
    public void deleteMissingAddress() {
	customer.getAddressBook().remove(0);

	Throwable exception = assertThrows(ResourceNotFoundException.class,
		() -> service.deleteAddress(CUSTOMER_ID, ADDRESS_ID),
		String.format("The test must fail due none address of id '%s' was found", ADDRESS_ID));

	assertEquals("Address of id '" + ADDRESS_ID + "' not found.", exception.getMessage());
    }
    
    @AfterEach
    void tearDown() {
	service = null;
	customer = null;
	documentClient = null;
    }

    private Customer mockCustomer() {
	Customer cust = new Customer();
	cust.setId(CUSTOMER_ID);
	cust.setName("CustomerName#1");
	cust.setAddressBook(mockAddressBook());

	return cust;
    }

    private List<Address> mockAddressBook() {
	List<Address> addressBook = new LinkedList<>();

	Address adr = new Address();
	addressBook.add(adr);
	adr.setId(ADDRESS_ID);
	adr.setStreet("Street#1");
	adr.setStreetNumber("Nr.1");
	adr.setZipCode("11111");
	adr.setCity("City#1");
	adr.setState("State#1");
	adr.setCountry("Country#1");

	return addressBook;
    }

}