package com.talentmngmt.model.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.talentmngmt.model.Employee;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private double salary;

    private CandidateDto person;
    @JsonIgnore
    private PlainPositionDto position;

    public static EmployeeDto from(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId(employee.getId());
        employeeDto.setSalary(employee.getSalary());

        if(Objects.nonNull(employee.getPerson())){
            employeeDto.setPerson(CandidateDto.from(employee.getPerson()));
        }

        if(Objects.nonNull(employee.getPosition())){
            employeeDto.setPosition(PlainPositionDto.from(employee.getPosition()));
        }
        
        return employeeDto;
    }
}
