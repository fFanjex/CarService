package org.example.carservice.repository;

import org.example.carservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByVinNumber(String vin);

    @Query("SELECT c.brand AS carBrand, COUNT(c.id) AS carCount " +
            "FROM Car c " +
            "JOIN Request r ON c.id = r.car.id " +
            "GROUP BY c.brand " +
            "ORDER BY carCount DESC")
    List<Object[]> getCarStatistics();
}
