package com.tripzy.controller;

import com.tripzy.dto.request.CabRequest;
import com.tripzy.dto.response.CabResponse;
import com.tripzy.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @PostMapping("/register/driver/{driverId}")
    public CabResponse registerCab(@RequestBody CabRequest cabRequest,
                                   @PathVariable("driverId") int driverId){
        return cabService.registerCab(cabRequest, driverId);
    }
}
