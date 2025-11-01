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
import sk.ukf.EmployeeDirectory.exception.ObjectNotFoundException;
import sk.ukf.EmployeeDirectory.exception.EmailAlreadyExistsException;

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
        return repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Employee with id " + id + " not found"));
    }
    @Override
    public Employee save(Employee e) {
        String email = e.getEmail();

        if (e.getId() == 0) {
            if (repo.existsByEmail(email)) {
                throw new EmailAlreadyExistsException("Email " + email + " already exists");
            }
        } else { // update
            repo.findByEmail(email).ifPresent(other -> {
                if (other.getId() != e.getId()) {
                    throw new EmailAlreadyExistsException("Email " + email + " already exists");
                }
            });
        }

        return repo.save(e);
    }
    @Override
    public void deleteById(int id) {
        if (!repo.existsById(id)) {
            throw new ObjectNotFoundException("Employee with id " + id + " not found");
        }
        repo.deleteById(id);
    }

}
