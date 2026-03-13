package com.bank.digitalWallet.service;

import com.bank.digitalWallet.domain.ELocationType;
import com.bank.digitalWallet.domain.Location;
import com.bank.digitalWallet.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepo;

    public String saveCustomerLocation(Location location, String parentId){

        if(parentId != null){
            Location locationParent =
                    locationRepo.findById(UUID.fromString(parentId)).orElse(null);

            if(locationParent == null){
                return "Parent location not found.";
            }else{
                location.setParent(locationParent);
            }
        }
        if(!location.getType().equals(ELocationType.PROVINCE) && parentId == null){
            return "Parent location is required for non-province locations.";
        }
        boolean checkLocation = locationRepo.existsByCode(location.getCode());
        if(checkLocation){
            return "Location with this code already exists.";
        }

        locationRepo.save(location);
        return "Location saved successfully.";
    }


}

