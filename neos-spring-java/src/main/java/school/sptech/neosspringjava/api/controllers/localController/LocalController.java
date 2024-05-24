package school.sptech.neosspringjava.api.controllers.localController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import school.sptech.neosspringjava.domain.repository.adressRepository.AdressRepository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;

@RestController
@RequestMapping("/locais")
@RequiredArgsConstructor
public class LocalController {

    private final LocalRepository localRepository;
    private final LocalMapper localMapper;
    private final AdressRepository addresRepository;

    @GetMapping
    public ResponseEntity<List<LocalResponse>> findAll() {
        return ResponseEntity.ok(localMapper.toLocalResponse(localRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalResponse> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(localMapper.toLocalResponse(localRepository.findById(id).get()));
    }

    @PostMapping
    public ResponseEntity<LocalResponse> save(@RequestBody LocalRequest localRequest) {
        Local local = Local.builder()
                .number(localRequest.number())
                .floor(localRequest.floor())
                .bloc(localRequest.bloc())
                .complement(localRequest.complement())
                .address(localRequest.address())
                .build();
        return ResponseEntity.ok(localMapper.toLocalResponse(localRepository.save(local)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalResponse> update(@RequestParam Integer id, @RequestBody LocalRequest localRequest) {
        Local local = Local.builder()
                .idLocal(id)
                .number(localRequest.number())
                .floor(localRequest.floor())
                .bloc(localRequest.bloc())
                .complement(localRequest.complement())
                .address(localRequest.address())
                .build();
        return ResponseEntity.ok(localMapper.toLocalResponse(localRepository.save(local)));
    }
  

    @DeleteMapping("/{id}")
    public void deleteLocal(@RequestParam Integer id) {
        localRepository.deleteById(id);
    }

    
}
