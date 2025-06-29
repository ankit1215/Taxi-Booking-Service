package com.tripzy.service;

import com.tripzy.Enum.Gender;
import com.tripzy.dto.request.CustomerRequest;
import com.tripzy.dto.response.CustomerResponse;
import com.tripzy.exception.CustomerNotFoundException;
import com.tripzy.model.Customer;
import com.tripzy.reposiory.CustomerRepository;
import com.tripzy.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        //RequestDto to Entity conversion
        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);

        // save the entity to db
        Customer savedCustomer = customerRepository.save(customer);

        // Saved entity to Response db
        return CustomerTransformer.customerToCustomerResponse(customer);
    }

    public CustomerResponse getCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Id");
        }

        Customer savedCustomer = optionalCustomer.get();

        // Saved entity to Response db
        return CustomerTransformer.customerToCustomerResponse(savedCustomer);

    }

    public List<CustomerResponse> getAllByGender(Gender gender) {
        List<Customer> customers = customerRepository.findByGender(gender);

        //entity to response dto
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer : customers){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }

    public List<CustomerResponse> getAllByGenderAndAge(Gender gender, int age) {
        List<Customer> customers = customerRepository.findByGenderAndAge(gender, age);

        //entity to response dto
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer : customers){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }

    public List<CustomerResponse> getAllByGenderAndAgeGreaterThan(String gender, int age) {
        List<Customer> customers = customerRepository.getAllByGenderAndAgeGreaterThan(gender, age);

        //entity to response dto
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer : customers){
            customerResponses.add(CustomerTransformer.customerToCustomerResponse(customer));
        }
        return customerResponses;
    }
}
