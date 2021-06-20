package com.talentmngmt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.talentmngmt.exception.PositionNotFoundException;
import com.talentmngmt.model.Position;
import com.talentmngmt.repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PositionService {
    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository){
        this.positionRepository = positionRepository;
    }

    public List<Position> getPositions(){
        return StreamSupport
                .stream(positionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Position addPosition(Position position){
        return positionRepository.save(position);
    }

    public Position editPosition(Long id, Position position){
        Optional<Position> positionByID = positionRepository.findById(id);
        if(!positionByID.isPresent()){
            throw new PositionNotFoundException(id);
        }

        Position positionUpdate = positionByID.get();
        positionUpdate.setName(position.getName());

        return positionRepository.save(positionUpdate);
    }

    public Position deletePosition(Long id){
        Optional<Position> positionByID = positionRepository.findById(id);
        if(!positionByID.isPresent()){
            throw new PositionNotFoundException(id);
        }

        Position positionDelete = positionByID.get();
        positionRepository.delete(positionDelete);

        return positionDelete;
    }

    public Position getPosition(Long id){
        Optional<Position> positionByID = positionRepository.findById(id);
        if(!positionByID.isPresent()){
            throw new PositionNotFoundException(id);
        }

        Position position = positionByID.get();
        return position;
    }
}
