package com.talentmngmt.repository;

import com.talentmngmt.model.Employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>, EmployeeCustomRepository {
    
}
