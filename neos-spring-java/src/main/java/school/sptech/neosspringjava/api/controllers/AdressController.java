package school.sptech.neosspringjava.api.controllers;

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
import school.sptech.neosspringjava.domain.model.client.address.Address;
import school.sptech.neosspringjava.domain.repository.AdressRepository;

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
 @RequestMapping("/adress")
public class AdressController {

    @Autowired
    private AdressRepository adressRepository;


    @GetMapping
    public ResponseEntity<List<Address>> adressLists() {
        List<Address> lstAddresses = adressRepository.findAll();
        return lstAddresses.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstAddresses);
    }

    @PostMapping
    public ResponseEntity<Address> adressAccess(@Valid @RequestBody Address address) {

        Address addressCadastrado = adressRepository.save(address);
        return ResponseEntity.status(201).body(addressCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> adressUpdate(@PathVariable Integer id, @RequestBody Address address) {

        if (!adressRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        address.setIdAddress(id);
        Address addressUpdated = adressRepository.save(address);
        return ResponseEntity.status(200).body(addressUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> adressDelete(@PathVariable Integer id) {

        if (!adressRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        adressRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }


}
