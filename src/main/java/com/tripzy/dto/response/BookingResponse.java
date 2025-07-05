package com.tripzy.dto.response;

import com.tripzy.Enum.TripStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {

    private String pickup;

    private String destination;

    private double tripDistanceInKm;

    private TripStatus tripStatus;

    private double billAmount;

    private Date bookedAt;

    private Date lastUpdateAt;

    private CustomerResponse customer;

    private CabResponse cab;
}
