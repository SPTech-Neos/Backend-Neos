package school.sptech.neosspringjava.service;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.persistence.EntityNotFoundException;
import school.sptech.neosspringjava.domain.model.Client;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Market;
import school.sptech.neosspringjava.domain.repository.MarketRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository mRepo;
    private final EstablishmentService establishmentService;
    private final ClientService clientService;

    public List<Market> findAll(){
        return mRepo.findAll();
    }

    public Market findById(Integer id){
        return mRepo.findById(id).orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
    }

    public List<Market> findByEstablishmentId(Integer id){
        Establishment e = establishmentService.findById(id);
        if (e == null ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado");
        }
        return mRepo.findByEstablishmentId(e.getId());
    }

    public List<Market> findByClientId(Integer id){
        Client c = clientService.findById(id);
        if (c == null ) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado");
        }

        return mRepo.findByClientId(c.getId());
    }
}
