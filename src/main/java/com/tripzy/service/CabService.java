package com.tripzy.service;

import com.tripzy.dto.request.CabRequest;
import com.tripzy.dto.response.CabResponse;
import com.tripzy.exception.DriverNotFoundException;
import com.tripzy.model.Cab;
import com.tripzy.model.Driver;
import com.tripzy.reposiory.CabRepository;
import com.tripzy.reposiory.DriverRepository;
import com.tripzy.transformer.CabTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabService {

    @Autowired
    DriverRepository driverRepository;

    public CabResponse registerCab(CabRequest cabRequest, int driverId) {

        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if(optionalDriver.isEmpty()){
            throw new DriverNotFoundException("Invalid Driver Id");
        }
        Driver driver = optionalDriver.get();
        Cab cab = CabTransformer.cabRequestToCab(cabRequest);
        driver.setCab(cab);

        //this will save both driver and cab
        Driver savedDrived = driverRepository.save(driver);

        return CabTransformer.cabToCabResponse(savedDrived.getCab(), savedDrived);

    }
}
