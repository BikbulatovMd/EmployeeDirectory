package sk.ukf.EmployeeDirectory.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sk.ukf.EmployeeDirectory.entity.Employee;

import java.util.List;

@Repository
public class EmploeeDAOJpaImpl implements EmployeeDAO {
    private EntityManager entityManager;

    @Autowired
    public EmploeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee ", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }
}