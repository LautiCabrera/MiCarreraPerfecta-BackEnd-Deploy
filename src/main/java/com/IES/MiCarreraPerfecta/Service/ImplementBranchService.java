package com.IES.MiCarreraPerfecta.Service;

import com.IES.MiCarreraPerfecta.Repository.BranchIntelligencesRepository;
import com.IES.MiCarreraPerfecta.Repository.BranchRepository;
import com.IES.MiCarreraPerfecta.Repository.BranchWordsKeyRepository;
import java.util.ArrayList;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
public class ImplementBranchService {

    //**********************************************
    //  Repositorios de tablas
    //**********************************************
    
    @Autowired
    private BranchWordsKeyRepository branchWordsKeyRepository;
    
    @Autowired
    private BranchIntelligencesRepository branchIntelligencesRepository;
    
    //**********************************************
    //  Filtros de Ramas 
    //**********************************************
    
    // Compara listas de palabras claves filtradas 
    public List<Integer> compareList(List<Integer> wordsPreferences, List<Integer> wordsBranches) {
        if (wordsPreferences.size() > wordsBranches.size()) {
            List<Integer> temp = wordsPreferences;
            wordsPreferences = wordsBranches;
            wordsBranches = temp;
        }

        Set<Integer> setWordsBranches = new HashSet<>(wordsBranches);
        List<Integer> wordsBranchesFilter = new ArrayList<>();

        for (Integer word : wordsPreferences) {
            if (setWordsBranches.contains(word)) {
                wordsBranchesFilter.add(word);
            }
        }

        return wordsBranchesFilter;
    }
    
    // Recibe la lista de palabras del controlador y hace la comparación
    public List<Integer> getBranchesFilter(List<Integer> wordsPreferences) {
        // Lista que almacena todas las palabras claves de branch
        List<Integer> wordsBranches = branchWordsKeyRepository.findAllWordKeys();
        // Lista que almacena la palabras claves filtradas
        List<Integer> wordsBranchesFilter = compareList(wordsPreferences, wordsBranches);
        
        return wordsBranchesFilter;
    }
    
    // Obtiene las ramas correspondientes a las palabras claves filtradas
    public List<Integer> getBranches(List<Integer> wordsBranchesFilter){
        // Lista que almacena las ramas correspondientes a las palabras clave filtradas
        List<Integer> branchesForWords = branchWordsKeyRepository.findBranchesByWordKeys(wordsBranchesFilter);
        
        return branchesForWords;
    }
    
    // Filtrado de ramas que le interesan al usuario
    public List<Integer> getBranchesOfInterest(List<Boolean> responses, List<Integer> branchesFilter){
        // Crear una lista para almacenar las ramas de interés
        List<Integer> branchesOfInterest = new ArrayList<>();

        // Iterar sobre las respuestas y la lista de ramas
        for (int i = 0; i < responses.size(); i++) {
            // Verificar si la respuesta en la posición actual es true
            if (responses.get(i)) {
                // Agregar la rama correspondiente a la lista de ramas de interés
                branchesOfInterest.add(branchesFilter.get(i));
            }
        }

        return branchesOfInterest;
    }
    
    // Obtiene las ramas correspondientes a las palabras claves filtradas
    public List<Integer> getBranchesMatching(List<Integer> listaIdBranch, List<Integer> listaIdIntelligence){
        // Lista para almacen de ramas definitivas
            List<Integer> finalBranchList;

             // Se ejecuta si el largo de la lista de ramas es mayor a 2
            if (listaIdBranch.size() > 2 ) {
                List<Integer> branch = branchIntelligencesRepository.findMatchingBranchIntelligence(listaIdBranch, listaIdIntelligence);
                // Se ejecuta si la lista generada por la query está vacia
                if (branch.isEmpty()) {
                    finalBranchList = listaIdBranch;
                    return finalBranchList;
                }
                finalBranchList = branch;
                return finalBranchList;
            } else { // se ejecuta si hay 1 o 2 ramas en la lista 
                finalBranchList = listaIdBranch;
                return finalBranchList;
            }
    }
    
}