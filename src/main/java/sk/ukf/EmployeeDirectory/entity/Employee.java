package sk.ukf.EmployeeDirectory.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import jakarta.validation.constraints.*;



@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 255, message = "First name must be between 2 and 255 characters")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 255, message = "Last name must be between 2 and 255 characters")
    @Column(name = "lastName")
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Birth date must be in the past")
    @Column(name = "birth_date")
    private LocalDate birth_date;

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email must have a valid format (e.g. name@example.com)"
    )
    @Column(name = "email")
    private String email;

    @Pattern(
            regexp = "^\\+421\\d{9}$",
            message = "Phone must be in +421XXXXXXXXX format"
    )
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Job title is required")
    @Size(max = 255, message = "Job title must not exceed 255 characters")
    @Column(name = "job_title")
    private String job_title;

    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    @Column(name = "salary")
    private Double salary;

    @NotNull(message = "Full-time status must be specified")
    @Column(name = "full_time")
    private Integer full_time;

    public Employee() {
    }


    public Employee(String firstName, String lastName, LocalDate birth_date, String email, String phone, String job_title, Double salary, Integer full_time) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.birth_date = birth_date;
        this.email = email;
        this.phone = phone;
        this.job_title = job_title;
        this.salary = salary;
        this.full_time = full_time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getFull_time() {
        return full_time;
    }

    public void setFull_time(Integer full_time) {
        this.full_time = full_time;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birth_date=" + birth_date +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", job_title='" + job_title + '\'' +
                ", salary=" + salary +
                ", full_time=" + full_time +
                '}';
    }

    public void setFullTime(int i) {

    }
}