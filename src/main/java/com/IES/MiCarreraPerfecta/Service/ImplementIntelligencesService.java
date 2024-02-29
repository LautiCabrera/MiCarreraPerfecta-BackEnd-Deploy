package com.IES.MiCarreraPerfecta.Service;

import com.IES.MiCarreraPerfecta.Repository.IntelligencesRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ImplementIntelligencesService {
    
    //**********************************************
    //  Repositorios de tablas
    //**********************************************
    
    @Autowired
    IntelligencesRepository intelligencesRepository;
    
    //**********************************************
    //  Filtros de Inteligencias 
    //**********************************************
    
    // Obtiene las inteligencias mas desarrolladas del usuario
    public List<Integer> getIntelligences(List<Integer> responseIntelligences) {
        List<Integer> topIntelligences = new ArrayList<>();
        List<Integer> allIntelligences = intelligencesRepository.findAllIntelligences();
        int maxValor = -1;
        
        // Encontrar el valor máximo en la lista de conteo
        for (int i : responseIntelligences) {
            maxValor = Math.max(maxValor, i);
        }
        
        // Determinar las inteligencias más desarrolladas
        for (int i = 0; i < responseIntelligences.size(); i++) {
            if (responseIntelligences.get(i) == maxValor) {
                topIntelligences.add(allIntelligences.get(i)); 
            }
        }
        
        return topIntelligences;
    }
    
}