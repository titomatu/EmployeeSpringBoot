package com.talentmngmt.repository;

import java.util.List;

import com.talentmngmt.model.Employee;

public interface EmployeeCustomRepository {
    List<Employee> findByName(String name);
    List<Employee> findByPosition(String positionName);
    List<Employee> findByCandidate(Long candidateId);
}
