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
import school.sptech.neosspringjava.entity.Address;
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
    public ResponseEntity<List<Address>> listarEnderecos() {
        List<Address> lstAddresses = enderecoRepository.findAll();
        return lstAddresses.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstAddresses);
    }

    @PostMapping
    public ResponseEntity<Address> cadastrarEndereco(@Valid @RequestBody Address address) {

        Address addressCadastrado = enderecoRepository.save(address);
        return ResponseEntity.status(201).body(addressCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> atualizarEndereco(@PathVariable Integer id, @RequestBody Address address) {

        if (!enderecoRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        address.setIdEndereco(id);
        Address addressAtualizado = enderecoRepository.save(address);
        return ResponseEntity.status(200).body(addressAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> deletarEndereco(@PathVariable Integer id) {

        if (!enderecoRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        enderecoRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }


}
