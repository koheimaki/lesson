package com.example.fukushu1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fukushu1.model.Company;
import com.example.fukushu1.service.CompanyService;

@Controller
@RequestMapping("/companys")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/list")
    public String showList(Model model) {
        List<Company> companys = companyService.findAllCompany();
        model.addAttribute("companys", companys);
        return "company/list";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("company", new Company());
        return "company/add";
    }

    @PostMapping("/add")
    public String add(Company company) {
        companyService.saveCompany(company);
        return "redirect:/companys/add";
    }
}
