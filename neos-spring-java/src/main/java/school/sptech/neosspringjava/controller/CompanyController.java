package school.sptech.neosspringjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import school.sptech.neosspringjava.entity.Company;
import school.sptech.neosspringjava.repository.CompanyRepository;

/**
 * @autor: @GabrielYukioMC
 * @data criação: 2024-03-31
 * @versão: 1.0.0
 * @descrição: Criação do controller CompanyController
 * @/alterado por: @GabrielYukioMC
 * @data alteração: 2024-03-31
 * 
 **/

@RestController
@RequestMapping("/Company")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public ResponseEntity<List<Company>> companyLists() {
        List<Company> lstCompanies = companyRepository.findAll();
        return lstCompanies.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstCompanies);
    }

    @PostMapping
    public ResponseEntity<Company> companyList(@Valid @RequestBody Company company) {

        if (existCNPJ(company.getCnpj())) {
            return ResponseEntity.status(204).build();
        }

        Company companyCadastrada = companyRepository.save(company);
        return ResponseEntity.status(201).body(companyCadastrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> companyUpdate(@PathVariable Integer id, @RequestBody Company company) {

        if (!companyRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        else {
            company.setIdCompany(id);
            Company companyUpdated = companyRepository.save(company);
            return ResponseEntity.status(200).body(companyUpdated);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> companyDelete(@PathVariable Integer id) {
        companyRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    private boolean existCNPJ(String cnpj) {
        for (Company e : companyRepository.findAll()) {
            if (e.getCnpj().equals(cnpj)) {
                return true;
            }
        }

        return false;
    }

}
