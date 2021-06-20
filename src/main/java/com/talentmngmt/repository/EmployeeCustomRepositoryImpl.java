package com.talentmngmt.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.talentmngmt.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> findByName(String name){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);

        Predicate firstNamePredicate = cb.equal(cb.lower(employee.get("person").get("name")), name.toLowerCase());
        cq.where(firstNamePredicate);

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Employee> findByPosition(String positionName){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);

        Predicate positionNamePredicate = cb.equal(cb.lower(employee.get("position").get("name")), positionName.toLowerCase());
        cq.where(positionNamePredicate);

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Employee> findByCandidate(Long candidateId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> employee = cq.from(Employee.class);

        Predicate employeeByPerson = cb.equal(employee.get("person").get("id"), candidateId);
        cq.where(employeeByPerson);

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        return query.getResultList();       
    }
}
