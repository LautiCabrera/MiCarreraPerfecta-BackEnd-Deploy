package com.IES.MiCarreraPerfecta.Repository;

import com.IES.MiCarreraPerfecta.Entity.Intelligences;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IntelligencesRepository extends JpaRepository<Intelligences, Integer> {
    
    @Query("SELECT i.id_intelligences FROM Intelligences i")
    List<Integer> findAllIntelligences();
    
}