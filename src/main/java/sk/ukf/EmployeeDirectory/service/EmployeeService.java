package sk.ukf.EmployeeDirectory.service;

import sk.ukf.EmployeeDirectory.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteById(int id);
}
