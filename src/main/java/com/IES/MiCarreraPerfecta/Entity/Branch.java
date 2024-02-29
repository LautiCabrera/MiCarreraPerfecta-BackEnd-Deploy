package com.IES.MiCarreraPerfecta.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter@Setter
@Entity
@Access(AccessType.FIELD)
public class Branch {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_branch;
    @NotNull
    private String name;
    @NotNull
    private String description;

    public Branch() {
        
    }

    public Branch(int id_branch, String name, String description) {
        this.id_branch = id_branch;
        this.name = name;
        this.description = description;
    }
    
}
