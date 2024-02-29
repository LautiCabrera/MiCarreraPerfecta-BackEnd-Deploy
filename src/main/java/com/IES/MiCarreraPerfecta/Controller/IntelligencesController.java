package com.IES.MiCarreraPerfecta.Controller;

import com.IES.MiCarreraPerfecta.Request.IntelligenceRequest;
import com.IES.MiCarreraPerfecta.Service.ImplementIntelligencesService;
import com.IES.MiCarreraPerfecta.Service.ImplementResultService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/intelligencesFilter")
@CrossOrigin(origins = {"https://lauticabrera.github.io/MiCarreraPerfecta-FrontEnd","https://mi-carrera-perfecta-front-end.vercel.app","http://127.0.0.1:5500"})
public class IntelligencesController {
    
    @Autowired
    private ImplementResultService implementResultService;
    
    @Autowired
    private ImplementIntelligencesService implementIntelligencesService;
    
    @PostMapping("/procesar-respuestas")
    public ResponseEntity<List<Integer>> procesarRespuestas(@RequestBody IntelligenceRequest responses) {
        
        // Verificar si responses es nulo
        if (responses == null) {
            // Manejar el caso cuando responses es nulo
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        // Separa las listas de inteligencias y ubicación del usuario
        List<Integer> intelligencesResponse = responses.getIntelligenceResponse();
        
        List<Double> locationResponse = responses.getLocationResponse();
        
        // Almacena la latitud y longitud de la ubicación del usuario
        implementResultService.setLocationFilter(locationResponse);
        
        // Obtiene y almacena las inteligencias mas desarrolladas del usuario
        List<Integer> intelligences  = implementIntelligencesService.getIntelligences(intelligencesResponse);
        implementResultService.setIntelligenceFilter(intelligences);
        
        return new ResponseEntity<>( HttpStatus.OK);
    }
    
}