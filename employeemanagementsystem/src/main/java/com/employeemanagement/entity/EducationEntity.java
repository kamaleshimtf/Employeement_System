package com.employeemanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "education")
public class EducationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String collageName;
    private String degreeName;
    private Double degreeScore;
    private Integer degreeYear;

    public EducationEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollageName() {
        return collageName;
    }

    public void setCollageName(String collageName) {
        this.collageName = collageName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Double getDegreeScore() {
        return degreeScore;
    }

    public void setDegreeScore(Double degreeScore) {
        this.degreeScore = degreeScore;
    }

    public Integer getDegreeYear() {
        return degreeYear;
    }

    public void setDegreeYear(Integer degreeYear) {
        this.degreeYear = degreeYear;
    }

    public EducationEntity(Long id,
                           String collageName,
                           String degreeName,
                           Double degreeScore,
                           Integer degreeYear) {
        this.id = id;
        this.collageName = collageName;
        this.degreeName = degreeName;
        this.degreeScore = degreeScore;
        this.degreeYear = degreeYear;
    }
}
