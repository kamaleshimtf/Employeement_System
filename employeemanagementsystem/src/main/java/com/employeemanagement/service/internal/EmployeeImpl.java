package com.employeemanagement.service.internal;

import com.employeemanagement.controller.EmployeeController;
import com.employeemanagement.entity.AddressEntity;
import com.employeemanagement.entity.DepartmentEntity;
import com.employeemanagement.entity.EmployeeEntity;
import com.employeemanagement.exception.ConflictException;
import com.employeemanagement.exception.NoContentException;
import com.employeemanagement.exception.NotFoundException;
import com.employeemanagement.repository.DepartmentRepository;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.service.external.DepartmentExternal;
import com.employeemanagement.service.external.EmployeeExternal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImpl implements EmployeeExternal, DepartmentExternal {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        String employeeEmail = employee.getEmployeeEmail();

        logger.debug("Inside service class : check if employee email exists {}", employeeEmail);

        if (employeeRepository.findByEmail(employeeEmail) == null) {
            logger.info("Inside service class : Successfully added employee : {}", employeeEmail);
            logger.debug("Inside service class : Employee added before save {}", employee);
            return employeeRepository.save(employee);
        }
        logger.error("Inside service class : employee already exists : {}", employee.getEmployeeEmail());
        throw new ConflictException("Employee already exists");
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();

        if (!employees.isEmpty()) {
            logger.info("Inside service class : Successfully retrieved all employees");
            return employees;
        }
        logger.warn("Inside service class : No employee found");
        throw new NoContentException("Employee list is empty");
    }

    @Override
    public EmployeeEntity updateEmployee(Long employeeId, EmployeeEntity updateEmployee) {
        logger.debug("Inside service class : update employee with Id {}", employeeId);
        EmployeeEntity oldEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> {
                    logger.warn("Inside service class : No employee found with id {}", employeeId);
                    return new NotFoundException("Employee not found");
                });

        List<AddressEntity> newEmployeeAddressList = new ArrayList<>();
        for (AddressEntity newEmployeeAddressEntity : updateEmployee.getEmployeeAddress()) {
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setAddressId(newEmployeeAddressEntity.getAddressId());
            addressEntity.setCity(newEmployeeAddressEntity.getCity());
            addressEntity.setCountry(newEmployeeAddressEntity.getCountry());
            addressEntity.setState(newEmployeeAddressEntity.getState());
            addressEntity.setZipCode(newEmployeeAddressEntity.getZipCode());
            addressEntity.setStreetName(newEmployeeAddressEntity.getStreetName());
            addressEntity.setStreetNumber(newEmployeeAddressEntity.getStreetNumber());
            newEmployeeAddressList.add(addressEntity);
        }

        oldEmployee.setEmployeeFirstName(updateEmployee.getEmployeeFirstName());
        oldEmployee.setEmployeeLastName(updateEmployee.getEmployeeLastName());
        oldEmployee.setEmployeeEducation(updateEmployee.getEmployeeEducation());
        oldEmployee.setEmployeeAddress(newEmployeeAddressList);
        oldEmployee.setEmployeeEmail(updateEmployee.getEmployeeEmail());
        oldEmployee.setEmployeeAge(updateEmployee.getEmployeeAge());
        oldEmployee.setEmployeeFullName(updateEmployee.getEmployeeFullName());
        oldEmployee.setEmployeeBranch(updateEmployee.getEmployeeBranch());
        oldEmployee.setEmployeeGender(updateEmployee.getEmployeeGender());
        oldEmployee.setEmployeeSalary(updateEmployee.getEmployeeSalary());
        oldEmployee.setEmployeeDepartment(updateEmployee.getEmployeeDepartment());
        logger.info("Inside service class : update employee with Id {}", employeeId);
        return employeeRepository.save(oldEmployee);
    }

    @Override
    public EmployeeEntity deleteEmployee(Long employeeId) {
        EmployeeEntity deleteEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> {
            logger.warn("Inside service class : Delete employee id not matching with employee id {}", employeeId);
            return new NotFoundException("Employee not found");
        });

        logger.info("Inside service class : Delete employee with Id {}", employeeId);
        employeeRepository.delete(deleteEmployee);
        return deleteEmployee;
    }

    @Override
    public EmployeeEntity getEmployee(Long employeeId) {
        logger.debug("Inside service class : getEmployee with Id {}", employeeId);
        return employeeRepository.findById(employeeId).orElseThrow(()->{
            logger.warn("Inside service class : Employee not found");
             return new NotFoundException("Employee not found");
        });
    }


    @Override
    public List<EmployeeEntity> findByDepartmentName(String name) {
        List<EmployeeEntity> employeeEntities = departmentRepository.findByDepartmentName(name);
        if(employeeEntities.isEmpty()) {
            throw new NotFoundException("Department not found");
        }
        return employeeEntities;
    }
}
