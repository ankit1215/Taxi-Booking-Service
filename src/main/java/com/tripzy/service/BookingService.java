package com.tripzy.service;

import com.tripzy.dto.request.BookingRequest;
import com.tripzy.dto.response.BookingResponse;
import com.tripzy.exception.CabUnavailableException;
import com.tripzy.exception.CustomerNotFoundException;
import com.tripzy.model.Booking;
import com.tripzy.model.Cab;
import com.tripzy.model.Customer;
import com.tripzy.model.Driver;
import com.tripzy.reposiory.BookingRepository;
import com.tripzy.reposiory.CabRepository;
import com.tripzy.reposiory.CustomerRepository;
import com.tripzy.reposiory.DriverRepository;
import com.tripzy.transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookingResponse bookCab(BookingRequest bookingRequest, int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid Customer Is");
        }
        Customer customer = optionalCustomer.get();
        Cab availableCab = cabRepository.getAvailableCabRandomly();
        if(availableCab==null){
            throw new CabUnavailableException("Sorry! No Cabs Available");
        }
        Booking booking = BookingTransformer.bookingRequestToBooking(bookingRequest, availableCab.getPerKmRate());
        Booking savedBooking = bookingRepository.save(booking);

        availableCab.setAvailable(false);
        customer.getBookings().add(savedBooking);

        Driver driver = driverRepository.getDriverByCabId(availableCab.getCabId());
        driver.getBookings().add(savedBooking);

        Customer savedCustomer = customerRepository.save(customer);
        Driver savedDriver = driverRepository.save(driver);

        sendEmail(savedCustomer);

        return BookingTransformer.bookingToBookingResponse(savedBooking, savedCustomer, availableCab, savedDriver);


    }

    private void sendEmail(Customer customer){

        String text = "Congrats!! " + customer.getName() + "Your cab has been booked!";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("ankut.acciojob@gmail.com");
        simpleMailMessage.setTo(customer.getEmailId());
        simpleMailMessage.setSubject("Cab Booked !!");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }
}
