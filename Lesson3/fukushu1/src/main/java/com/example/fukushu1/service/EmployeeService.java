package com.example.fukushu1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fukushu1.model.Employee;
import com.example.fukushu1.repository.EmployeeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    //登録
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    //更新
    @Transactional
    public void updateEmployee(Employee updateEmployee) {
        Employee existingEmployee = employeeRepository.findById(updateEmployee.getId())
            .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        existingEmployee.setName(updateEmployee.getName());
        existingEmployee.setCompany(updateEmployee.getCompany());
        employeeRepository.save(existingEmployee);
    }
    //削除
    @Transactional
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee no found"));

            employeeRepository.delete(employee);
    }
}
