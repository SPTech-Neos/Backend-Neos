package school.sptech.neosspringjava.service.establishmentService;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRespose;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRopository establishmentRopository;

    public EstablishmentRespose save(EstablishmentRequest establishmentResquest) {
        Establishment establishment = new Establishment();
        establishment.setName(establishmentResquest.name());
        establishment.setCnpj(establishmentResquest.cnpj());
        establishment.setStartShift(establishmentResquest.startShift());
        establishment.setEndShift(establishmentResquest.endShift());
        establishment.setAssessment(establishmentResquest.assessment());
        establishment.setQtdAssessment(establishmentResquest.qtdAssessment());
        establishment.setLocal(establishmentResquest.local());
        establishment.setDescription(establishmentResquest.description());
        establishment.setFilters(establishmentResquest.filters());

        return EstablishmentMapper.toEstablishmentResponse(establishmentRopository.save(establishment));
    }

    public EstablishmentRespose update(EstablishmentRequest establishmentResquest, Integer id) {
        Establishment establishment = establishmentRopository.findById(id).orElseThrow();
        establishment.setName(establishmentResquest.name());
        establishment.setCnpj(establishmentResquest.cnpj());
        establishment.setStartShift(establishmentResquest.startShift());
        establishment.setEndShift(establishmentResquest.endShift());
        establishment.setAssessment(evaluativeCalculation(establishmentResquest.assessment(),establishmentResquest.qtdAssessment(),establishment.getAssessment(),establishment.getQtdAssessment()));
        establishment.setQtdAssessment(establishment.getQtdAssessment()+establishmentResquest.qtdAssessment());
        establishment.setLocal(establishmentResquest.local());
        establishment.setDescription(establishmentResquest.description());
        establishment.setFilters(establishmentResquest.filters());

        return EstablishmentMapper.toEstablishmentResponse(establishmentRopository.save(establishment));
    }

    public void delete(Integer id) {
        establishmentRopository.deleteById(id);
    }

    public EstablishmentRespose findById(Integer id) {
        return EstablishmentMapper.toEstablishmentResponse(establishmentRopository.findById(id).orElseThrow());
    }

    public List<EstablishmentRespose> findAll() {
        return EstablishmentMapper.toEstablishmentResponse(establishmentRopository.findAll());
    }

    private Double evaluativeCalculation(Double voto, Integer numVotos, Double votoBanco, Integer numVotosBanco){
       return ((votoBanco*numVotosBanco)+voto)/numVotosBanco+numVotos;
    };
}
