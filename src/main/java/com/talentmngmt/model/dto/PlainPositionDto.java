package com.talentmngmt.model.dto;

import com.talentmngmt.model.Position;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class PlainPositionDto {
    private Long id;
    private String name;

    public static PlainPositionDto from(Position position){
        PlainPositionDto positionDto = new PlainPositionDto();

        positionDto.setId(position.getId());
        positionDto.setName(position.getName());

        return positionDto;
    }
}
