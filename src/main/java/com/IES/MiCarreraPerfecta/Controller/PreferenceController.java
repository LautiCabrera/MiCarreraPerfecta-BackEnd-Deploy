package com.IES.MiCarreraPerfecta.Controller;

import com.IES.MiCarreraPerfecta.Request.BooleanRequest;
import com.IES.MiCarreraPerfecta.Service.ImplementBranchService;
import com.IES.MiCarreraPerfecta.Service.ImplementPreferenceService;
import com.IES.MiCarreraPerfecta.Service.ImplementResultService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preferenceFilter")
@CrossOrigin(origins = {"https://lauticabrera.github.io/MiCarreraPerfecta-FrontEnd","https://mi-carrera-perfecta-front-end.vercel.app","http://127.0.0.1:5500"})
public class PreferenceController {
    
    @Autowired
    private ImplementPreferenceService implementPreferenceService;
    
    @Autowired
    private ImplementBranchService implementBranchService;
    
    @Autowired
    private ImplementResultService implementResultService;
    
    @PostMapping("/procesar-respuestas")
    public ResponseEntity<List<Integer>> procesarRespuestas(@RequestBody BooleanRequest request) {
        // Lista de respuesta del front
        List<Boolean> responses = request.getResponses();
        
        // Verificar si responses es nulo
        if (responses == null) {
            // Manejar el caso cuando responses es nulo
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Obtener grupos de preferencias de interés
        List<Integer> groupsOfInterest = implementPreferenceService.getPreferencesGroupsOfInterest(responses);

        // Obtener preferencias de interés
        List<Integer> preferencesOfInterest = implementPreferenceService.getFilteredPreferences(groupsOfInterest);

        // Obtener hasta 10 palabras clave relacionadas con las preferencias seleccionadas
        List<Integer> wordsPreferencesFilter = implementPreferenceService.getTopKeywords(preferencesOfInterest);
        
        // Almacena las palabras claves filtradas en una clase interna
        implementResultService.setPreferenceFilter(wordsPreferencesFilter);
        
        // Obtiene las palabras claves de las ramas que coinciden con las de preferencias
        List<Integer> wordsBranchesFilter = implementBranchService.getBranchesFilter(wordsPreferencesFilter);
        
        // Obtiene las ramas correspondiente a las palabras filtradas
        List<Integer> branchesFilter = implementBranchService.getBranches(wordsBranchesFilter);
        
        // Almacena las ramas filtradas en una clase interna
        implementResultService.setPreferenceBranchFilter(branchesFilter);
        
        return new ResponseEntity<>( branchesFilter,HttpStatus.OK);
    }
    
}