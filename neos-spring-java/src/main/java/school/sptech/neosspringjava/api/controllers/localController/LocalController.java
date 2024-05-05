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
import school.sptech.neosspringjava.api.mappers.localMapper.LocalMapper;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;

@RestController
@RequestMapping("/locais")
@RequiredArgsConstructor
public class LocalController {

    private final LocalRepository localRepository;
    private final LocalMapper localMapper;

    @GetMapping
    public ResponseEntity<List<Local>> getLocais() {
        return ResponseEntity.ok(localRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> getLocalById(@RequestParam Integer id) {
        return ResponseEntity.ok(localRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity<Local> createLocal(@RequestBody Local local) {
        return ResponseEntity.status(201).body(localRepository.save(local));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> updateLocal(@RequestParam Integer id, @RequestBody Local local) {
        Local localToUpdate = localRepository.findById(id).get();
        localToUpdate.setNumber(local.getNumber());
        localToUpdate.setFloor(local.getFloor());
        localToUpdate.setBloc(local.getBloc());
        localToUpdate.setComplement(local.getComplement());
        localToUpdate.setFkAddress(local.getFkAddress());
        return ResponseEntity.ok(localRepository.save(localToUpdate));
    }

    @DeleteMapping("/{id}")
    public void deleteLocal(@RequestParam Integer id) {
        localRepository.deleteById(id);
    }

    
}
