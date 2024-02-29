package com.IES.MiCarreraPerfecta.Controller;

import com.IES.MiCarreraPerfecta.Service.ImplementCareerService;
import com.IES.MiCarreraPerfecta.Service.ImplementResultService;
import com.IES.MiCarreraPerfecta.Utils.EncryptorGenerator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/careerFilter")
@CrossOrigin(origins = {"https://lauticabrera.github.io/MiCarreraPerfecta-FrontEnd","https://mi-carrera-perfecta-front-end.vercel.app","http://127.0.0.1:5500"})
public class CareerController {

    @Autowired
    private ImplementCareerService implementCareerService;

    @Autowired
    private ImplementResultService implementResultService;
    
    @Autowired
    private EncryptorGenerator encryptor;

    @PostMapping("/procesar-respuestas")
    public ResponseEntity<String> procesarRespuestas(@RequestBody List<Integer> responses) {

        // Verificar si responses es nulo
        if (responses == null) {
            // Manejar el caso cuando responses es nulo
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Obtiene las palabras claves de preferencias almacenadas en la clase interna
        List<Double> LocationFilter = implementResultService.getLocationFilter();

        // Obtiene las ramas almacenadas en la clase interna
        List<Integer> finalBranchList = implementResultService.getBranchFilter();

        // Obtiene las carreras en base a las palabras claves de coincidencia
        List<Integer> careerFromBranch = implementCareerService.getCareerFromBranch(finalBranchList);

        Integer selectCareer = implementCareerService.selectCareers(careerFromBranch, implementCareerService, responses.get(0),responses.get(1), responses.get(2), LocationFilter);

        // Verificar si se encontraron datos
        if (selectCareer != -1) {
            // Encripta el ID de la carrera
            String careerIdEncrypted = encryptor.encrypt(selectCareer);
            
            // Devolver la información de la carrera perfecta
            return new ResponseEntity<>(careerIdEncrypted, HttpStatus.OK);
        } else {
            // Devolver un error 404 si no se encontraron datos
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/obtener-carrera-perfecta/{encryptedCareerId}")
    public ResponseEntity<Object> obtenerCarreraPerfecta(@PathVariable String encryptedCareerId) {
        // Desencripta el ID de la carrera
        int careerId = encryptor.decrypt(encryptedCareerId);
        
        // Suponiendo que implementCareerService.getPerfectCareer() devuelve la información de la carrera perfecta
        List<Object[]> career = implementCareerService.getPerfectCareer(careerId);

        // Verificar si se encontraron datos
        if (career != null && !career.isEmpty()) {
            // Devolver la información de la carrera perfecta
            return new ResponseEntity<>(career, HttpStatus.OK);
        } else {
            // Devolver un error 404 si no se encontraron datos
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/busqueda-carrera")
    public ResponseEntity<Object> procesarRespuestas(@RequestBody String nameCareer) {

        // Verificar si responses es nulo
        if (nameCareer == null) {
            // Manejar el caso cuando responses es nulo
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        // Encripta el ID de la carrera
         List<Object[]> careerSearch = implementCareerService.getPerfectCareerByName(nameCareer);
        
        return new ResponseEntity<>( careerSearch,HttpStatus.OK);
    }

}