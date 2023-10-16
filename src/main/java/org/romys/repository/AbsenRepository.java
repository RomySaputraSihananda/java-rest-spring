package org.romys.repository;

import java.util.List;

import org.romys.model.AbsenModel;
import org.romys.model.DTO.AbsenDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenRepository extends JpaRepository<AbsenModel, Long> {
    @Query("SELECT a FROM AbsenModel a WHERE a.student_id = :studentId")
    List<AbsenDTO> findAbsenByStudentId(@Param("studentId") long studentId);

    // List<AbsenModel> findAbsenModelByStudentId(int studentId);
}
