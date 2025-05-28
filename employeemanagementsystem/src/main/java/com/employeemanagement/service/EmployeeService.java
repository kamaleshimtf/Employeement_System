package com.employeemanagement.service;
import com.employeemanagement.entity.EmployeeEntity;
import com.employeemanagement.service.external.DepartmentExternal;
import com.employeemanagement.service.external.EmployeeExternal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeExternal employeeExternal;
    private final DepartmentExternal departmentExternal;

    public EmployeeService(EmployeeExternal employeeExternal, DepartmentExternal departmentExternal) {
        this.employeeExternal = employeeExternal;
        this.departmentExternal = departmentExternal;
    }

    public EmployeeEntity addEmployee(EmployeeEntity employee) {

        return employeeExternal.addEmployee(employee);
    }

    public List<EmployeeEntity> getAllEmployees() {
        return employeeExternal.getAllEmployees();
    }

    public EmployeeEntity updateEmployee(Long employeeId, EmployeeEntity updateEmployee) {
        return employeeExternal.updateEmployee(employeeId, updateEmployee);
    }

    public EmployeeEntity deleteEmployee(Long employeeId) {
        return employeeExternal.deleteEmployee(employeeId);
    }

    public EmployeeEntity getEmployee(Long employeeId) {
        return employeeExternal.getEmployee(employeeId);
    }

    public List<EmployeeEntity> searchDepartment(String departmentName) {
        return departmentExternal.findByDepartmentName(departmentName);
    }
}
