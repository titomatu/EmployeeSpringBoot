package com.talentmngmt.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.talentmngmt.exception.PositionNotFoundException;
import com.talentmngmt.model.Position;
import com.talentmngmt.repository.PositionRepository;

import org.springframework.stereotype.Service;


@Service
public class PositionService {
    private final PositionRepository positionRepository;

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
        Position positionUpdate = positionRepository.findById(id)
                                        .orElseThrow(() -> new PositionNotFoundException(id));
        positionUpdate.setName(position.getName());

        return positionRepository.save(positionUpdate);
    }

    public Position deletePosition(Long id){
        Position positionDelete = positionRepository.findById(id)
                                    .orElseThrow(() -> new PositionNotFoundException(id));
        positionRepository.delete(positionDelete);

        return positionDelete;
    }

    public Position getPosition(Long id){
        Position position = positionRepository.findById(id)
                                .orElseThrow(() -> new PositionNotFoundException(id));
        return position;
    }
}
