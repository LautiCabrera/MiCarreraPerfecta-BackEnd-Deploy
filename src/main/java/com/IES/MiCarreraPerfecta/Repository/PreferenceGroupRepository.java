package com.IES.MiCarreraPerfecta.Repository;

import com.IES.MiCarreraPerfecta.Entity.PreferenceGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceGroupRepository extends JpaRepository<PreferenceGroup, Integer> {
    
    @Query("SELECT pg.id_preference_group FROM PreferenceGroup pg")
    List<Integer> findAllPreferenceGroup();
    
}
