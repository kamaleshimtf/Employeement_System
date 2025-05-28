package com.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branch")
public class BranchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long branchId;
    private String branchName;
    private String branchAddress;
    private String branchCity;

    @OneToMany(mappedBy = "employeeBranch", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EmployeeEntity> employees;
}
