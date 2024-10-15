package school.sptech.neosspringjava.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.neosspringjava.api.dtos.phoneDto.PhoneRequest;
import school.sptech.neosspringjava.api.dtos.phoneDto.PhoneResponse;
import school.sptech.neosspringjava.api.mappers.PhoneMapper;
import school.sptech.neosspringjava.service.PhoneService;

import java.util.List;

@RequestMapping("/phones")
@RestController
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService pService;

    @GetMapping
    public ResponseEntity<List<PhoneResponse>> findAllPhones(){
        return ResponseEntity.ok(PhoneMapper.toResponses(pService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(PhoneMapper.toResponse(pService.findById(id)));
    }

    @GetMapping("/number")
    public ResponseEntity<PhoneResponse> findByNumber( @RequestParam String areaCode, @RequestParam String number){
        return ResponseEntity.ok(PhoneMapper.toResponse(pService.findByCountryCodeAndAreaCode(areaCode, number)));
    }

    @PostMapping
    public ResponseEntity<PhoneResponse> save(@RequestBody PhoneRequest pDto){
        return ResponseEntity.ok(PhoneMapper.toResponse(pService.save(pDto)));
    }
}
