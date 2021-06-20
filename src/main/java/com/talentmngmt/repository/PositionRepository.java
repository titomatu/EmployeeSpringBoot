package com.talentmngmt.repository;

import com.talentmngmt.model.Position;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long>{

}
