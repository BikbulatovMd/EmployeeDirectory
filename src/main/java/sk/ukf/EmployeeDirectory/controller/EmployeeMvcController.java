package sk.ukf.EmployeeDirectory.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.ukf.EmployeeDirectory.entity.Employee;
import sk.ukf.EmployeeDirectory.service.EmployeeService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeMvcController {

    private final EmployeeService employeeService;

    // значения для drop-down из application.properties
    @Value("${employee.jobTitles}")
    private String[] jobTitlesProp;

    public EmployeeMvcController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // список
    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/list";
    }

    // детальная
    @GetMapping("/{id}")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/view";
    }

    // форма добавления
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Employee empty = new Employee();
        model.addAttribute("employee", empty);
        model.addAttribute("jobTitles", List.of(jobTitlesProp));
        model.addAttribute("employmentOptions", employmentOptions());
        return "employees/form";
    }

    // сохранение нового (без UPDATE)
    @PostMapping
    public String create(@ModelAttribute("employee") @Valid Employee employee) {
        employee.setId(0); // гарантируем INSERT
        employeeService.save(employee);
        return "redirect:/employees";
    }

    // удаление
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

    // опции для radio
    private List<EmploymentOption> employmentOptions() {
        return Arrays.asList(
                new EmploymentOption(1, "Plný úväzok"),
                new EmploymentOption(2, "Čiastočný úväzok"),
                new EmploymentOption(3, "Dohoda"),
                new EmploymentOption(4, "Stážista/Praktikant")
        );
    }

    public record EmploymentOption(int code, String label) {}
}
