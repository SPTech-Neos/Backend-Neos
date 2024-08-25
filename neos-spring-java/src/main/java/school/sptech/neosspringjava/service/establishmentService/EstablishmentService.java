package school.sptech.neosspringjava.service.establishmentService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cloudinary.api.exceptions.NotFound;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRelacionamento;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentFullResponse;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentFullResponseList;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentResponse;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.model.status.Status;
import school.sptech.neosspringjava.domain.repository.establishmentRepository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.domain.repository.ratingRepository.RatingRepository;
import school.sptech.neosspringjava.domain.repository.status.StatusRepository;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;
import school.sptech.neosspringjava.service.filterService.FilterService;
import school.sptech.neosspringjava.service.paymentService.PaymentService;
import school.sptech.neosspringjava.service.productService.ProductService;
import school.sptech.neosspringjava.service.ratingService.RatingService;
import school.sptech.neosspringjava.service.schedulingService.SchedulingService;
import school.sptech.neosspringjava.service.statusService.StatusService;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;
    private final EstablishmentMapper establishmentMapper;
    private final LocalRepository localRepository;
    private final EmployeeService employeeService;
    private final FilterService filterService;
    private final PaymentService paymentService;
    private final ProductService productService;
    private final SchedulingService schedulingService;
    private final StatusService statusService;
    private final RatingRepository ratingRepository;

    public Establishment save(EstablishmentRequest establishmentRequest) {
        Establishment establishment = new Establishment();

        Integer localId = establishmentRequest.localId();
        if (localId == null) {
            throw new IllegalArgumentException("ID do local não pode ser nulo");
        }

        Local local = localRepository.findById(localId).orElseThrow(() -> new RuntimeException("Local não encontrado"));


        establishment.setName(establishmentRequest.name());
        establishment.setLocal(local);
        establishment.setImgUrl(establishmentRequest.imgUrl());

        Establishment e = establishmentRepository.save(establishment);

        return e;
    }

    private Establishment getEstablishmentResponse(EstablishmentRequest establishmentRequest,
                                                          Establishment establishment) {

        Local local = localRepository.findById(establishmentRequest.localId())
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));


        Status status = statusService.buscarStatusPorId(establishmentRequest.statusId());

        establishment.setName(establishmentRequest.name());
        establishment.setLocal(local);
        establishment.setStatus(status);
        establishment.setImgUrl(establishmentRequest.imgUrl());

        return establishmentRepository.save(establishment);
    }

    public Establishment update(EstablishmentRequest establishmentResquest, Integer id) {
        EstablishmentResponse establishment = findById(id);

        return EstablishmentMapper.toEstablishment(establishment);
    }

    public Establishment inactiveEstablishment(Integer id){
        EstablishmentResponse eDto = findById(id);

        Status s = statusService.buscarStatusPorNome("Inativo");
        if(eDto.getStatus() == s){
            throw new RuntimeException("O estabelecimento já está inativo");
        }

        eDto.setStatus(s);

        return establishmentRepository.save(EstablishmentMapper.toEstablishment(eDto));

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
        EstablishmentResponse eDto = findById(id);

        establishmentRepository.delete(EstablishmentMapper.toEstablishment(eDto));
    }

    public EstablishmentResponse findById(Integer id) {

        Establishment e = establishmentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Estabelecimento não encontrado")
        );

        EstablishmentResponse eDto = EstablishmentMapper.toEstablishmentResponse(e);

        Optional<Double> media = ratingRepository.findMediaByEstablishment(e.getId());

        Double mediaVotes = media.isEmpty() ? 0.0 : media.get();

        eDto.setMedia(mediaVotes);
        eDto.setTotalRatings(0);

        return eDto;

    }

    public List<EstablishmentResponse> findAll() {

        List<Establishment> establishments = establishmentRepository.findAll();

        List<EstablishmentResponse> eDtos = EstablishmentMapper.toEstablishmentResponseList(establishments);
        List<Double> medias = ratingRepository.findAllMediasByEstablishment();

        for (int i = 0; i < medias.size(); i++) {
            eDtos.get(i).setMedia(medias.get(i).doubleValue());
        }

        return eDtos;
    }

    public List<Establishment> findAllActives() {

        List<Establishment> establishments = establishmentRepository.findAllByStatus(statusService.buscarStatusPorNome("Ativo"));

        return establishments;
    }

    public List<Establishment> findAllInatives(){
        List<Establishment> e = establishmentRepository.findAllByStatus(
                statusService.buscarStatusPorNome("Inativo")
        );

        return e;
    }

    public Establishment reactive(Integer id){
        EstablishmentResponse eDto = findById(id);

        Status s = statusService.buscarStatusPorNome("Ativo");
        if(eDto.getStatus() == s){
            throw new RuntimeException("O estabelecimento já está ativo");
        }

        eDto.setStatus(s);

        return establishmentRepository.save(EstablishmentMapper.toEstablishment(eDto));
    }

    private Double evaluativeCalculation(Double voto, Integer numVotos, Double votoBanco, Integer numVotosBanco) {
        return ((votoBanco * numVotosBanco) + voto) / numVotosBanco + numVotos;
    }

    private List<EmployeeRelacionamento> findEmployeesByEstablishment(EstablishmentResponse establishments) {
        List<EmployeeRelacionamento> employees = employeeService.findAllByEstablishment(establishments.getId());

        return employees;
    }

    private List<EmployeeRelacionamento> findEmployeesByEstablishments(List<EstablishmentResponse> establishments) {
        List<Integer> establishmentIds = establishments.stream().map(EstablishmentResponse::getId)
                .collect(Collectors.toList());
        return employeeService.findAllByEstablishmentIds(establishmentIds);
    }

    private List<FilterResponse> findFiltersByEstablishments(List<EstablishmentResponse> establishmentsResponse) {
        List<Establishment> establishments = establishmentsResponse.stream()
                .map(establishmentResponse -> establishmentMapper.toEstablishment(establishmentResponse))
                .collect(Collectors.toList());
        return filterService.findAllByEstablishments(establishments);
    }

    private List<ProductResponse> findProductsByEstablishments(List<EstablishmentResponse> establishmentsResponse) {
        List<Establishment> establishments = establishmentsResponse.stream()
                .map(establishmentResponse -> establishmentMapper.toEstablishment(establishmentResponse))
                .collect(Collectors.toList());

        return productService.findAllByEstablishments(establishments);
    }
}