package org.romys.repository;

import java.sql.Timestamp;
import java.util.List;

import org.romys.model.DAO.AbsenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenRepository extends JpaRepository<AbsenModel, Long> {
    List<AbsenModel> findByDateBetween(Timestamp startDate, Timestamp endDate);

    // @Query("SELECT a FROM AbsenModel a WHERE a.student_id = :studentId")
    // List<AbsenDTO> findAbsenByStudentId(@Param("studentId") long studentId);

    // List<AbsenModel> findAbsenModelByStudentId(int studentId);
}
