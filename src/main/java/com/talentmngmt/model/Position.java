package com.talentmngmt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.talentmngmt.model.dto.PlainPositionDto;
import com.talentmngmt.model.dto.PositionDto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Position")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Position implements Serializable{
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
    private String name;

    @OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    @Override
    public String toString(){
        return "Position{" + "id=" + this.id + ", name='" + this.name + '\'' + "}";
    }

    public static Position from(PositionDto positionDto){
        Position position = new Position();

        position.setId(positionDto.getId());
        position.setName(positionDto.getName());
        position.setEmployees(positionDto.getEmployeesDto().stream().map(Employee :: from).collect(Collectors.toList()));

        return position;
    }

    public static Position from(PlainPositionDto positionDto){
        Position position = new Position();

        position.setId(positionDto.getId());
        position.setName(positionDto.getName());

        return position;
    }
}
