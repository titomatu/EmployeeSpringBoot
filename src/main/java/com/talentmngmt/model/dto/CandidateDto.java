package com.talentmngmt.model.dto;

import com.talentmngmt.model.Candidate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class CandidateDto {
    private Long id;
    private String name, lastName, address, cityName, cellphone;

    public static CandidateDto from(Candidate candidate){
        CandidateDto candidateDto = new CandidateDto();

        candidateDto.setId(candidate.getId());
        candidateDto.setName(candidate.getName());
        candidateDto.setLastName(candidate.getLastName());
        candidateDto.setAddress(candidate.getAddress());
        candidateDto.setCityName(candidate.getCityName());
        candidateDto.setCellphone(candidate.getCellphone());

        return candidateDto;
    }
}
