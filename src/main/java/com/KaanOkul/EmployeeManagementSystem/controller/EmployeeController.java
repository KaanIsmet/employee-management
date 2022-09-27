package com.KaanOkul.EmployeeManagementSystem.controller;

import com.KaanOkul.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.KaanOkul.EmployeeManagementSystem.model.Employee;
import com.KaanOkul.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // create employee rest api
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(employee);
    }

    //update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not exist with id:" + id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(employeeDetails.getDepartment());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    //delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee not exist with id: " + id));
        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



