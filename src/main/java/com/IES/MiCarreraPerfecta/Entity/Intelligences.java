package com.IES.MiCarreraPerfecta.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter@Setter
@Entity
@Access(AccessType.FIELD)
public class Intelligences {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_intelligences;
    @NotNull
    private String name;
    @NotNull
    private String description;

    public Intelligences() {
        
    }

    public Intelligences(int id_intelligences, String name, String description) {
        this.id_intelligences = id_intelligences;
        this.name = name;
        this.description = description;
    }
    
}