package org.romys.repository;

import org.romys.model.DAO.AbsenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenRepository extends JpaRepository<AbsenModel, Long> {
}
