package com.luv2code.springboot.cruddemo.dao;

import com.luv2code.springboot.cruddemo.entity.Employee;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //used to indicate that the class provides the mechanism for storage,
// retrieval, search, update and delete operation on objects

public class EmployeeDAOJpaImpl implements EmployeeDAO{
    //define field for entity manager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theManager){
        this.entityManager=theManager;
    }

    @Override
    public List<Employee> findAll() {

        //create a query
        TypedQuery<Employee> allEmployees= entityManager.createQuery("from Employee",Employee.class);

        //execute query and put it in result list
        List<Employee> employees=allEmployees.getResultList();

        //return result list
        return employees;
    }

    @Override
    public Employee findById(int id) {
        Employee newEmp=entityManager.find(Employee.class,id);

        return newEmp;
    }

    @Override
    public Employee save(Employee theEmployee) {

        Employee dbEmployee=entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {

        Employee emp=entityManager.find(Employee.class,id);

        entityManager.remove(emp);

    }
}
