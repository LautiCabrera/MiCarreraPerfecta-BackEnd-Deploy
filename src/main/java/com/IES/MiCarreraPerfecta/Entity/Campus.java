package com.IES.MiCarreraPerfecta.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Entity
@Access(AccessType.FIELD)
public class Campus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_campus;
    @NotNull
    private Integer id_university;
    @NotNull
    private String name;
    @NotNull
    private String location;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @NotNull
    private Integer main;
    @NotNull
    private String www;
    @NotNull
    private String email;

    public Campus() {
        
    }

    public Campus(int id_campus, Integer id_university, String name, String location, Double latitude, Double longitude, Integer main, String www, String email) {
        this.id_campus = id_campus;
        this.id_university = id_university;
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.main = main;
        this.www = www;
        this.email = email;
    }
    
}