package com.example.fukushu1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fukushu1.model.Company;
import com.example.fukushu1.model.Employee;
import com.example.fukushu1.repository.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    
    public Optional<Company> findCompanyById(Long id) {
        return companyRepository.findById(id);
    }
    //登録機能
    @Transactional
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }
    
    //更新機能
    @Transactional
    public void updateCompanyWithEmployee(Company updateCompany, List<Employee> updateEmployees) {
        Company existingCompany = companyRepository.findById(updateCompany.getId())
            .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        existingCompany.setName(updateCompany.getName());
        existingCompany.setEmployees(updateEmployees);
        companyRepository.save(existingCompany);
    }
    //削除機能
    @Transactional
    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
            .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        company.getEmployees().clear();
        companyRepository.delete(company);
    }

}
