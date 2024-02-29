package com.IES.MiCarreraPerfecta.Request;

import java.util.List;

public class IntelligenceRequest {
    
    private List<Integer> intelligenceResponse;
    private List<Double> locationResponse;

    public List<Integer> getIntelligenceResponse() {
        return intelligenceResponse;
    }

    public void setIntelligenceResponse(List<Integer> intelligenceResponse) {
        this.intelligenceResponse = intelligenceResponse;
    }

    public List<Double> getLocationResponse() {
        return locationResponse;
    }

    public void setLocationResponse(List<Double> locationResponse) {
        this.locationResponse = locationResponse;
    }
    
}