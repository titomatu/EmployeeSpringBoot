package com.talentmngmt.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.talentmngmt.model.Candidate;
import com.talentmngmt.model.dto.CandidateDto;
import com.talentmngmt.service.CandidateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService){
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<CandidateDto> createCandidate(@RequestBody final CandidateDto candidateDto){
        Candidate candidate = candidateService.createCandidate(Candidate.from(candidateDto));

        return new ResponseEntity<>(CandidateDto.from(candidate), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidateDto> updateCandidate(@PathVariable(value = "id") final Long id,
                                                    @RequestBody final CandidateDto candidateDto){
        Candidate candidate = candidateService.editCandidate(id, Candidate.from(candidateDto));

        return new ResponseEntity<>(CandidateDto.from(candidate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CandidateDto> deleteCandidate(@PathVariable(value = "id") final Long id){
        Candidate candidate = candidateService.deleteCandidate(id);

        return new ResponseEntity<>(CandidateDto.from(candidate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDto> getCandidate(@PathVariable(value = "id") final Long id){
        Candidate candidate = candidateService.getCandidate(id);

        return new ResponseEntity<>(CandidateDto.from(candidate), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CandidateDto>> getCandidates(){
        List<Candidate> candidates = candidateService.getCandidates();
        List<CandidateDto> candidatesDto = candidates.stream().map(CandidateDto :: from).collect(Collectors.toList());

        return new ResponseEntity<>(candidatesDto, HttpStatus.OK);
    }
}
