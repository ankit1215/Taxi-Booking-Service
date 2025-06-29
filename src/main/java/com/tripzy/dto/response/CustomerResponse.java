package com.tripzy.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class CustomerResponse {

    private String name;

    private int age;

    private String emailId;
}
