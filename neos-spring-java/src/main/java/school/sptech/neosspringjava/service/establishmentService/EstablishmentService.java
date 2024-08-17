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
import school.sptech.neosspringjava.domain.repository.status.StatusRepository;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;
import school.sptech.neosspringjava.service.filterService.FilterService;
import school.sptech.neosspringjava.service.paymentService.PaymentService;
import school.sptech.neosspringjava.service.productService.ProductService;
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

    public EstablishmentResponse save(EstablishmentRequest establishmentRequest) {
        Establishment establishment = new Establishment();

        Integer localId = establishmentRequest.localId();
        if (localId == null) {
            throw new IllegalArgumentException("ID do local não pode ser nulo");
        }

        Local local = localRepository.findById(localId).orElseThrow(() -> new RuntimeException("Local não encontrado"));


        establishment.setName(establishmentRequest.name());
        establishment.setLocal(local);
        establishment.setImgUrl(establishmentRequest.imgUrl());

        establishment = establishmentRepository.save(establishment);

        return establishmentMapper.toEstablishmentResponse(establishment);
    }

    private EstablishmentResponse getEstablishmentResponse(EstablishmentRequest establishmentRequest,
                                                          Establishment establishment) {

        Local local = localRepository.findById(establishmentRequest.localId())
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));


        Status status = statusService.buscarStatusPorId(establishmentRequest.statusId());

        establishment.setName(establishmentRequest.name());
        establishment.setLocal(local);
        establishment.setStatus(status);
        establishment.setImgUrl(establishmentRequest.imgUrl());

        establishment = establishmentRepository.save(establishment);

        return establishmentMapper.toEstablishmentResponse(establishment);
    }

    public EstablishmentResponse update(EstablishmentRequest establishmentResquest, Integer id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));
        return getEstablishmentResponse(establishmentResquest, establishment);
    }

    public EstablishmentResponse partialUpdate(EstablishmentRequest establishmentRequest, Integer id) {
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

        establishment = establishmentRepository.save(establishment);
        return establishmentMapper.toEstablishmentResponse(establishment);
    }

    public void delete(Integer id) {
        establishmentRepository.deleteById(id);
    }

    public EstablishmentResponse findById(Integer id) {
        Establishment establishment = establishmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));
        return establishmentMapper.toEstablishmentResponse(establishment);
    }

    public List<EstablishmentResponse> findAll() {

        List<Establishment> establishments = establishmentRepository.findAll();

        return establishmentMapper.toEstablishmentResponseList(establishments);
    }

    public List<EstablishmentResponse> findAllActives() {

        List<Establishment> establishments = establishmentRepository.findAllByStatus(statusService.buscarStatusPorNome("Ativo"));

        return establishmentMapper.toEstablishmentResponseList(establishments);
    }



    private Double evaluativeCalculation(Double voto, Integer numVotos, Double votoBanco, Integer numVotosBanco) {
        return ((votoBanco * numVotosBanco) + voto) / numVotosBanco + numVotos;
    }

    public List<EstablishmentFullResponse> findAllFull(Integer id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID do estabelecimento não pode ser nulo");
            }

            Optional<Establishment> establishmentOptional = establishmentRepository.findById(id);
            if (establishmentOptional.isEmpty()) {
                throw new NotFound("Estabelecimento não encontrado");
            }

            Establishment establishment = establishmentOptional.get();

            List<EmployeeRelacionamento> employees = employeeService.findAllByEstablishment(id);
            if (employees.isEmpty()) {
                throw new NotFound("Funcionários não encontrados");
            }

            List<FilterResponse> filters = filterService.findAllByEstablishment(establishment);
            if (filters.isEmpty()) {
                throw new NotFound("Filtros não encontrados");
            }

            List<ProductResponse> products = productService.findAllByEstablishment(establishment);
            if (products.isEmpty()) {
                throw new NotFound("Produtos não encontrados");
            }

            EstablishmentResponse establishmentResponse = establishmentMapper.toEstablishmentResponse(establishment);

            EstablishmentFullResponse establishmentFullResponse = new EstablishmentFullResponse(
                    establishmentResponse,
                    employees,
                    filters,
                    products);

            return List.of(establishmentFullResponse);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar estabelecimentos", e);
        }
    }

    public List<EstablishmentFullResponseList> findFull() {
        try {
            List<Establishment> establishments = establishmentRepository.findAll();
            List<EstablishmentFullResponseList> establishmentFullResponseLists = new ArrayList<>();

            for (Establishment establishment : establishments) {
                EstablishmentResponse establishmentResponse = establishmentMapper.toEstablishmentResponse(establishment);

                List<EmployeeRelacionamento> employees = findEmployeesByEstablishments(List.of(establishmentResponse));
                List<FilterResponse> filters = findFiltersByEstablishments(List.of(establishmentResponse));
                List<ProductResponse> products = findProductsByEstablishments(List.of(establishmentResponse));

                EstablishmentFullResponseList establishmentFullResponseList = new EstablishmentFullResponseList(
                        List.of(establishmentResponse),
                        employees,
                        filters,
                        products);

                establishmentFullResponseLists.add(establishmentFullResponseList);
            }

            return establishmentFullResponseLists;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar estabelecimentos", e);
        }
    }

    private List<EmployeeRelacionamento> findEmployeesByEstablishment(EstablishmentResponse establishments) {
        List<EmployeeRelacionamento> employees = employeeService.findAllByEstablishment(establishments.id());

        return employees;
    }

    private List<EmployeeRelacionamento> findEmployeesByEstablishments(List<EstablishmentResponse> establishments) {
        List<Integer> establishmentIds = establishments.stream().map(EstablishmentResponse::id)
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

    public List<EstablishmentFullResponse> matrix(Integer id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("ID do estabelecimento não pode ser nulo");
            }

            Optional<Establishment> establishmentOptional = establishmentRepository.findById(id);
            if (establishmentOptional.isEmpty()) {
                throw new NotFound("Estabelecimento não encontrado");
            }

            Establishment establishment = establishmentOptional.get();

            Queue<EmployeeRelacionamento> employeeQueue = new LinkedList<>();
            Stack<EmployeeRelacionamento> employeeStack = new Stack<>();

            List<EmployeeRelacionamento> employees = employeeService.findAllByEstablishment(id);
            if (employees.isEmpty()) {
                throw new NotFound("Funcionários não encontrados");
            }

            for (EmployeeRelacionamento employee : employees) {
                employeeQueue.add(employee);
                employeeStack.push(employee);
            }

            Queue<FilterResponse> filterQueue = new LinkedList<>();
            Stack<FilterResponse> filterStack = new Stack<>();

            List<FilterResponse> filters = filterService.findAllByEstablishment(establishment);
            if (filters.isEmpty()) {
                throw new NotFound("Filtros não encontrados");
            }

            for (FilterResponse filter : filters) {
                filterQueue.add(filter);
                filterStack.push(filter);
            }

            Queue<ProductResponse> productQueue = new LinkedList<>();
            Stack<ProductResponse> productStack = new Stack<>();

            List<ProductResponse> products = productService.findAllByEstablishment(establishment);
            if (products.isEmpty()) {
                throw new NotFound("Produtos não encontrados");
            }

            for (ProductResponse product : products) {
                productQueue.add(product);
                productStack.push(product);
            }

            EstablishmentResponse establishmentResponse = establishmentMapper.toEstablishmentResponse(establishment);

            EstablishmentFullResponse establishmentFullResponse = new EstablishmentFullResponse(
                    establishmentResponse,
                    new ArrayList<>(employeeQueue),
                    new ArrayList<>(filterQueue),
                    new ArrayList<>(productQueue));

            return List.of(establishmentFullResponse);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar estabelecimentos", e);
        }
    }


    

}