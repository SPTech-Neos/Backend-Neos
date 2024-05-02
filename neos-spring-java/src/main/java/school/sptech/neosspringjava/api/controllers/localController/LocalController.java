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
    private final AdressRepository adressRepository;
    
    @GetMapping
    public ResponseEntity<List<LocalResponse>> getAllLocals() {
        return ResponseEntity.ok(localMapper.toResponseList(localRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalResponse> getLocalById(@RequestParam Integer id) {
        return ResponseEntity.ok(localMapper.toResponse(localRepository.findById(id).get()));
    }


    @PostMapping
    public ResponseEntity<LocalResponse> createLocal(@RequestBody LocalRequest localRequest) {
        Local local = new Local();
        local.setNumber(localRequest.number());
        local.setFloor(localRequest.floor());
        local.setBloc(localRequest.bloc());
        local.setComplement(localRequest.complement());
        local.setAddress(adressRepository.findById(localRequest.address()).get());
        return ResponseEntity.ok(localMapper.toResponse(localRepository.save(local)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalResponse> updateLocal(@RequestParam Integer id, @RequestBody LocalRequest localRequest) {
        Local local = localRepository.findById(id).get();
        local.setNumber(localRequest.number());
        local.setFloor(localRequest.floor());
        local.setBloc(localRequest.bloc());
        local.setComplement(localRequest.complement());
        local.setAddress(adressRepository.findById(localRequest.address()).get());
        return ResponseEntity.ok(localMapper.toResponse(localRepository.save(local)));
    }
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocal(@RequestParam Integer id) {
        localRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
