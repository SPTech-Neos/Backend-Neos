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
 * @descrição: Criação do controller EmpresaController
 * @/alterado por: @GabrielYukioMC
 * @data alteração: 2024-03-31
 * 
 **/

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public ResponseEntity<List<Company>> listarEmpresas() {
        List<Company> lstCompanies = companyRepository.findAll();
        return lstCompanies.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstCompanies);
    }

    @PostMapping
    public ResponseEntity<Company> cadastrarEmpresa(@Valid @RequestBody Company company) {

        if (existeCNPJ(company.getCnpj())) {
            return ResponseEntity.status(204).build();
        }

        Company companyCadastrada = companyRepository.save(company);
        return ResponseEntity.status(201).body(companyCadastrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> atualizarEmpresa(@PathVariable Integer id, @RequestBody Company company) {

        if (!companyRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        else {
            company.setIdEmpresa(id);
            Company companyAtualizada = companyRepository.save(company);
            return ResponseEntity.status(200).body(companyAtualizada);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Integer id) {
        companyRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    private boolean existeCNPJ(String cnpj) {
        for (Company e : companyRepository.findAll()) {
            if (e.getCnpj().equals(cnpj)) {
                return true;
            }
        }

        return false;
    }

}
