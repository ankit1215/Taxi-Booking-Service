package com.tripzy.controller;

import com.tripzy.dto.request.DriverRequest;
import com.tripzy.dto.response.DriverResponse;
import com.tripzy.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    DriverService driverService;

    @PostMapping("/add")
    public DriverResponse addDriver(@RequestBody DriverRequest driverRequest){
        return driverService.addDriver(driverRequest);
    }
}
