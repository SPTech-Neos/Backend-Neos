package school.sptech.neosspringjava.service.establishmentService;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRespose;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.repository.companyRepository.CompanyRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.service.integracaoImageApi.IntegracaoImageApiService;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRopository establishmentRopository;
    private final EstablishmentMapper establishmentMapper;
    private final LocalRepository localRepository;
    private final CompanyRepository companyRepository;
    

    public EstablishmentRespose save(EstablishmentRequest establishmentResquest) {

        Establishment establishment = new Establishment();

        Local local = localRepository.findById(establishmentResquest.localId()).orElseThrow(() -> new RuntimeException("Local não encontrado"));

        Company company = companyRepository.findById(establishmentResquest.companyId()).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        establishment.setName(establishmentResquest.name());
        establishment.setCompany(company);
        establishment.setLocal(local);
        establishment.setImgUrl(establishmentResquest.imgUrl());

        establishment = establishmentRopository.save(establishment);

        return establishmentMapper.toEstablishmentResponse(establishment);


      
    }

    public EstablishmentRespose update(EstablishmentRequest establishmentResquest, Integer id) {

        Establishment establishment = establishmentRopository.findById(id).orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));

        Local local = localRepository.findById(establishmentResquest.localId()).orElseThrow(() -> new RuntimeException("Local não encontrado"));

        Company company = companyRepository.findById(establishmentResquest.companyId()).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        establishment.setName(establishmentResquest.name());
        establishment.setCompany(company);
        establishment.setLocal(local);
        establishment.setImgUrl(establishmentResquest.imgUrl());

        establishment = establishmentRopository.save(establishment);

        return establishmentMapper.toEstablishmentResponse(establishment);
       
    }

    public void delete(Integer id) {
        establishmentRopository.deleteById(id);
    }

    public EstablishmentRespose findById(Integer id) {

        Establishment establishment = establishmentRopository.findById(id).orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));

        return establishmentMapper.toEstablishmentResponse(establishment);
      
    }

    public List<EstablishmentRespose> findAll() {

        List<Establishment> establishments = establishmentRopository.findAll();

        return establishmentMapper.toEstablishmentResponseList(establishments);
      
    }

    private Double evaluativeCalculation(Double voto, Integer numVotos, Double votoBanco, Integer numVotosBanco){
       return ((votoBanco*numVotosBanco)+voto)/numVotosBanco+numVotos;
    };
}
