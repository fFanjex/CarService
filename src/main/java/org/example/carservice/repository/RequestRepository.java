package org.example.carservice.repository;

import org.example.carservice.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT COUNT(DISTINCT c.owner) FROM Car c WHERE LOWER(c.brand) = LOWER(:brand)")
    long countDistinctOwnersByCarBrand(@Param("brand") String brand);

    List<Request> findByCar_VinNumber(String vin);

    Page<Request> findAll(Pageable pageable);

    List<Request> findByCar_BrandContainingIgnoreCase(String brand);

    Page<Request> findByCar_BrandContainingIgnoreCase(String brand, Pageable pageable);

}
