package school.sptech.neosspringjava.service.phoneService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.api.dtos.phoneDto.PhoneRequest;
import school.sptech.neosspringjava.api.mappers.phoneMapper.PhoneMapper;
import school.sptech.neosspringjava.domain.model.phone.Phone;
import school.sptech.neosspringjava.domain.repository.phoneRepository.PhoneRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository pRep;

    public Phone findById(Integer id){
        return pRep.findById(id).orElseThrow(() -> new RuntimeException("Telefone não encontrado"));
    }

    public List<Phone> findAll(){
        return pRep.findAll();
    }

    public Phone findByCountryCodeAndAreaCode(String areaCode, String number){
        Optional<Phone> p = pRep.findByAreaCodeAndNumber(areaCode, number);

        if(p.isEmpty()){
            throw new RuntimeException("Telefone não encontrado");
        }

        return p.get();
    }

    public Phone save(PhoneRequest p){
        return pRep.save(PhoneMapper.toEntity(p));
    }
}
