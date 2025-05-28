package com.employeemanagement.service.external;

import com.employeemanagement.entity.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeExternal {
    public EmployeeEntity addEmployee(EmployeeEntity employee);

    List<EmployeeEntity> getAllEmployees();

    EmployeeEntity updateEmployee(Long employeeId, EmployeeEntity updateEmployee);

    EmployeeEntity deleteEmployee(Long employeeId);

    EmployeeEntity getEmployee(Long employeeId);

}
