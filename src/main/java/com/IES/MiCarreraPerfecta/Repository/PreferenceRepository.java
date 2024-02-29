package com.IES.MiCarreraPerfecta.Repository;

import com.IES.MiCarreraPerfecta.Entity.Preference;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceRepository extends JpaRepository<Preference, Integer>{
    
    @Query(value = "SELECT id_preference FROM preference p WHERE p.id_preference_group IN :groupsOfInterest", nativeQuery = true)
    List<Integer> getPreferencesByGroupsOfInterest(@Param("groupsOfInterest") List<Integer> groupsOfInterest);
    
}
