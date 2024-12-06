package com.java.employee.Controller;

import com.java.employee.Entity.EmployeeEntity;
import com.java.employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping ("")
    public ResponseEntity<EmployeeEntity> saveEmployee(@RequestBody EmployeeEntity employee) {
        return ResponseEntity.ok(employeeService.saveEmployee(employee));
    }

    @GetMapping("/tax/{employeeId}")
    public ResponseEntity<EmployeeEntity> getEmployeeTaxDetails(@PathVariable String employeeId) {
        EmployeeEntity employee = employeeService.getAllEmployees().stream()
                .filter(emp -> emp.getEmployeeId().equals(employeeId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        return ResponseEntity.ok(employeeService.getEmployeeTaxDetails(employee));
    }
}
