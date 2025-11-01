package sk.ukf.EmployeeDirectory.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sk.ukf.EmployeeDirectory.entity.Employee;
import sk.ukf.EmployeeDirectory.service.EmployeeService;

import java.net.URI;
import java.util.List;

import  org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import sk.ukf.EmployeeDirectory.dto.ApiResponse;
import sk.ukf.EmployeeDirectory.entity.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {
    private final EmployeeService service;
    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Employee>>> getAll() {
        var list = service.findAll();
        return ResponseEntity.ok(ApiResponse.success(list, "Employees fetched successfully"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> getOne(@PathVariable int id) {
        var e = service.findById(id);
        if (e == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Employee with id " + id + " not found"));
        }
        return ResponseEntity.ok(ApiResponse.success(e, "Employee fetched successfully"));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> create(@Valid @RequestBody Employee input,
                                                        UriComponentsBuilder uri) {
        input.setId(0);
        var saved = service.save(input);

        var location = uri.path("/api/employees/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location)
                .body(ApiResponse.success(saved, "Employee created successfully"));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Employee>> update(@PathVariable int id,
                                                        @Valid @RequestBody Employee input) {
        if (service.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Employee with id " + id + " not found"));
        }
        input.setId(id);
        var saved = service.save(input);
        return ResponseEntity.ok(ApiResponse.success(saved, "Employee updated successfully"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable int id) {
        if (service.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Employee with id " + id + " not found"));
        }
        service.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Employee deleted successfully"));
    }

}
