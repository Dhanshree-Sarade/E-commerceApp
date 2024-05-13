package com.ecommerce.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.entity.Customer;
import com.ecommerce.backend.repository.CustomerRepository;

@Service
public class CustomerService {
	 @Autowired
	    private CustomerRepository customerRepository;

	 public ResponseEntity<Object> saveCustomer(Customer customer) {
	        // Check if a customer with the same email already exists
	        Customer existingCustomer = customerRepository.findByEmail(customer.getEmail());
	        if (existingCustomer != null) {
	            // Customer with this email already exists, return a ResponseEntity with an error message
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                    .body("Customer with email '" + customer.getEmail() + "' already exists");
	        } else {
	            // Customer does not exist, save the new customer
	            Customer savedCustomer = customerRepository.save(customer);
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	        }
	    }

	    public List<Customer> getAllCustomers() {
	        return customerRepository.findAll();
	    }

	    public Customer getCustomerById(Long id) {
	        Optional<Customer> optionalCustomer = customerRepository.findById(id);
	        return optionalCustomer.orElse(null);
	    }

	    public Customer updateCustomer(Long id, Customer customer) {
	        // Check if the customer with the given ID exists
	        Optional<Customer> optionalExistingCustomer = customerRepository.findById(id);
	        if (optionalExistingCustomer.isPresent()) {
	            Customer existingCustomer = optionalExistingCustomer.get();
	            existingCustomer.setFirstName(customer.getFirstName());
	            existingCustomer.setLastName(customer.getLastName());
	            existingCustomer.setEmail(customer.getEmail());
	            existingCustomer.setMobileNumber(customer.getMobileNumber());
	            existingCustomer.setPassword(customer.getPassword());
	            existingCustomer.setAddress(customer.getAddress());
	            existingCustomer.setPincode(customer.getPincode());
	            existingCustomer.setCity(customer.getCity());
	            existingCustomer.setState(customer.getState());
	            return customerRepository.save(existingCustomer);
	        } else {
	            return null; // Customer not found
	        }
	    }

	    public void deleteCustomer(Long id) {
	        customerRepository.deleteById(id);
	    }

}
