package sk.ukf.EmployeeDirectory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.ukf.EmployeeDirectory.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // ничего вручную не добавляем — все CRUD-методы уже есть
}
