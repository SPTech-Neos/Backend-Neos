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

import school.sptech.neosspringjava.entity.Merchant;
import school.sptech.neosspringjava.repository.MerchantRopository;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantRopository merchantRepository;

    @GetMapping
    public ResponseEntity<List<Merchant>> listMerchant() {
        List<Merchant> lstMerchants = merchantRepository.findAll();
        return lstMerchants.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstMerchants);
    }

    @PostMapping
    public ResponseEntity<Merchant> postMerchant(Merchant merchant) {

        Merchant MerchantCadastrado = merchantRepository.save(merchant);
        return ResponseEntity.status(201).body(MerchantCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMechant(Integer id, Merchant Merchant) {

        if (!merchantRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        Merchant.setIdMerchant(id);
        Merchant merchantAtualizado = merchantRepository.save(Merchant);
        return ResponseEntity.status(200).body(merchantAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Merchant> deleteMerchant(Integer id) {

        if (!merchantRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        merchantRepository.deleteById(id);
        return ResponseEntity.status(200).build();
    }
}
