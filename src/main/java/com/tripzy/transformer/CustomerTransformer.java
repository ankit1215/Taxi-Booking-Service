package com.tripzy.transformer;

import com.tripzy.dto.request.CustomerRequest;
import com.tripzy.dto.response.CustomerResponse;
import com.tripzy.model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){
//        Customer customer = new Customer();
//        customer.setName(customerRequest.getName());
//        customer.setAge(customerRequest.getAge());
//        customer.setEmailId(customerRequest.getEmailId());
//        return customer;
       return Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .emailId(customerRequest.getEmailId())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){
//        CustomerResponse customerResponse = new CustomerResponse();
//        customerResponse.setName(customer.getName());
//        customerResponse.setAge(customer.getAge());
//        customerResponse.setEmailId(customer.getEmailId());
//        return customerResponse;

        return CustomerResponse.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .emailId(customer.getEmailId())
                .build();
    }
}
