package com.talentmngmt.repository;

import com.talentmngmt.model.Candidate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long>{
    
}
