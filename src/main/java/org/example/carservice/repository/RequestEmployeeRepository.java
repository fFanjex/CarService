package org.example.carservice.repository;

import org.example.carservice.model.EmployeeInfo;
import org.example.carservice.model.RequestEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestEmployeeRepository extends JpaRepository<RequestEmployee, Long> {
}
