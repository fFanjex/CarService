package org.example.carservice.repository;

import org.example.carservice.model.SpareRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpareRequestRepository extends JpaRepository<SpareRequest, Long> {
}
