package com.IES.MiCarreraPerfecta.Controller;

import com.IES.MiCarreraPerfecta.Request.BooleanRequest;
import com.IES.MiCarreraPerfecta.Service.ImplementBranchService;
import com.IES.MiCarreraPerfecta.Service.ImplementResultService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branchFilter")
@CrossOrigin(origins = {"https://lauticabrera.github.io/MiCarreraPerfecta-FrontEnd","https://mi-carrera-perfecta-front-end.vercel.app","http://127.0.0.1:5500"})
public class BranchController {
    
    @Autowired
    private ImplementBranchService implementBranchService;
    
    @Autowired
    private ImplementResultService implementResultService;

    @PostMapping("/procesar-respuestas")
    public ResponseEntity<List<Integer>> procesarRespuestas(@RequestBody BooleanRequest request) {
        // Almacena lista de respuestas que se enviaron del front
        List<Boolean> responses = request.getResponses();
        
        // Verificar si responses es nulo
        if (responses == null) {
            // Manejar el caso cuando responses es nulo
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        // Obtiene las ramas filtradas en la clase interna
        List<Integer> branchesFilter = implementResultService.getPreferenceBranchFilter();
        
        // Obtiene las inteligencias filtradas en la clase interna
        List<Integer> listaIdIntelligence = implementResultService.getIntelligenceFilter();
        
        // Obtiene las ramas de interes del usuario
        List<Integer> listaIdBranch = implementBranchService.getBranchesOfInterest(responses, branchesFilter);
        
        // Se ejecuta si el largo de la lista de ramas es mayor a 2
        List<Integer> finalBranchList = implementBranchService.getBranchesMatching(listaIdBranch, listaIdIntelligence);
        
        // Almacena la lista de ramas finales filtradas
        implementResultService.setBranchFilter(finalBranchList);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}