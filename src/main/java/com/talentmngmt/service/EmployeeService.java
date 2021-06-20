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
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    CandidateRepository candidateRepository;

    public Employee createEmployee(Long positionId, Long candidateId, Employee employee){
        Optional<Position> positionByID = positionRepository.findById(positionId);
        if(!positionByID.isPresent()){
            throw new PositionNotFoundException(positionId);
        }
        Position position = positionByID.get();  
        employee.setPosition(position);

        Optional<Candidate> candidateByID = candidateRepository.findById(candidateId);
        if(!candidateByID.isPresent()){
            throw new CandidateNotFoundException(candidateId);
        }
        Candidate candidate = candidateByID.get();
        employee.setPerson(candidate);

        List<Employee> employees = employeeRepository.findByCandidate(candidateId);
        if(employees.size() > 0){
            throw new CandidateEmployeeExistsException(candidateId);
        }

        Employee employee1 = employeeRepository.save(employee);

        return employee1;
    }

    public Employee updateEmployee(Long positionId, Long candidateId, Long employeeId, Employee employee){
        Optional<Position> positionByID = positionRepository.findById(positionId);
        if(!positionByID.isPresent()){
            throw new PositionNotFoundException(positionId);
        }
        Position position = positionByID.get(); 

        Optional<Candidate> candidateByID = candidateRepository.findById(candidateId);
        if(!candidateByID.isPresent()){
            throw new CandidateNotFoundException(candidateId);
        }
        Candidate candidate = candidateByID.get();

        Optional<Employee> employeeByID = employeeRepository.findById(employeeId);
        if(!employeeByID.isPresent()){
            throw new EmployeeNotFoundException(employeeId);
        }
        Employee employeeToUpdate = employeeByID.get();

        employeeToUpdate.setSalary(employee.getSalary());
        employeeToUpdate.setPosition(position);
        employeeToUpdate.setPerson(candidate);

        return employeeRepository.save(employeeToUpdate);
    }

    public Employee deleteEmployee(Long id){
        Optional<Employee> employeeByID = employeeRepository.findById(id);
        if(!employeeByID.isPresent()){
            throw new EmployeeNotFoundException(id);
        }
        Employee employee = employeeByID.get();

        employeeRepository.deleteById(id);

        return employee;
    }

    public Employee getEmployee(Long id){
        Optional<Employee> employeeByID = employeeRepository.findById(id);
        if(!employeeByID.isPresent()){
            throw new EmployeeNotFoundException(id);
        }
        Employee employee = employeeByID.get();

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
