package school.sptech.neosspringjava.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.LocalDto.LocalRequest;
import school.sptech.neosspringjava.api.dtos.LocalDto.LocalResponse;
import school.sptech.neosspringjava.api.mappers.LocalMapper;
import school.sptech.neosspringjava.domain.model.Address;
import school.sptech.neosspringjava.domain.model.Local;
import school.sptech.neosspringjava.domain.repository.AddressRepository;
import school.sptech.neosspringjava.domain.repository.LocalRepository;

@Service
@RequiredArgsConstructor
public class LocalService {

private final LocalRepository localRepository;
    private final LocalMapper localMapper;
    private final AddressRepository addressRepository;

   
    public List<LocalResponse> findAll() {
        return localMapper.toLocalResponse(localRepository.findAll());
    }

    
    public Local findById( Integer id) {
        return localRepository.findById(id).orElseThrow(() -> new RuntimeException("Local não encontrado"));
    }

   
    public LocalResponse save(LocalRequest localRequest) {
        Address address = addressRepository.findById(localRequest.address()).orElseThrow();
        Local local = Local.builder()
                .number(localRequest.number())
                .floor(localRequest.floor())
                .block(localRequest.block())
                .complement(localRequest.complement())
                .address(address)
                .build();
        return localMapper.toLocalResponse(localRepository.save(local));
    }

    
    public LocalResponse update( Integer id, LocalRequest localRequest) {

        Address address = addressRepository.findById(localRequest.address()).orElseThrow();

        Local local = Local.builder()
                .id(id)
                .number(localRequest.number())
                .floor(localRequest.floor())
                .block(localRequest.block())
                .complement(localRequest.complement())
                .address(address)
                .build();
        return localMapper.toLocalResponse(localRepository.save(local));
    }
  

    
    public void deleteLocal( Integer id) {
        localRepository.deleteById(id);
    }


}
