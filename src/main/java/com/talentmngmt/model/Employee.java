package com.talentmngmt.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.talentmngmt.model.dto.EmployeeDto;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Employee")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Employee implements Serializable{
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    private double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Candidate person;

    private static final Logger log = LoggerFactory.getLogger(Employee.class.getName());


    @Override
    public String toString(){
        return "Emplyee{" + "id=" + this.id + ", position=" + this.position.toString() + 
            ", salary=" + this.salary + "}";
    }

    @PrePersist
    public void prePersist(){
        log.info("PrePersist method called");
    }

    public static Employee from(EmployeeDto employeeDto){
        Employee employee = new Employee();

        employee.setId(employeeDto.getId());
        employee.setSalary(employeeDto.getSalary());

        if(Objects.nonNull(employeeDto.getPerson())){
            employee.setPerson(Candidate.from(employeeDto.getPerson()));
        }

        if(Objects.nonNull(employeeDto.getPosition())){
            employee.setPosition(Position.from(employeeDto.getPosition()));
        }

        return employee;
    }
}
