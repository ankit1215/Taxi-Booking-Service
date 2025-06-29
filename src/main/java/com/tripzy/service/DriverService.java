package com.tripzy.service;

import com.tripzy.dto.request.DriverRequest;
import com.tripzy.dto.response.DriverResponse;
import com.tripzy.model.Driver;
import com.tripzy.reposiory.DriverRepository;
import com.tripzy.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public DriverResponse addDriver(DriverRequest driverRequest) {
        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest);
        Driver savedDeriver = driverRepository.save(driver);
        return DriverTransformer.driverToDriverResponse(savedDeriver);
    }
}
