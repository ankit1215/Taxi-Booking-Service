package com.tripzy.dto.request;

import com.tripzy.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerRequest {

    private String name;

    private int age;

    private String emailId;

    private Gender gender;
}
