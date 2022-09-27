package com.KaanOkul.EmployeeManagementSystem.repository;

import com.KaanOkul.EmployeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
