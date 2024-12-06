package com.java.employee.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String employeeId;

        private String firstName;

        private String lastName;

        private String email;

        private List<String> phoneNumbers;

        private LocalDate dateOfJoining;

        private double salary;
        @Transient
        private double cess;
        @Transient
        private double tax;



        public EmployeeEntity() {

        }

        public EmployeeEntity(String employeeId, String firstName, String lastName, String email, String phoneNumbers, LocalDate dateOfJoining, double salary, double cess, double tax, double cess1) {
                this.employeeId = employeeId;
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phoneNumbers = Collections.singletonList(phoneNumbers);
                this.dateOfJoining = dateOfJoining;
                this.salary = salary;
                this.cess = cess;
                this.tax = tax;
        }
        public Long getId()
                {
                        return id;
                }
        public void setId(Long id)
        {
                this.id = id;
        }
        public String getEmployeeId()
        {
                return employeeId;
        }
        public void setEmployeeId(String employeeId)
        {
                this.employeeId = employeeId;
        }
        public String getFirstName()
        {
                return firstName;
        }
        public void setFirstName(String firstName)
        {
                this.firstName = firstName;
        }
        public String getLastName()
        {
                return lastName;
        }
        public void setLastName(String lastName)
        {
                this.lastName = lastName;
        }
        public String getEmail()
        {
                return email;
        }
        public void setEmail(String email)
        {
                this.email = email;
        }
        public List<String> getPhoneNumbers()
        {
                return phoneNumbers;
        }
        public void setPhoneNumbers(List<String> phoneNumbers)
        {
                this.phoneNumbers = phoneNumbers;
        }
        public LocalDate getDateOfJoining()
        {
                return dateOfJoining;
        }
        public void setDateOfJoining(LocalDate dateOfJoining)
        {
                this.dateOfJoining = dateOfJoining;
        }
        public double getSalary()
        {
                return salary;
        }
        public void setSalary(double salary)
        {
                this.salary = salary;
        }
        // Add getters and setters for tax and cess
        public double getTax() {
                return tax;
        }

        public void setTax(double tax) {
                this.tax = tax;
        }

        public double getCess() {
                return cess;
        }

        public void setCess(double cess) {
                this.cess = cess;
        }
}


