package com.IES.MiCarreraPerfecta.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter@Setter
@Entity
@Access(AccessType.FIELD)
public class BranchIntelligence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_branch_intelligence;
    @NotNull
    private int id_branch;
    @NotNull
    private int id_intelligence;
    @NotNull
    private int priority;

    public BranchIntelligence() {
        
    }

    public BranchIntelligence(int id_branch_intelligence, int id_branch, int id_intelligence, int priority) {
        this.id_branch_intelligence = id_branch_intelligence;
        this.id_branch = id_branch;
        this.id_intelligence = id_intelligence;
        this.priority = priority;
    }
    
}