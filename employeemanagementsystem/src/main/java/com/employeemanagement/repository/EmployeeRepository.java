package com.employeemanagement.repository;

import com.employeemanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query(value = "SELECT * FROM employee WHERE employee_email = :email", nativeQuery = true)
    EmployeeEntity findByEmail(String email);
}
