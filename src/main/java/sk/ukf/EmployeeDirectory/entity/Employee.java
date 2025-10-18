package sk.ukf.EmployeeDirectory.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birth_date;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "job_title")
    private String job_title;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "full_time")
    private Integer full_time;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Date birth_date,
                    String email, String phone, String job_title, Double salary, Integer full_time) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
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
}