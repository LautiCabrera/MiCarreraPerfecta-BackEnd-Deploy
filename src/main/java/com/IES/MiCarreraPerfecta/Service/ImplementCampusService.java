package com.IES.MiCarreraPerfecta.Service;

import com.IES.MiCarreraPerfecta.Repository.CampusRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementCampusService {
    
    @Autowired CampusRepository campusRepository;
    
    // Obtiene todas las latitudes y longitudes de los campus
    public List<Object[]> getLatitudesAndLongitudes(List<Integer>carrers){
        
        List<Object[]> latitudesAndLongitudes = campusRepository.findLatitudesAndLongitudesByCareerIds(carrers);
        
        return latitudesAndLongitudes;
    }
    
}