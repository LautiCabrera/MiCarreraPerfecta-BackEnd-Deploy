package com.IES.MiCarreraPerfecta.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter@Setter
@Entity
@Access(AccessType.FIELD)
public class BranchWordsKey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_branch_word_key;
    @NotNull
    private int id_branch;
    @NotNull
    private int id_word_key;

    public BranchWordsKey() {
        
    }

    public BranchWordsKey(int id_branch_word_key, int id_branch, int id_word_key) {
        this.id_branch_word_key = id_branch_word_key;
        this.id_branch = id_branch;
        this.id_word_key = id_word_key;
    }
    
}