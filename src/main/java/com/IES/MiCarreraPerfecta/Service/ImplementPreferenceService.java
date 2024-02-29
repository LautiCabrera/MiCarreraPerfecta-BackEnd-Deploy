package com.IES.MiCarreraPerfecta.Service;

import com.IES.MiCarreraPerfecta.Repository.PreferenceGroupRepository;
import com.IES.MiCarreraPerfecta.Repository.PreferenceRepository;
import com.IES.MiCarreraPerfecta.Repository.PreferenceWordsKeyRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ImplementPreferenceService {
    
     //**********************************************
    //  Repositorios de tablas
    //**********************************************
    
    @Autowired
    private PreferenceGroupRepository preferenceGroupRepository;
    
    @Autowired
    private PreferenceRepository preferenceRepository;
    
    @Autowired
    private PreferenceWordsKeyRepository preferenceWordsKeyRepository;
    
    //**********************************************
    //  Filtros de Preferencias 
    //**********************************************
    
    // Filtrado de grupos de preferencias que le interesan al usuario
    public List<Integer> getPreferencesGroupsOfInterest(List<Boolean> responses) {
        // Obtener todas los grupos de preferencia desde la base de datos
        List<Integer> allPreferencesGroups = preferenceGroupRepository.findAllPreferenceGroup();
        // Crear una lista para almacenar los grupos de interés
        List<Integer> preferencesGroupsOfInterest = new ArrayList<>();

        // Iterar sobre cada grupo de preferencia y comparar con las respuestas
        for (int i = 0; i < allPreferencesGroups.size(); i++) {
            Integer preferenceGroup = allPreferencesGroups.get(i);
            // Agregar el grupo a la lista de interés si la respuesta es "True"
            if (responses.get(i)) {
                preferencesGroupsOfInterest.add(preferenceGroup);
            }
        }
        
        return preferencesGroupsOfInterest;
    }

    // Filtro para obtener preferencias basadas en los grupos filtrados
    public List<Integer> getFilteredPreferences(List<Integer> preferencesGroupsOfInterest) {
        // Obtener las preferencias desde el repository utilizando la consulta personalizada
        List<Integer> filteredPreferences = preferenceRepository.getPreferencesByGroupsOfInterest(preferencesGroupsOfInterest);

        return filteredPreferences;
    }

    // Función para obtener hasta 10 palabras clave aleatorias relacionadas con las preferencias seleccionadas
    public List<Integer> getTopKeywords(List<Integer> filteredPreferences) {
        // Obtener todas las IDs de palabras clave relacionadas con las preferencias filtradas
        List<Integer> keywordIds = preferenceWordsKeyRepository.getKeywordIdsForPreferences(filteredPreferences);
        
        // Revolver la lista de IDs para obtener una selección aleatoria
        Collections.shuffle(keywordIds);

        // Limitar la lista a 10 elementos o al tamaño de la lista, lo que sea menor
        int limit = Math.min(keywordIds.size(), 10);
        return keywordIds.subList(0, limit);
    }

}