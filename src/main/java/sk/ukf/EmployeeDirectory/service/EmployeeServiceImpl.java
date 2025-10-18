package sk.ukf.EmployeeDirectory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.EmployeeDirectory.dao.EmployeeDAO;
import sk.ukf.EmployeeDirectory.entity.Employee;
import org.springframework.stereotype.Service;
import sk.ukf.EmployeeDirectory.dao.EmployeeRepository;
import sk.ukf.EmployeeDirectory.entity.Employee;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Employee> findAll() {
        return repo.findAll();
    }

    @Override
    public Employee findById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee) {
        return repo.save(employee);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
