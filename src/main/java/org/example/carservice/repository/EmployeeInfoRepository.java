package org.example.carservice.repository;

import org.example.carservice.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {
    Optional<EmployeeInfo> findEmployeeInfoByEmployeeId(Integer employee_id);
}
