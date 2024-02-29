package com.IES.MiCarreraPerfecta.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Access(AccessType.FIELD)
public class Career {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_career;
    @NotNull
    private String name;
    @NotNull
    private int title_intermediate;
    @NotNull
    private String description;
    @NotNull
    private int duration_months;
    @NotNull
    private int id_type_career;
    @NotNull
    private int id_modality;
    @NotNull
    private int id_branch;
    @NotNull
    private int id_range;

    public Career() {
        
    }

    public Career(int id_career, String name, int title_intermediate, String description, int duration_months, int id_type_career, int id_modality, int id_branch, int id_range) {
        this.id_career = id_career;
        this.name = name;
        this.title_intermediate = title_intermediate;
        this.description = description;
        this.duration_months = duration_months;
        this.id_type_career = id_type_career;
        this.id_modality = id_modality;
        this.id_branch = id_branch;
        this.id_range = id_range;
    }
    
}
