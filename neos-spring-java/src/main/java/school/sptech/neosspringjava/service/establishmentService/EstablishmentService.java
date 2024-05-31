package school.sptech.neosspringjava.service.establishmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRespose;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.filter.Filter;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.filterRepository.FilterRepository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.domain.repository.serviceRepository.ServiceRepository;
import school.sptech.neosspringjava.exception.NaoEncontradoException;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;
    private final LocalRepository localRepository;
    private final EstablishmentMapper establishmentMapper;
    private final FilterRepository filterRepository;
    private final ServiceRepository serviceRepository;

    @Transactional
    public EstablishmentRespose save(EstablishmentRequest establishmentRequest) {
        Establishment establishment = new Establishment();
        establishment.setName(establishmentRequest.name());
        establishment.setCnpj(establishmentRequest.cnpj());
        establishment.setStartShift(establishmentRequest.startShift());
        establishment.setEndShift(establishmentRequest.endShift());
        establishment.setAssessment(establishmentRequest.assessment());
        establishment.setQtdAssessment(establishmentRequest.qtdAssessment());
        establishment.setDescription(establishmentRequest.description());


        if (establishmentRequest.fkServices() != null) {
            establishment.setServices(establishmentRequest.fkServices().stream()
                    .map(id -> serviceRepository.findById(id).orElseThrow(() ->
                            new NaoEncontradoException("Service not found with id " + id)))
                    .collect(Collectors.toList()));
        }


        // Verificar e salvar local, obtendo o ID
        if (establishmentRequest.fkLocal() != null) {
            establishment.setLocal(localRepository.findById(establishmentRequest.fkLocal()).orElseThrow(() ->
                    new NaoEncontradoException("Local not found with id " + establishmentRequest.fkLocal())));

            Local existingLocal = localRepository.findById(establishmentRequest.fkLocal()).orElseThrow();
            establishment.setLocal(existingLocal);
        }

        if (establishmentRequest.fkFilters() != null) {
            establishment.setFilters(establishmentRequest.fkFilters().stream()
                    .map(id -> filterRepository.findById(id).orElseThrow(() ->
                            new NaoEncontradoException("Filter not found with id " + id)))
                    .collect(Collectors.toList()));
        }

        Establishment savedEstablishment = establishmentRepository.save(establishment);

        return establishmentMapper.toEstablishmentResponse(savedEstablishment);
    }


    public EstablishmentRespose update(EstablishmentRequest establishmentResquest, Integer id) {
        Establishment establishment = establishmentRepository.getReferenceById(id);

        establishment.setName(establishmentResquest.name());
        establishment.setCnpj(establishmentResquest.cnpj());
        establishment.setStartShift(establishmentResquest.startShift());
        establishment.setEndShift(establishmentResquest.endShift());
        establishment.setAssessment(evaluativeCalculation(establishmentResquest.assessment(),establishmentResquest.qtdAssessment(),establishment.getAssessment(),establishment.getQtdAssessment()));
        establishment.setQtdAssessment(establishment.getQtdAssessment()+establishmentResquest.qtdAssessment());
        establishment.setLocal(localRepository.getReferenceById(establishmentResquest.fkLocal()));
        establishment.setDescription(establishmentResquest.description());
        establishment.setFilters(filterRepository.findAllById(establishmentResquest.fkFilters()));

        return EstablishmentMapper.toEstablishmentResponse(establishmentRepository.save(establishment));
    }

    public void delete(Integer id) {
        establishmentRepository.deleteById(id);
    }

    public EstablishmentRespose findById(Integer id) {
        Establishment findEstablishment = establishmentRepository.findById(id).orElseThrow();

        return establishmentMapper.toEstablishmentResponse(findEstablishment);
    }

    public List<EstablishmentRespose> findAll() {
        return EstablishmentMapper.toEstablishmentResponse(establishmentRepository.findAll());
    }

    private Double evaluativeCalculation(Double voto, Integer numVotos, Double votoBanco, Integer numVotosBanco){
       return ((votoBanco*numVotosBanco)+voto)/numVotosBanco+numVotos;
    };
}
