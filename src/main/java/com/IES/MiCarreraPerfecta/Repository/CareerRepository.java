package com.IES.MiCarreraPerfecta.Repository;

import com.IES.MiCarreraPerfecta.Entity.Career;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Integer> {
    
    @Query("SELECT DISTINCT id_career FROM Career WHERE id_branch IN (:finalBranchList)")
    List<Integer> findCareerForBranch(@Param("finalBranchList") List<Integer> finalBranchList);
    
    @Query(value = "SELECT DISTINCT c.id_career FROM career c " +
       "JOIN campus_career cc ON c.id_career = cc.id_career " +
       "JOIN campus cp ON cc.id_campus = cp.id_campus " +
       "JOIN university u ON cp.id_university = u.id_university " +
       "WHERE c.id_career IN :idCareerFilter " +
       "AND u.id_management = :idManagement", nativeQuery = true)
    List<Integer> findCareerFromManagement(List<Integer> idCareerFilter, int idManagement);
    
    @Query("SELECT DISTINCT id_career FROM Career WHERE id_career IN :careerFromManagement AND id_modality = :modality")
    List<Integer> findCareerFromModality(@Param("careerFromManagement")List<Integer>careerFromManagement, @Param("modality")int modality);

    @Query("SELECT DISTINCT id_career FROM Career WHERE id_career IN :careerFromModality AND duration_months IN :duration ")
    List<Integer> findCareerFromDuration(@Param("careerFromModality")List<Integer>careerFromModality, @Param("duration")List<Integer>duration);
    
    @Query(value = "SELECT cc.id_career FROM campus c " +
               "JOIN campus_career cc ON c.id_campus = cc.id_campus " +
               "ORDER BY SQRT(POW(c.latitude - :latitude, 2) + POW(c.longitude - :longitude, 2)) " +
               "LIMIT 1", nativeQuery = true)
    List<Integer> findCareerFromLocation(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
    
    @Query(value = "SELECT " +
                   "c.name AS career_name, " +
                   "c.description AS career_description, " +
                   "c.duration_months AS career_duration, " +
                   "tc.name AS typecareer_name, " +
                   "m.modality AS modality_name, " +
                   "b.name AS branch_name, " +
                   "b.description AS branch_description, " +
                   "r.name AS range_name, " +
                   "u.name AS university_name, " +
                   "mg.name_management AS university_management, " +
                   "req.name AS requirement_name, " +
                   "camp.name AS campus_name, " +
                   "camp.location AS campus_location, " +
                   "camp.www AS campus_website, " +
                   "camp.email AS campus_email " +
                   "FROM career c " +
                   "JOIN typecareer tc ON c.id_type_career = tc.id_type_career " +
                   "JOIN modality m ON c.id_modality = m.id_modality " +
                   "JOIN branch b ON c.id_branch = b.id_branch " +
                   "JOIN `range` r ON c.id_range = r.id_range " +
                   "JOIN campus_career cc ON c.id_career = cc.id_career " +
                   "JOIN campus camp ON cc.id_campus = camp.id_campus " +
                   "JOIN university u ON camp.id_university = u.id_university " +
                   "JOIN management mg ON u.id_management = mg.id_management " +
                   "LEFT JOIN requirement req ON cc.id_requirement = req.id_requirement " +
                   "WHERE c.id_career = :id_career LIMIT 1", nativeQuery = true)
    List<Object[]> findCareerDetailsById(Integer id_career);
    
    @Query(value = "SELECT " +
                   "c.name AS career_name, " +
                   "c.description AS career_description, " +
                   "c.duration_months AS career_duration, " +
                   "tc.name AS typecareer_name, " +
                   "m.modality AS modality_name, " +
                   "b.name AS branch_name, " +
                   "b.description AS branch_description, " +
                   "r.name AS range_name, " +
                   "u.name AS university_name, " +
                   "mg.name_management AS university_management, " +
                   "req.name AS requirement_name, " +
                   "camp.name AS campus_name, " +
                   "camp.location AS campus_location, " +
                   "camp.www AS campus_website, " +
                   "camp.email AS campus_email " +
                   "FROM career c " +
                   "JOIN typecareer tc ON c.id_type_career = tc.id_type_career " +
                   "JOIN modality m ON c.id_modality = m.id_modality " +
                   "JOIN branch b ON c.id_branch = b.id_branch " +
                   "JOIN `range` r ON c.id_range = r.id_range " +
                   "JOIN campus_career cc ON c.id_career = cc.id_career " +
                   "JOIN campus camp ON cc.id_campus = camp.id_campus " +
                   "JOIN university u ON camp.id_university = u.id_university " +
                   "JOIN management mg ON u.id_management = mg.id_management " +
                   "LEFT JOIN requirement req ON cc.id_requirement = req.id_requirement " +
                   "WHERE c.name LIKE %:nameCareer%", nativeQuery = true)
    List<Object[]> findCareersByName(@Param("nameCareer") String nameCareer);
    
}