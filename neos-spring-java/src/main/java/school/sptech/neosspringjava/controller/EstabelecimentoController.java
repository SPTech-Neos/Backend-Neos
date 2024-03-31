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
import org.springframework.web.service.annotation.PutExchange;

import school.sptech.neosspringjava.entity.Estabelecimento;
import school.sptech.neosspringjava.repository.EstabelecimentoRopository;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoRopository estabelecimentoRepository;

    @GetMapping
    public ResponseEntity<List<Estabelecimento>> listarEstabelecimentos() {
        List<Estabelecimento> lstEstabelecimentos = estabelecimentoRepository.findAll();
        return lstEstabelecimentos.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstEstabelecimentos);
    }

    @PostMapping
    public ResponseEntity<Estabelecimento> cadastrarEstabelecimento(Estabelecimento estabelecimento) {

        Estabelecimento estabelecimentoCadastrado = estabelecimentoRepository.save(estabelecimento);
        return ResponseEntity.status(201).body(estabelecimentoCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estabelecimento> atualizarEstabelecimento(Integer id, Estabelecimento estabelecimento) {

        if (!estabelecimentoRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        estabelecimento.setIdEstabelecimento(id);
        Estabelecimento estabelecimentoAtualizado = estabelecimentoRepository.save(estabelecimento);
        return ResponseEntity.status(200).body(estabelecimentoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estabelecimento> deletarEstabelecimento(Integer id) {

        if (!estabelecimentoRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        estabelecimentoRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }
}
