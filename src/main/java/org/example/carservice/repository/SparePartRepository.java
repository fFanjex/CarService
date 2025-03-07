package org.example.carservice.repository;

import org.example.carservice.model.Request;
import org.example.carservice.model.SparePart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Long> {
    List<SparePart> findByPartName(String partName);
    Page<SparePart> findAll(Pageable pageable);
}
