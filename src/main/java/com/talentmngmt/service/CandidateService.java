package com.talentmngmt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.talentmngmt.exception.CandidateNotFoundException;
import com.talentmngmt.model.Candidate;
import com.talentmngmt.repository.CandidateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
    }

    public Candidate createCandidate(Candidate candidate){
        return candidateRepository.save(candidate);
    }

    public Candidate editCandidate(Long id, Candidate candidate){
        Optional<Candidate> candidateById = candidateRepository.findById(id);
        if(!candidateById.isPresent()){
            throw new CandidateNotFoundException(id);
        }
        Candidate candidateUpdate = candidateById.get();
        candidateUpdate.setName(candidate.getName());
        candidateUpdate.setLastName(candidate.getLastName());
        candidateUpdate.setAddress(candidate.getAddress());
        candidateUpdate.setCityName(candidate.getCityName());
        candidateUpdate.setCellphone(candidate.getCellphone());

        return candidateRepository.save(candidateUpdate);
    }

    public Candidate deleteCandidate(Long id){
        Optional<Candidate> candidateById = candidateRepository.findById(id);
        if(!candidateById.isPresent()){
            throw new CandidateNotFoundException(id);
        }
        Candidate candidateDelete = candidateById.get();
        candidateRepository.delete(candidateDelete);

        return candidateDelete;
    }

    public Candidate getCandidate(Long id){
        Optional<Candidate> candidateById = candidateRepository.findById(id);
        if(!candidateById.isPresent()){
            throw new CandidateNotFoundException(id);
        }
        Candidate candidate = candidateById.get();
        return candidate;
    }

    public List<Candidate> getCandidates(){
        return StreamSupport
                    .stream(candidateRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());
            
    }
}