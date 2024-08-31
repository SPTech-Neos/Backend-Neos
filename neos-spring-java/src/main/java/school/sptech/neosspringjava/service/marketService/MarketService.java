package school.sptech.neosspringjava.service.marketService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.domain.model.market.Market;
import school.sptech.neosspringjava.domain.repository.marketRepository.MarketRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final MarketRepository mRepo;

    public List<Market> findAll(){
        return mRepo.findAll();
    }

    public Market findById(Integer id){
        return mRepo.findById(id).orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
    }
}
