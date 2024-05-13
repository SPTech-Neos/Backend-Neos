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
import school.sptech.neosspringjava.api.dtos.companyDto.*;
import school.sptech.neosspringjava.api.mappers.companyMapper.CompanyMapper;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.repository.companyRepository.CompanyRepository;
import school.sptech.neosspringjava.service.companyService.CompanyService;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

   private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> save(@Valid @RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.ok(companyService.save(companyRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(companyService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(@PathVariable Integer id, @Valid @RequestBody CompanyRequest companyRequest) {
        return ResponseEntity.ok(companyService.update(id, companyRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
