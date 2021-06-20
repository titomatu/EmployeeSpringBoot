package com.talentmngmt.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.talentmngmt.model.Position;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class PositionDto {
    private Long id;
    private String name;
    private List<EmployeeDto> employeesDto = new ArrayList<>();

    public static PositionDto from(Position position){
        PositionDto positionDto = new PositionDto();

        positionDto.setId(position.getId());
        positionDto.setName(position.getName());
        positionDto.setEmployeesDto(position.getEmployees().stream().map(EmployeeDto :: from).collect(Collectors.toList()));
        
        return positionDto;
    }
}
