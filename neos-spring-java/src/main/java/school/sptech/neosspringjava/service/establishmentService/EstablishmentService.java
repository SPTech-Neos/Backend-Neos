package school.sptech.neosspringjava.service.establishmentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRelacionamento;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.phone.Phone;
import school.sptech.neosspringjava.domain.model.status.Status;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.domain.repository.ratingRepository.RatingRepository;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;
import school.sptech.neosspringjava.service.paymentService.PaymentService;
import school.sptech.neosspringjava.service.phoneService.PhoneService;
import school.sptech.neosspringjava.service.productService.ProductService;
import school.sptech.neosspringjava.service.schedulingService.SchedulingService;
import school.sptech.neosspringjava.service.statusService.StatusService;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;
    private final LocalRepository localRepository;
    private final EmployeeService employeeService;
    private final PaymentService paymentService;
    private final ProductService productService;
    private final SchedulingService schedulingService;
    private final StatusService statusService;
    private final RatingRepository ratingRepository;
    private final PhoneService pService;

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
        if(e.getStatus() == s){
            throw new RuntimeException("O estabelecimento já está inativo");
        }


        return establishmentRepository.save(e);

    }

    public Establishment partialUpdate(EstablishmentRequest establishmentRequest, Integer id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));

        if (establishmentRequest.name() != null) {
            establishment.setName(establishmentRequest.name());
        }
        if (establishmentRequest.localId() != null) {
            Local local = localRepository.findById(establishmentRequest.localId())
                    .orElseThrow(() -> new RuntimeException("Local não encontrado"));
            establishment.setLocal(local);
        }
        if (establishmentRequest.imgUrl() != null) {
            establishment.setImgUrl(establishmentRequest.imgUrl());
        }

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