package com.example.fukushu1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fukushu1.model.Employee;
import com.example.fukushu1.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/show")
    public String showList(Model model) {
        List<Employee> employees = employeeService.findAllEmployees();
        model.addAttribute("employees", employees);
        return "/employees/show";
    }

    @GetMapping("/add")
    public String addFrom(Model model) {
        model.addAttribute("employee", new Employee());
        return "/employees/add";
    }

    @PostMapping("/add")
    public String add(Employee newEmployee) {
        employeeService.savEmployee(newEmployee);
        return "redirect:/employees/add";
    }

    @GetMapping("/edit/{employeeId}")
    public String editForm(@PathVariable("employeeId") Long employeeId, Model model) {
        Employee employee = employeeService.findEmployeeById(employeeId)
            .orElseThrow(() -> new IllegalArgumentException("Inbalid employee Id" + employeeId));
        model.addAttribute("employee", employee);
        return "/employees/edit";
    }

    @PostMapping("/edit")
    public String edit(Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees/show";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "firstName", required = false) String firstName,
                    Model model) {
        List<Employee> employees = employeeService.findByFirstName(firstName);
        model.addAttribute("employees", employees);
        return "/employees/search";
    }
}
