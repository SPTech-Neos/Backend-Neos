package school.sptech.neosspringjava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ProductType")
public class ProductTypeController {
    @Autowired
    private ProductTypeRopository ProductTypeRopository;

    @GetMapping
    public ResponseEntity<List<ProductType>> listProductType(){
        List<ProductType> lstProductType = ProductTypeRopository.findAll();
        return lstProductType.isEmpty() ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lstProductType);
    }
}
