package com.IES.MiCarreraPerfecta.Repository;

import com.IES.MiCarreraPerfecta.Entity.BranchWordsKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchWordsKeyRepository extends JpaRepository<BranchWordsKey, Integer> {
    
    @Query("SELECT bw.id_word_key FROM BranchWordsKey bw")
    List<Integer> findAllWordKeys();
    
    @Query(value = "SELECT DISTINCT bwk.id_branch FROM BranchWordsKey bwk WHERE bwk.id_word_key IN :wordKeys")
    List<Integer> findBranchesByWordKeys(@Param("wordKeys") List<Integer> wordKeys);
    
}
