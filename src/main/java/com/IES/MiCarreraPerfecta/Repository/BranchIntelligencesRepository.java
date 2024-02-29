package com.IES.MiCarreraPerfecta.Repository;

import com.IES.MiCarreraPerfecta.Entity.BranchIntelligence;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchIntelligencesRepository extends JpaRepository<BranchIntelligence, Integer>{
    
    @Query("SELECT bi.id_branch_intelligence FROM BranchIntelligence bi")
    List<Integer> findAllBranchIntelligences();
    
   @Query("SELECT bi.id_branch FROM BranchIntelligence bi " +
           "WHERE bi.id_branch IN :branchIds AND bi.id_intelligence IN :intelligenceIds " +
           "AND bi.priority = (SELECT MAX(bi2.priority) FROM BranchIntelligence bi2 WHERE bi2.id_branch = bi.id_branch) " +
           "AND bi.priority = (SELECT MAX(bi3.priority) FROM BranchIntelligence bi3 WHERE bi3.id_intelligence = bi.id_intelligence)")
    List<Integer> findMatchingBranchIntelligence(@Param("branchIds") List<Integer> branchIds, @Param("intelligenceIds") List<Integer> intelligenceIds);

}