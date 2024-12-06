package com.java.employee.Service;

import com.java.employee.Entity.EmployeeEntity;
import com.java.employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public EmployeeEntity saveEmployee(EmployeeEntity employee) {

        if (employee.getPhoneNumbers() == null || employee.getPhoneNumbers().isEmpty()) {
            throw new IllegalArgumentException("At least one phone number is required");
        }
        if (employee.getEmployeeId() == null || employee.getFirstName() == null ||
                employee.getLastName() == null || employee.getEmail() == null ||
                employee.getDateOfJoining() == null || employee.getSalary() <= 0) {
            throw new IllegalArgumentException("All fields are mandatory and must be valid");
        }
        return employeeRepository.save(employee);
    }


    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public double calculateTax(double Salary) {
        double tax = 0;
        if (Salary > 250000) {
            double taxableIncome = Math.min(Salary, 500000) - 250000;
            tax += taxableIncome * 0.05;
        }
        if (Salary > 500000) {
            double taxableIncome = Math.min(Salary, 1000000) - 500000;
            tax += taxableIncome * 0.10;
        }
        if (Salary > 1000000) {
            double taxableIncome = Salary - 1000000;
            tax += taxableIncome * 0.20;
        }
        return tax;
    }

    public double calculateCess(double yearlySalary) {
        if (yearlySalary > 2500000) {
            return (yearlySalary - 2500000) * 0.02;
        }
        return 0;
    }

    public double calculateYearlySalary(EmployeeEntity employee) {
        LocalDate currentDate = LocalDate.now();
        LocalDate joiningDate = employee.getDateOfJoining();

        if (joiningDate == null || employee.getSalary() == 0) {
            throw new IllegalArgumentException("Joining date and salary cannot be null");
        }

        int monthsWorked = (int) ChronoUnit.MONTHS.between(joiningDate.withDayOfMonth(1), currentDate.withDayOfMonth(1));

        double partialMonthSalary = 0;
        if (joiningDate.getMonthValue() == currentDate.getMonthValue() && joiningDate.getYear() == currentDate.getYear()) {
            int daysInMonth = joiningDate.lengthOfMonth();
            partialMonthSalary = (daysInMonth - joiningDate.getDayOfMonth() + 1) * (employee.getSalary() / daysInMonth);
        }

        return (monthsWorked * employee.getSalary()) + partialMonthSalary;
    }

    public double calculateTotalSalary(EmployeeEntity employee) {
        double yearlySalary = calculateYearlySalary(employee);
        return yearlySalary + calculateTax(yearlySalary) + calculateCess(yearlySalary);
    }

    public EmployeeEntity getEmployeeTaxDetails(EmployeeEntity employee) {
        double yearlySalary = calculateYearlySalary(employee);
        double tax = calculateTax(yearlySalary);
        double cess = calculateCess(yearlySalary);

        return new EmployeeEntity(
                employee.getEmployeeId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumbers().toString(),
                employee.getDateOfJoining(),
                employee.getSalary()
        );

    }
}
