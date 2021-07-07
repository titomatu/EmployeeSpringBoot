package com.talentmngmt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.talentmngmt.exception.CandidateEmployeeExistsException;
import com.talentmngmt.exception.CandidateNotFoundException;
import com.talentmngmt.exception.EmployeeNotFoundException;
import com.talentmngmt.exception.PositionNotFoundException;
import com.talentmngmt.model.Candidate;
import com.talentmngmt.model.Employee;
import com.talentmngmt.model.Position;
import com.talentmngmt.repository.CandidateRepository;
import com.talentmngmt.repository.EmployeeRepository;
import com.talentmngmt.repository.PositionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final CandidateRepository candidateRepository;

    public EmployeeService( EmployeeRepository employeeRepository,
                            PositionRepository positionRepository,
                            CandidateRepository candidateRepository){
        this.candidateRepository = candidateRepository;
        this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Long positionId, Long candidateId, Employee employee){
        employee.setPosition(
            this.positionRepository.findById(positionId)
                    .orElseThrow(() -> new PositionNotFoundException(positionId))
        );

        employee.setPerson(
            this.candidateRepository.findById(candidateId)
                    .orElseThrow(() -> new CandidateNotFoundException(candidateId))
        );

        if(employeeRepository.findByCandidate(candidateId).size() > 0){
            throw new CandidateEmployeeExistsException(candidateId);
        }

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long positionId, Long candidateId, Long employeeId, Employee employee){
        Employee employeeToUpdate = this.employeeRepository.findById(employeeId)
                                            .orElseThrow(() ->
                                                new EmployeeNotFoundException(employeeId)
                                            );

        employeeToUpdate.setSalary(employee.getSalary());

        employeeToUpdate.setPosition(
            this.positionRepository.findById(positionId)
                    .orElseThrow(() -> new PositionNotFoundException(positionId))
        );

        employeeToUpdate.setPerson(
            this.candidateRepository.findById(positionId)
                    .orElseThrow(() -> new CandidateNotFoundException(candidateId))
        );

        return employeeRepository.save(employeeToUpdate);
    }

    public Employee deleteEmployee(Long id){
        Employee employee = this.employeeRepository.findById(id)                           
                                    .orElseThrow(() -> new EmployeeNotFoundException(id));

        employeeRepository.deleteById(id);

        return employee;
    }

    public Employee getEmployee(Long id){
        Employee employee = this.employeeRepository.findById(id)
                                    .orElseThrow(() -> new EmployeeNotFoundException(id));

        return employee;
    }

    public List<Employee> getEmployees(){
        return StreamSupport
                .stream(employeeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByName(String name){
        return StreamSupport
                .stream(employeeRepository.findByName(name).spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeesByPosition(String positionName){
        return StreamSupport
                .stream(employeeRepository.findByPosition(positionName).spliterator(), false)
                .collect(Collectors.toList());
    }
}
