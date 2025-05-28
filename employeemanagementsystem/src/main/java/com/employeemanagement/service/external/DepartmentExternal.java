package com.employeemanagement.service.external;

import com.employeemanagement.entity.EmployeeEntity;
import java.util.List;

public interface DepartmentExternal {
    List<EmployeeEntity> findByDepartmentName(String name);
}
