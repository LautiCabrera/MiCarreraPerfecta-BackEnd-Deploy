package com.IES.MiCarreraPerfecta.Repository;

import com.IES.MiCarreraPerfecta.Entity.PreferenceWordsKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenceWordsKeyRepository extends JpaRepository<PreferenceWordsKey, Integer>{
    
    @Query(value = "SELECT DISTINCT pwk.id_word_key FROM PreferenceWordsKey pwk WHERE pwk.id_preference IN :preferenceIds ORDER BY RAND() LIMIT 10")
    List<Integer> getKeywordIdsForPreferences(@Param("preferenceIds") List<Integer> preferenceIds);
    
}
