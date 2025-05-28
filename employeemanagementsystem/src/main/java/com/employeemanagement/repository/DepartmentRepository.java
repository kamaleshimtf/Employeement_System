package com.employeemanagement.repository;

import com.employeemanagement.entity.DepartmentEntity;
import com.employeemanagement.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    @Query(value = "SELECT * FROM employee e INNER JOIN department d ON e.employee_department_id = d.department_id WHERE d.department_name = :departmentName", nativeQuery = true)
    List<EmployeeEntity> findByDepartmentName(String departmentName);
}
