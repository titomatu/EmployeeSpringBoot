package com.talentmngmt.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.talentmngmt.model.Position;
import com.talentmngmt.model.dto.PlainPositionDto;
import com.talentmngmt.model.dto.PositionDto;
import com.talentmngmt.service.PositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService){
        this.positionService = positionService;
    }

    @PostMapping
    public ResponseEntity<PlainPositionDto> addPosition(@RequestBody final PlainPositionDto positionDto){
        Position position = positionService.addPosition(Position.from(positionDto));

        return new ResponseEntity<>(PlainPositionDto.from(position), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PlainPositionDto> editPosition(@PathVariable(name = "id") final Long id,
                                                        @RequestBody final PlainPositionDto positionDto){
        Position position = positionService.editPosition(id, Position.from(positionDto));

        return new ResponseEntity<>(PlainPositionDto.from(position), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PlainPositionDto> deletePosition(@PathVariable(name = "id") final Long id){
        Position position = positionService.deletePosition(id);

        return new ResponseEntity<>(PlainPositionDto.from(position), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PositionDto>> getPositions(){
        List<Position> positions = positionService.getPositions();
        List<PositionDto> positionsDto = positions.stream().map(PositionDto :: from).collect(Collectors.toList());

        return new ResponseEntity<>(positionsDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PlainPositionDto> getPosition(@PathVariable(name = "id") final Long id){
        Position position = positionService.getPosition(id);

        return new ResponseEntity<>(PlainPositionDto.from(position), HttpStatus.OK);
    }
}
