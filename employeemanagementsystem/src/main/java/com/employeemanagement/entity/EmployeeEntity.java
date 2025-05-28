package com.employeemanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeId;

    @NotBlank(message = "Please provide a fullname")
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
}
