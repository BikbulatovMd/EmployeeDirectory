package sk.ukf.EmployeeDirectory.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.ukf.EmployeeDirectory.entity.Employee;
import sk.ukf.EmployeeDirectory.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeMvcController {

    private final EmployeeService employeeService;

    @Value("${employee.jobTitles}")
    private String[] jobTitlesProp;

    public EmployeeMvcController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("jobTitles", List.of(jobTitlesProp));
        model.addAttribute("employmentOptions", employmentOptions());
        return "employees/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("jobTitles", List.of(jobTitlesProp));
        model.addAttribute("employmentOptions", employmentOptions());
        return "employees/form";
    }

    @PostMapping
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("jobTitles", List.of(jobTitlesProp));
            model.addAttribute("employmentOptions", employmentOptions());
            return "employees/form";
        }
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "employees/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "employees/view";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }

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
