package com.talentmngmt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.talentmngmt.model.Employee;
import com.talentmngmt.model.dto.EmployeeDto;
import com.talentmngmt.service.EmployeeService;

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
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/{positionId}/{candidateId}")
    public ResponseEntity<EmployeeDto> createEmployee(@PathVariable(value = "positionId") final Long positionId,
                                                    @PathVariable(value = "candidateId") final Long candidateId,
                                                    @RequestBody final EmployeeDto employeeDto){
        Employee employee = employeeService.createEmployee(positionId, candidateId, Employee.from(employeeDto));
        
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    @PutMapping(value = "/{employeeId}/{positionId}/{candidateId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(value = "employeeId") final Long employeeId,
                                                    @PathVariable(value = "positionId") final Long positionId,
                                                    @PathVariable(value = "candidateId") final Long candidateId,
                                                    @RequestBody final EmployeeDto employeeDto){
        Employee employee = employeeService.updateEmployee(positionId, candidateId, employeeId, Employee.from(employeeDto));
        
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable(value = "id") final Long id){
        Employee employee = employeeService.deleteEmployee(id);

        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable(value = "id") final long id){
        Employee employee = employeeService.getEmployee(id);

        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    @GetMapping(value = {"/query", "/query/{employeeName}", "/query/position/{positionName}"})
    public ResponseEntity<List<EmployeeDto>> getEmployees(
        @PathVariable(value = "employeeName", required = false) final Optional<String> employeeName,
        @PathVariable(value = "positionName", required = false) final Optional<String> positionName){
        List<Employee> employees = new ArrayList<>();
        List<EmployeeDto> employeesDto = new ArrayList<>();
        
        if(employeeName.isPresent()){
            String name = employeeName.get();
            employees = employeeService.getEmployeesByName(name);
            employeesDto = employees.stream().map(EmployeeDto :: from).collect(Collectors.toList());
        
            return new ResponseEntity<>(employeesDto, HttpStatus.BAD_GATEWAY);
        } else if (positionName.isPresent()) {
            String name = positionName.get();
            employees = employeeService.getEmployeesByPosition(name);
            employeesDto = employees.stream().map(EmployeeDto :: from).collect(Collectors.toList());
        
            return new ResponseEntity<>(employeesDto, HttpStatus.BAD_GATEWAY);
        } else {
            employees = employeeService.getEmployees();
            employeesDto = employees.stream().map(EmployeeDto :: from).collect(Collectors.toList());
            
            return new ResponseEntity<>(employeesDto, HttpStatus.OK);
        }
    }
}
