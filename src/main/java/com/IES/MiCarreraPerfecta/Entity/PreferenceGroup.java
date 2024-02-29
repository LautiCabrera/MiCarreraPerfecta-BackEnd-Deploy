package com.IES.MiCarreraPerfecta.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Access(AccessType.FIELD)
public class PreferenceGroup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_preference_group;
    @NotNull
    private String group;

    public PreferenceGroup() {
        
    }

    public PreferenceGroup(int id_preference_group, String group) {
        this.id_preference_group = id_preference_group;
        this.group = group;
    }
    
}