package com.IES.MiCarreraPerfecta.Service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImplementResultService {

    // inteligencias
    private List<Integer> intelligenceFilter;
    // latitud y longitud
    private List<Double> locationFilter;
    // palabras claves de preferencias
    private List<Integer> preferenceFilter;
    // ramas que se filtraron
    private List<Integer> preferenceBranchFilter;
    // ramas de interés
    private List<Integer> branchFilter;
    
    public List<Integer> getIntelligenceFilter() {
        return intelligenceFilter;
    }

    public List<Double> getLocationFilter() {
        return locationFilter;
    }

    public List<Integer> getPreferenceFilter() {
        return preferenceFilter;
    }

    public List<Integer> getPreferenceBranchFilter() {
        return preferenceBranchFilter;
    }

    public List<Integer> getBranchFilter() {
        return branchFilter;
    }

    public void setIntelligenceFilter(List<Integer> intelligenceFilter) {
        this.intelligenceFilter = intelligenceFilter;
    }

    public void setLocationFilter(List<Double> locationFilter) {
        this.locationFilter = locationFilter;
    }

    public void setPreferenceFilter(List<Integer> preferenceFilter) {
        this.preferenceFilter = preferenceFilter;
    }

    public void setPreferenceBranchFilter(List<Integer> preferenceBranchFilter) {
        this.preferenceBranchFilter = preferenceBranchFilter;
    }

    public void setBranchFilter(List<Integer> branchFilter) {
        this.branchFilter = branchFilter;
    }

}