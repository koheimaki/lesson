package com.example.fukushu1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fukushu1.model.Employee;
import com.example.fukushu1.service.CompanyService;
import com.example.fukushu1.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public String showList(Model model) {
        List<Employee> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees);
        return "/employee/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("companys", companyService.findAllCompany());
        return "/employee/add";
    }

    @PostMapping("/add")
    public String add(Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees/add";
    }

    @GetMapping("/edit/{employeeId}")
    public String editForm(@PathVariable("employeeId") Long employeeId, Model model) {
        Employee employee = employeeService.findEmployeeById(employeeId)
            .orElseThrow(() -> new EntityNotFoundException("Employee not found with id" + employeeId));
        model.addAttribute("employee", employee);
        model.addAttribute("companys", companyService.findAllCompany());
        return "/employee/edit";
    }

    @PostMapping("/edit")
    public String edit(Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/delete/{employeeId}")
    public String delete(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employees/list";
    }
}
