package com.IES.MiCarreraPerfecta.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Access(AccessType.FIELD)
public class Preference {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_preference;
    @NotNull
    private int id_preference_group;
    @NotNull
    private String name;

    public Preference() {
        
    }

    public Preference(int id_preference, int id_preference_group, String name) {
        this.id_preference = id_preference;
        this.id_preference_group = id_preference_group;
        this.name = name;
    }
    
}
