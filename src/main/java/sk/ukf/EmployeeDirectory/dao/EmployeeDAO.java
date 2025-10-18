package sk.ukf.EmployeeDirectory.dao;

import sk.ukf.EmployeeDirectory.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

//    Student findById(int id);
//
//    Student save(Student student);
//
//    void deleteById(int id);
}