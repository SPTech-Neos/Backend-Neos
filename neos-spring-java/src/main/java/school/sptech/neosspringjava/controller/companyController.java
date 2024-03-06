package school.sptech.neosspringjava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.neosspringjava.Company;

@RestController
@RequestMapping("/company")
public class companyController {

    List<Company> lstCompany = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<Company>> getAllCompany() {
        return ResponseEntity.status(200).body(lstCompany);
    }

    @GetMapping("/{id}")
    public Company getCompanyById(int id) {
        return lstCompany.get(id);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Company> getCompanyByCnpj(String cnpj) {

        if (lstCompany.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200)
                .body(lstCompany.stream().filter(c -> c.getCnpj().equals(cnpj)).findFirst().get());

    }

    @PostMapping("/")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {

        boolean exists = lstCompany.stream().anyMatch(c -> c.getCnpj().equals(company.getCnpj()));
        if (exists) {
            return ResponseEntity.status(409).build();
        }

        lstCompany.add(company);
        return ResponseEntity.status(201).body(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(int id, @RequestBody Company company) {

        if (lstCompany.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        lstCompany.set(id, company);
        return ResponseEntity.status(200).body(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Company> deleteCompany(int id) {

        if (lstCompany.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        lstCompany.remove(id);
        return ResponseEntity.status(200).body(lstCompany.get(id));
    }
}
