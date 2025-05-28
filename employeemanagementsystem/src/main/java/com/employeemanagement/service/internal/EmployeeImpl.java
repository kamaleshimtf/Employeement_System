package com.employeemanagement.service.internal;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImpl implements EmployeeExternal, DepartmentExternal {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeEntity addEmployee(EmployeeEntity employee) {
        if (employeeRepository.findByEmail(employee.getEmployeeEmail()) == null) {
            return employeeRepository.save(employee);
        }
        throw new ConflictException("Employee already exists");
    }

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        if (!employeeRepository.findAll().isEmpty()) {
            return employeeRepository.findAll();
        }
        throw new NoContentException("Employee list is empty");
    }

    @Override
    public EmployeeEntity updateEmployee(Long employeeId, EmployeeEntity updateEmployee) {
        EmployeeEntity oldEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found"));

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
        return employeeRepository.save(oldEmployee);
    }

    @Override
    public EmployeeEntity deleteEmployee(Long employeeId) {
        EmployeeEntity deleteEmployee = employeeRepository.findById(employeeId).get();
        if (deleteEmployee == null) {
            throw new NotFoundException("Employee not found");
        }
        employeeRepository.delete(deleteEmployee);
        return deleteEmployee;
    }

    @Override
    public EmployeeEntity getEmployee(Long employeeId) {
      if(employeeRepository.findById(employeeId).isEmpty()) {
          throw new NotFoundException("Employee not found");
      }
      return employeeRepository.findById(employeeId).get();
    }


    @Override
    public List<EmployeeEntity> findByDepartmentName(String name) {
       if(departmentRepository.findByDepartmentName(name).isEmpty()) {
           throw new NotFoundException("Department not found");
       }
       return departmentRepository.findByDepartmentName(name);
    }
}
