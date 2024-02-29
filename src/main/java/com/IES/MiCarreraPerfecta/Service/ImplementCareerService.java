package com.IES.MiCarreraPerfecta.Service;

import com.IES.MiCarreraPerfecta.Utils.DistanceCoordinates;
import com.IES.MiCarreraPerfecta.Repository.CareerRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class ImplementCareerService {
    
    //**********************************************
    //  Repositorios de tablas
    //**********************************************
    
    @Autowired 
    CareerRepository careerRepository;
    
    @Autowired 
    ImplementBranchService implementBranchService;
    
    @Autowired 
    ImplementResultService implementResultService;
    
    @Autowired 
    ImplementCampusService implementCampusService;
    
    DistanceCoordinates comparador = new DistanceCoordinates();
    
    //**********************************************
    //  Filtros de Carreras: Encuesta
    //**********************************************
    
    // Obtiene las carreras correspondientes a las ramas definitivas
    public List<Integer> getCareerFromBranch(List<Integer> finalBranchList){
        
        List<Integer> careerForBranch = careerRepository.findCareerForBranch(finalBranchList);
        
        return careerForBranch;
    }
    
    // Filtra las carreras de las ramas en base al tipo de gestión
    public List<Integer> getCareerFromManagement(List<Integer> careers, int idManagement){
        
        List<Integer> careerForManagement = careerRepository.findCareerFromManagement(careers, idManagement);
        
        return careerForManagement;
    }
    
    // Filtra las carreras de gestión en base a la modalidad
    public List<Integer> getCareerFromModality(List<Integer> careers, int idModality){
        
        List<Integer> careerFromModality = careerRepository.findCareerFromModality(careers, idModality);
        
        return careerFromModality;
    }
    
    // Obtiene una duración de meses
    public List<Integer> getDuration(int duration) {
        return switch (duration) {
            case 0 -> Arrays.asList(10);
            case 1 -> Arrays.asList(18, 24);
            case 2 -> Arrays.asList(30, 32, 36);
            case 3 -> Arrays.asList(48, 54, 57);
            case 4 -> Arrays.asList(60, 66, 72);
            case 5 -> Arrays.asList(10,18,24,30,32,36,48,54,57,60, 66, 72);
            default -> new ArrayList<>();
        };
    }
    
    // Filtra las carreras de modalidad en base a la duración
    public List<Integer> getCareerFromDuration(List<Integer> careers, List<Integer> duration){
        
        List<Integer> careerFromDuration = careerRepository.findCareerFromDuration(careers, duration);
        
        return careerFromDuration;
    }
    
    public List<Integer> getCareerFromLocation(List<Integer> careers, List<Double> locationUser) {
        List<Integer> careersFromLocation = new ArrayList<>();

        if (locationUser != null && locationUser.size() >= 2) {
            double userLatitude = locationUser.get(0);
            double userLongitude = locationUser.get(1);

            // Obtener las latitudes y longitudes de los campus asociados a las carreras
            List<Object[]> campusLocations = implementCampusService.getLatitudesAndLongitudes(careers);

            // Convertir la lista de arreglos de objetos a un arreglo bidimensional de dobles
            double[][] campusLocationsArray = new double[campusLocations.size()][2];
            for (int i = 0; i < campusLocations.size(); i++) {
                Object[] location = campusLocations.get(i);
                double latitude = (double) location[0];
                double longitude = (double) location[1];
                campusLocationsArray[i] = new double[]{latitude, longitude};
            }

            // Determinar la ubicación más cercana al usuario
            double[] nearestLocation = comparador.ubicacionMasCercana(userLatitude, userLongitude, campusLocationsArray);

            if (nearestLocation != null) {
                double nearestLatitude = (double) nearestLocation[0];
                double nearestLongitude = (double) nearestLocation[1];
                System.out.println("La ubicación más cercana a tus coordenadas es: (" + nearestLatitude + ", " + nearestLongitude + ")");

                // Obtener las carreras asociadas a la ubicación más cercana
                careersFromLocation = careerRepository.findCareerFromLocation(nearestLatitude, nearestLongitude);
            } else {
                careersFromLocation =careers;
            }
        } else {
            careersFromLocation =careers;
        }

        return careersFromLocation;
    }
    
    // Busca coincidencias de datos entre múltiples listas
    public List<Integer> findCommonCareers(List<List<Integer>> lists) {
        if (lists == null || lists.size() < 2) {
            return Collections.emptyList(); // No se puede encontrar coincidencia si hay menos de dos listas
        }

        // Crea un conjunto para almacenar las intersecciones
        Set<Integer> intersection = new HashSet<>(lists.get(0));

        // Iterar sobre las listas restantes y encontrar la intersección con el conjunto actual
        for (int i = 1; i < lists.size(); i++) {
            Set<Integer> currentSet = new HashSet<>(lists.get(i));
            intersection.retainAll(currentSet);
        }

        return new ArrayList<>(intersection);
    }
    
    // Elige carreras en base a diversos filtros 
    public int selectCareers(List<Integer> careersFromBranch, ImplementCareerService implementCareerService, int idManagement, int idModality, int duration, List<Double> userLocation) {

        List<Integer> filteredCareersFromManagement = new ArrayList<>();
        List<Integer> filteredCareersFromModality = new ArrayList<>();
        List<Integer> filteredCareersFromDuration = new ArrayList<>();
        List<Integer> filteredCareersFromLocation = new ArrayList<>();

        if (idManagement != 0) {
            filteredCareersFromManagement = implementCareerService.getCareerFromManagement(careersFromBranch, idManagement);
        } else {
            filteredCareersFromManagement = careersFromBranch;
        }

        if (idModality != 0) {
            filteredCareersFromModality = implementCareerService.getCareerFromModality(careersFromBranch, idModality);
        } else {
            filteredCareersFromModality = careersFromBranch;
        }

        if (duration != 0) {
            filteredCareersFromDuration = implementCareerService.getCareerFromDuration(careersFromBranch, implementCareerService.getDuration(duration));
        } 

        if (userLocation != null && !userLocation.isEmpty()) {
            filteredCareersFromLocation = implementCareerService.getCareerFromLocation(careersFromBranch, userLocation);
        } 

        List<List<Integer>> allLists = Arrays.asList(filteredCareersFromManagement, filteredCareersFromModality, filteredCareersFromDuration, filteredCareersFromLocation);

        List<Integer> fourCommon = findCommonCareers(allLists);

        // Si hay coincidencias en las cuatro listas, seleccionar una carrera
        if (!fourCommon.isEmpty()) {
            return fourCommon.get(0);
        } else {
            // Intentar encontrar coincidencias entre tres listas a la vez
            List<Integer> threeCommon = new ArrayList<>();

            for (int i = 0; i < allLists.size(); i++) {
                List<List<Integer>> subLists = new ArrayList<>(allLists);
                subLists.remove(i);
                threeCommon = findCommonCareers(subLists);
                if (!threeCommon.isEmpty()) {
                    return threeCommon.get(0);
                }
            }

            // Intentar encontrar coincidencias entre dos listas a la vez
            List<Integer> twoCommon = new ArrayList<>();

            for (int i = 0; i < allLists.size(); i++) {
                for (int j = i + 1; j < allLists.size(); j++) {
                    List<Integer> sublist1 = allLists.get(i);
                    List<Integer> sublist2 = allLists.get(j);
                    twoCommon = findCommonCareers(Arrays.asList(sublist1, sublist2));
                    if (!twoCommon.isEmpty()) {
                        return twoCommon.get(0);
                    }
                }
            }

            // Si no se encuentra ninguna coincidencia, devolver -1
            return -1; 
        }    
    }
    
    // Obtiene un objeto con los datos de la carrera perceta
    public List<Object[]> getPerfectCareer(int selectCareer) {
        // Busca la carrera por id
        List<Object[]> career = careerRepository.findCareerDetailsById(selectCareer);
        
        if (career == null) {
            // Manejar el caso en el que no se encuentra la carrera seleccionada
            return null;
        }
        
        return career;
    }
    
    //**********************************************
    //  Filtros de Carreras: Busqueda
    //**********************************************
    
    // Obtiene un objeto con los datos de la carrera perceta
    public List<Object[]> getPerfectCareerByName(String nameCareer) {
        // Eliminar comillas dobles si están presentes
        nameCareer = nameCareer.replaceAll("\"", ""); 
        
        // Busca la carrera por el string que ingrese el usuario
        List<Object[]> career = careerRepository.findCareersByName("%" + nameCareer + "%");
        
        return career;
    }

}