package school.sptech.neosspringjava.api.controllers.companyController;

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
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.companyDto.CompanyResponse;
import school.sptech.neosspringjava.api.mappers.companyMapper.CompanyMapper;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.repository.companyRepository.CompanyRepository;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository companyRepository;

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompany() {
        List<Company> company = companyRepository.findAll();

        return ResponseEntity.ok().body(CompanyMapper.toCompanyResponseList(company));
    }

   @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable int id) {
        Company company = companyRepository.findById(id).orElseThrow();
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(CompanyMapper.toCompanyResponse(company));
    }
   
    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody Company company) {
        companyRepository.save(company);
        return ResponseEntity.ok(CompanyMapper.toCompanyResponse(company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable int id, @Valid @RequestBody Company company) {
        Company companyUpdate = companyRepository.findById(id).orElseThrow();
        if (companyUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        companyUpdate.setName(company.getName());
        companyUpdate.setCnpj(company.getCnpj());
        companyRepository.save(companyUpdate);
        return ResponseEntity.ok(CompanyMapper.toCompanyResponse(companyUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable int id) {
        Company company = companyRepository.findById(id).orElseThrow();
        if (company == null) {
            return ResponseEntity.notFound().build();
        }
        companyRepository.delete(company);
        return ResponseEntity.ok().build();
    }
}
