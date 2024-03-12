package school.sptech.neosspringjava.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.neosspringjava.services.company;

@RestController
@RequestMapping("/company")
public class CompanyController {

    List<company> lstCompany = new ArrayList<>();

    @GetMapping("/")
    public ResponseEntity<List<company>> getAllCompany() {
        return ResponseEntity.status(200).body(lstCompany);
    }

    @GetMapping("/{id}")
    public company getCompanyById(int id) {
        return lstCompany.get(id);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<company> getCompanyByCnpj(String cnpj) {

        if (lstCompany.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200)
                .body(lstCompany.stream().filter(c -> c.getCnpj().equals(cnpj)).findFirst().get());

    }

    @PostMapping("/")
    public ResponseEntity<company> createCompany(@RequestBody company company) {

        boolean exists = lstCompany.stream().anyMatch(c -> c.getCnpj().equals(company.getCnpj()));
        if (exists) {
            return ResponseEntity.status(409).build();
        }

        lstCompany.add(company);
        return ResponseEntity.status(201).body(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<company> updateCompany(int id, @RequestBody company company) {

        if (lstCompany.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        lstCompany.set(id, company);
        return ResponseEntity.status(200).body(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<company> deleteCompany(int id) {

        if (lstCompany.size() == 0) {
            return ResponseEntity.status(404).build();
        }

        lstCompany.remove(id);
        return ResponseEntity.status(200).body(lstCompany.get(id));
    }
}
