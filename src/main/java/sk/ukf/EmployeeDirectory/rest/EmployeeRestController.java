package sk.ukf.EmployeeDirectory.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sk.ukf.EmployeeDirectory.entity.Employee;
import sk.ukf.EmployeeDirectory.service.EmployeeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private final EmployeeService service;

    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    // GET /api/employees — список
    @GetMapping
    public List<Employee> getAll() {
        return service.findAll();
    }

    // GET /api/employees/{id} — один по id
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable int id) {
        Employee e = service.findById(id);
        return (e == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(e);
    }

    // POST /api/employees — создать
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee input, UriComponentsBuilder uri) {
        // гарантируем INSERT, а не случайный UPDATE
        input.setId(0);
        Employee saved = service.save(input);

        URI location = uri.path("/api/employees/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved); // 201 + Location
    }

    // PUT /api/employees/{id} — обновить
    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable int id, @RequestBody Employee input) {
        if (service.findById(id) == null) return ResponseEntity.notFound().build();
        // путь — источник истины
        input.setId(id);
        Employee saved = service.save(input); // save() выполнит UPDATE
        return ResponseEntity.ok(saved);
    }

    // DELETE /api/employees/{id} — удалить
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (service.findById(id) == null) return ResponseEntity.notFound().build();
        service.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}
