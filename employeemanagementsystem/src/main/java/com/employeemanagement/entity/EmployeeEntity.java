package com.employeemanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Table(name = "employee")
@Validated
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    @NotBlank(message = "Please provide a full name")
    private String employeeFullName;

    @NotBlank(message = "Please provide a firstname")
    private String employeeFirstName;

    @NotBlank(message = "Please provide a lastname")
    private String employeeLastName;

    @Email(message = "Please provide a valid email address")
    private String employeeEmail;
    private Integer employeeAge;
    private String employeeGender;
    private String employeePhoneNumber;
    private Double employeeSalary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "education_id")
    private EducationEntity employeeEducation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_address_id")
    private List<AddressEntity> employeeAddress;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_branch_id")
    private BranchEntity employeeBranch;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_department_id")
    private DepartmentEntity employeeDepartment;

    public EmployeeEntity() {

    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }

    public Double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public EducationEntity getEmployeeEducation() {
        return employeeEducation;
    }

    public void setEmployeeEducation(EducationEntity employeeEducation) {
        this.employeeEducation = employeeEducation;
    }

    public List<AddressEntity> getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(List<AddressEntity> employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public BranchEntity getEmployeeBranch() {
        return employeeBranch;
    }

    public void setEmployeeBranch(BranchEntity employeeBranch) {
        this.employeeBranch = employeeBranch;
    }

    public DepartmentEntity getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(DepartmentEntity employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public EmployeeEntity(long employeeId,
                          String employeeFullName,
                          String employeeFirstName,
                          String employeeLastName,
                          String employeeEmail,
                          Integer employeeAge,
                          String employeeGender,
                          String employeePhoneNumber,
                          Double employeeSalary,
                          EducationEntity employeeEducation,
                          List<AddressEntity> employeeAddress,
                          BranchEntity employeeBranch,
                          DepartmentEntity employeeDepartment) {
        this.employeeId = employeeId;
        this.employeeFullName = employeeFullName;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeEmail = employeeEmail;
        this.employeeAge = employeeAge;
        this.employeeGender = employeeGender;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeSalary = employeeSalary;
        this.employeeEducation = employeeEducation;
        this.employeeAddress = employeeAddress;
        this.employeeBranch = employeeBranch;
        this.employeeDepartment = employeeDepartment;
    }
}
