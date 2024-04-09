package school.sptech.neosspringjava.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.neosspringjava.domain.local.Local;
import school.sptech.neosspringjava.repository.LocalRepository;

@RestController
@RequestMapping("/locais")
public class LocalController {

    @Autowired
    private LocalRepository localRepository;

    @GetMapping
    public ResponseEntity<List<Local>> listarLocais() {
        List<Local> lstLocais = localRepository.findAll();
        return lstLocais.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstLocais);
    }

    @PostMapping
    public ResponseEntity<Local> cadastrarLocal(Local local) {

        Local localCadastrado = localRepository.save(local);
        return ResponseEntity.status(201).body(localCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> atualizarLocal(Integer id, Local local) {

        if (!localRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        local.setIdLocal(id);
        Local localAtualizado = localRepository.save(local);
        return ResponseEntity.status(200).body(localAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Local> deletarLocal(Integer id) {

        if (!localRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        localRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }
}
