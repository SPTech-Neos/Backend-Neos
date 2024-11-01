package school.sptech.neosspringjava.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.Local;
import school.sptech.neosspringjava.domain.model.Phone;
import school.sptech.neosspringjava.domain.model.Service;
import school.sptech.neosspringjava.domain.model.Status;
import school.sptech.neosspringjava.domain.repository.EmployeeRepository;
import school.sptech.neosspringjava.domain.repository.EmployeeServicesRepository;
import school.sptech.neosspringjava.domain.repository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.LocalRepository;
import school.sptech.neosspringjava.domain.repository.PhoneRepository;
import school.sptech.neosspringjava.domain.repository.RatingRepository;
import school.sptech.neosspringjava.domain.repository.StatusRepository;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.ServiceMapper;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;
    private final LocalRepository localRepository;
    private final StatusService statusService;
    private final RatingRepository ratingRepository;
    private final PhoneService pService;
    private final EmployeeServicesRepository employeeServicesRepository;
    private final StatusRepository statusRepository;
    private final PhoneRepository phoneRepository;

    public Establishment save(EstablishmentRequest establishmentRequest) {
        Establishment establishment = new Establishment();

        Integer localId = establishmentRequest.localId();
        if (localId == null) {
            throw new IllegalArgumentException("ID do local não pode ser nulo");
        }

        Local local = localRepository.findById(localId).orElseThrow(() -> new RuntimeException("Local não encontrado"));
        Phone p = pService.findById(establishmentRequest.phoneId());

        establishment.setName(establishmentRequest.name());
        establishment.setLocal(local);
        establishment.setImgUrl(establishmentRequest.imgUrl());
        establishment.setPhone(p);
        establishment.setAditumId(establishmentRequest.aditumId());
        establishment.setDescription(establishmentRequest.description());
        establishment.setStartShift(establishmentRequest.startShift());
        establishment.setEndShift(establishmentRequest.endShift());
        establishment.setCnpj(establishmentRequest.cnpj());
        establishment.setStatus(statusService.findById(establishmentRequest.statusId()));


        Establishment e = establishmentRepository.save(establishment);

        return e;
    }

    private Establishment getEstablishmentResponse(EstablishmentRequest establishmentRequest,
                                                          Establishment establishment) {

        Local local = localRepository.findById(establishmentRequest.localId())
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));


        Status status = statusService.findById(establishmentRequest.statusId());

        establishment.setName(establishmentRequest.name());
        establishment.setLocal(local);
        establishment.setStatus(status);
        establishment.setImgUrl(establishmentRequest.imgUrl());

        return establishmentRepository.save(establishment);
    }

    public Establishment update(EstablishmentRequest establishmentResquest, Integer id) {
        Establishment e = findById(id);

        return e;
    }

    public Establishment inactiveEstablishment(Integer id){
        Establishment e = findById(id);

        Status s = statusService.findStatusByName("Inativo");
        if(e.getStatus().equals(s)){
            throw new RuntimeException("O estabelecimento já está inativo");
        }

        e.setStatus(s);

        return establishmentRepository.save(e);

    }

    public Establishment partialUpdate(EstablishmentRequest establishmentRequest, Integer id) {
        // Busca o estabelecimento pelo ID
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));
    
        // Atualiza o nome, se presente
        if (establishmentRequest.name() != null) {
            establishment.setName(establishmentRequest.name());
        }
    
        // Atualiza o local, se presente
        if (establishmentRequest.localId() != null) {
            Local local = localRepository.findById(establishmentRequest.localId())
                    .orElseThrow(() -> new RuntimeException("Local não encontrado"));
            establishment.setLocal(local);
        }
    
        // Atualiza a imagem, se presente
        if (establishmentRequest.imgUrl() != null) {
            establishment.setImgUrl(establishmentRequest.imgUrl());
        }
    
        // Atualiza o telefone, se presente
        if (establishmentRequest.phoneId() != null) {
            Phone phone = phoneRepository.findById(establishmentRequest.phoneId())
                    .orElseThrow(() -> new RuntimeException("Telefone não encontrado"));
            establishment.setPhone(phone);
        }
    
        // Atualiza o status, se presente
        if (establishmentRequest.statusId() != null) {
            Status status = statusRepository.findById(establishmentRequest.statusId())
                    .orElseThrow(() -> new RuntimeException("Status não encontrado"));
            establishment.setStatus(status);
        }
    
        // Atualiza o horário de início, se presente
        if (establishmentRequest.startShift() != null) {
            establishment.setStartShift(establishmentRequest.startShift());
        }
    
        // Atualiza o horário de fim, se presente
        if (establishmentRequest.endShift() != null) {
            establishment.setEndShift(establishmentRequest.endShift());
        }
    
        // Atualiza a descrição, se presente
        if (establishmentRequest.description() != null) {
            establishment.setDescription(establishmentRequest.description());
        }
    
        // Atualiza o CNPJ, se presente
        if (establishmentRequest.cnpj() != null) {
            establishment.setCnpj(establishmentRequest.cnpj());
        }
    
        // Salva as alterações no repositório
        return establishmentRepository.save(establishment);
    }
    

    public void delete(Integer id) {
        Establishment e = findById(id);

        establishmentRepository.delete(e);
    }

    public Double findMediaById(Integer id){
       Optional<Double> media = ratingRepository.findMediaByEstablishment(id);

        return media.orElse(0.0);

    }

    public List<Double> findAllMediasEstablishmentOrder(){
        return ratingRepository.findAllMediasByEstablishment();
    }

    public Establishment findById(Integer id) {

        Establishment e = establishmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Estabelecimento não encontrado")
        );

        return e;

    }

    public List<ServiceResponse> findServicesById(Integer id) {
        // Busca os serviços usando o repositório
        List<Service> services = employeeServicesRepository.findServicesById(id);
        // Mapeia a lista de Service para ServiceResponse
        return ServiceMapper.toServiceResponseList(services);
    }

    public List<Establishment> findAll() {
        return establishmentRepository.findAll();
    }

    public List<Establishment> findAllActives() {

        List<Establishment> establishments = establishmentRepository.findAllByStatus(statusService.findStatusByName("Ativo"));

        return establishments;
    }

    public List<Establishment> findAllInatives(){
        List<Establishment> e = establishmentRepository.findAllByStatus(
                statusService.findStatusByName("Inativo")
        );

        return e;
    }

    public Establishment reactive(Integer id){
        Establishment e = findById(id);

        Status s = statusService.findStatusByName("Ativo");
        if(e.getStatus().equals(s)){
            throw new RuntimeException("Esse estabelecimento já está ativo");
        }
        e.setStatus(s);

        return establishmentRepository.save(e);
    }

    public List<Double> findBestMedias(Integer top){
        List<Double> medias = ratingRepository.findBestMediasByTop(top);

        return medias;
    }

    public List<Establishment> findBestRateds(Integer top){
        List<Establishment> e = ratingRepository.findBestRatedsByTop(top);

        return e;
    }
}