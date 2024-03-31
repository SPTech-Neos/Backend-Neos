package school.sptech.neosspringjava.controller;

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
import school.sptech.neosspringjava.entity.Endereco;
import school.sptech.neosspringjava.repository.EnderecoRepository;

/**
 * @autor: @GabrielYukioMC
 * @data criação: 2024-03-31
 * @versão: 1.0.0
 * @descrição: Criação do controller EmpresaController
 * @/alterado por: @GabrielYukioMC
 * @data alteração: 2024-03-31
 * 
 **/

 @RestController
 @RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;


    @GetMapping
    public ResponseEntity<List<Endereco>> listarEnderecos() {
        List<Endereco> lstEnderecos = enderecoRepository.findAll();
        return lstEnderecos.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstEnderecos);
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrarEndereco(@Valid @RequestBody Endereco endereco) {

        Endereco enderecoCadastrado = enderecoRepository.save(endereco);
        return ResponseEntity.status(201).body(enderecoCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Integer id, @RequestBody Endereco endereco) {

        if (!enderecoRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        endereco.setIdEndereco(id);
        Endereco enderecoAtualizado = enderecoRepository.save(endereco);
        return ResponseEntity.status(200).body(enderecoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> deletarEndereco(@PathVariable Integer id) {

        if (!enderecoRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        enderecoRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }


}
