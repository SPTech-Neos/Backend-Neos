package school.sptech.neosspringjava.api.controllers.localController;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.LocalDto.LocalRequest;
import school.sptech.neosspringjava.api.dtos.LocalDto.LocalResponse;
import school.sptech.neosspringjava.api.mappers.localMapper.LocalMapper;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.service.localService.LocalService;

@RestController
@RequestMapping("/local")
@RequiredArgsConstructor
public class LocalController {


    private final LocalService localService;


    @GetMapping
    public ResponseEntity<List<LocalResponse>> findAll() {
        return ResponseEntity.ok(localService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalResponse> findById(@PathVariable Integer id) {
        LocalResponse localResponse = localService.findById(id);
        if (localResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(localResponse);
    }

    @PostMapping
    public ResponseEntity<LocalResponse> save(@RequestBody LocalRequest localRequest) {
        
        return ResponseEntity.ok(localService.save(localRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalResponse> update(@PathVariable Integer id, @RequestBody LocalRequest localRequest) {
        LocalResponse localResponse = localService.update(id, localRequest);
        if (localResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(localResponse);
    }
  

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocal(@PathVariable Integer id) {
        
        localService.deleteLocal(id);
        return ResponseEntity.noContent().build();
    }

    
}
