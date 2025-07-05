package com.tripzy.transformer;

import com.tripzy.Enum.TripStatus;
import com.tripzy.dto.request.BookingRequest;
import com.tripzy.dto.response.BookingResponse;
import com.tripzy.model.Booking;
import com.tripzy.model.Cab;
import com.tripzy.model.Customer;
import com.tripzy.model.Driver;

public class BookingTransformer {

    public static Booking bookingRequestToBooking(BookingRequest bookingRequest, double perKmRate){
        return Booking.builder()
                .pickup(bookingRequest.getPickup())
                .destination(bookingRequest.getDestination())
                .tripDistanceInKm(bookingRequest.getTripDistanceInKm())
                .tripStatus(TripStatus.IN_PROGRESS)
                .billAmount(bookingRequest.getTripDistanceInKm()*perKmRate)
                .build();
    }

    public static BookingResponse bookingToBookingResponse(Booking booking, Customer customer, Cab cab, Driver driver){
        return BookingResponse.builder()
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .tripDistanceInKm(booking.getTripDistanceInKm())
                .tripStatus(booking.getTripStatus())
                .billAmount(booking.getBillAmount())
                .bookedAt(booking.getBookedAt())
                .lastUpdateAt(booking.getLastUpdateAt())
                .customer(CustomerTransformer.customerToCustomerResponse(customer))
                .cab(CabTransformer.cabToCabResponse(cab, driver))
                .build();
    }
}
