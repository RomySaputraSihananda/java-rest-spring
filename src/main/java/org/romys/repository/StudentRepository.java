package org.romys.repository;

import java.sql.Timestamp;
import java.util.List;

import org.romys.model.DAO.StudentModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
    Page<StudentModel> findAll(Pageable pageable);

    @Query("SELECT s FROM StudentModel s INNER JOIN s.absen a WHERE a.date BETWEEN :start AND :end")
    List<StudentModel> findAllStudentsWithAbsenInDateRange(@Param("start") Timestamp start,
            @Param("end") Timestamp end);

    @Query("SELECT s FROM StudentModel s INNER JOIN s.absen a WHERE a.date BETWEEN :start AND :end")
    Page<StudentModel> findAllStudentsWithAbsenInDateRange(@Param("start") Timestamp start, @Param("end") Timestamp end,
            Pageable pageable);
}
