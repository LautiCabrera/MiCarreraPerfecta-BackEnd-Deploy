package com.IES.MiCarreraPerfecta.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
@Access(AccessType.FIELD)
public class PreferenceWordsKey {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_p_w_k;
    @NotNull
    private int id_preference;
    @NotNull
    private int id_word_key;

    public PreferenceWordsKey() {
        
    }

    public PreferenceWordsKey(int id_p_w_k, int id_preference, int id_word_key) {
        this.id_p_w_k = id_p_w_k;
        this.id_preference = id_preference;
        this.id_word_key = id_word_key;
    }
    
}