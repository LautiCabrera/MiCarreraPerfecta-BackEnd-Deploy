package com.IES.MiCarreraPerfecta.Repository;

import com.IES.MiCarreraPerfecta.Entity.Campus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CampusRepository extends JpaRepository <Campus, Long>{
    
    @Query(value = "SELECT c.latitude, c.longitude FROM campus c JOIN campus_career cc ON c.id_campus = cc.id_campus JOIN career cr ON cc.id_career = cr.id_career WHERE cr.id_career IN :careerIds", nativeQuery = true)
    List<Object[]> findLatitudesAndLongitudesByCareerIds(@Param("careerIds") List<Integer> careerIds);
    
}