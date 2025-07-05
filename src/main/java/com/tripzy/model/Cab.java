package com.tripzy.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "cab")
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cabId;

    private String cabNumber;

    private String cabModel;

    private double perKmRate;

    private boolean available;

}
