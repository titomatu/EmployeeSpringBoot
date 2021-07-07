package com.talentmngmt.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.talentmngmt.exception.CandidateNotFoundException;
import com.talentmngmt.model.Candidate;
import com.talentmngmt.repository.CandidateRepository;

import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public Candidate createCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public Candidate editCandidate(Long id, Candidate candidate){
        Candidate candidateUpdate = candidateRepository.findById(id)
                                        .orElseThrow(() -> new CandidateNotFoundException(id));
        
        candidateUpdate.setName(candidate.getName());
        candidateUpdate.setLastName(candidate.getLastName());
        candidateUpdate.setAddress(candidate.getAddress());
        candidateUpdate.setCityName(candidate.getCityName());
        candidateUpdate.setCellphone(candidate.getCellphone());

        return candidateRepository.save(candidateUpdate);
    }

    public Candidate deleteCandidate(Long id){
        Candidate candidateDelete = candidateRepository.findById(id)
                                        .orElseThrow(() -> new CandidateNotFoundException(id));
        candidateRepository.delete(candidateDelete);

        return candidateDelete;
    }

    public Candidate getCandidate(Long id){
        Candidate candidate = candidateRepository.findById(id)
                                  .orElseThrow(() -> new CandidateNotFoundException(id));
        return candidate;
    }

    public List<Candidate> getCandidates(){
        return StreamSupport
                    .stream(candidateRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());
            
    }
}