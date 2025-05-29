package com.employeemanagement.controller;

import com.employeemanagement.entity.EmployeeEntity;
import com.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/employee") 
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeEntity> addEmployee(@Valid @RequestBody EmployeeEntity employee) {
        logger.info("Inside controller: Adding a new employee");
        EmployeeEntity employeeEntity = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeEntity);
    }

    @GetMapping
    public List<EmployeeEntity> getEmployees() {
        logger.info("Inside controller: getting all employees details");
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeEntity> getEmployee(@PathVariable Long employeeId) {
        logger.info("Inside controller: getting all employees details with id: {}", employeeId);
        return ResponseEntity.ok(employeeService.getEmployee(employeeId));
    }


    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeEntity employee) {
        logger.info("Inside controller: updating employee: {}", employeeId);
        EmployeeEntity employeeEntity = employeeService.updateEmployee(employeeId, employee);
        return ResponseEntity.status(HttpStatus.OK).body(employeeEntity);
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable Long employeeId) {
        logger.info("Inside controller: deleting employee: {}", employeeId);
        EmployeeEntity employeeEntity = employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(employeeEntity);
    }

    @GetMapping(path = "department/{departmentName}")
    public List<EmployeeEntity> getEmployeeByDepartment(@PathVariable String departmentName) {
        logger.info("Inside controller: getting all employees by department: {}", departmentName);
        return employeeService.searchDepartment(departmentName);
    }
}
