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
import school.sptech.neosspringjava.entity.Empresa;
import school.sptech.neosspringjava.repository.EmpresaRepository;

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
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List<Empresa> lstEmpresas = empresaRepository.findAll();
        return lstEmpresas.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstEmpresas);
    }

    @PostMapping
    public ResponseEntity<Empresa> cadastrarEmpresa(@Valid @RequestBody Empresa empresa) {

        if (existeCNPJ(empresa.getCnpj())) {
            return ResponseEntity.status(204).build();
        }

        Empresa empresaCadastrada = empresaRepository.save(empresa);
        return ResponseEntity.status(201).body(empresaCadastrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Integer id, @RequestBody Empresa empresa) {

        if (!empresaRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        else {
            empresa.setIdEmpresa(id);
            Empresa empresaAtualizada = empresaRepository.save(empresa);
            return ResponseEntity.status(200).body(empresaAtualizada);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Integer id) {
        empresaRepository.deleteById(id);
        return ResponseEntity.status(204).build();
    }

    private boolean existeCNPJ(String cnpj) {
        for (Empresa e : empresaRepository.findAll()) {
            if (e.getCnpj().equals(cnpj)) {
                return true;
            }
        }

        return false;
    }

}
