package school.sptech.neosspringjava.service.establishmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cloudinary.api.exceptions.NotFound;

import ch.qos.logback.core.filter.Filter;
import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.FilterDto.FilterResponse;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeRelacionamento;
import school.sptech.neosspringjava.api.dtos.employee.EmployeeResponse;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentFullResponse;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRequest;
import school.sptech.neosspringjava.api.dtos.establishmentDTO.EstablishmentRespose;
import school.sptech.neosspringjava.api.dtos.produtcDto.ProductResponse;
import school.sptech.neosspringjava.api.dtos.serviceDto.ServiceResponse;
import school.sptech.neosspringjava.api.mappers.establishmentMapper.EstablishmentMapper;
import school.sptech.neosspringjava.domain.model.company.Company;
import school.sptech.neosspringjava.domain.model.establishment.Establishment;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.repository.companyRepository.CompanyRepository;
import school.sptech.neosspringjava.domain.repository.establishmentRopository.EstablishmentRopository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;
import school.sptech.neosspringjava.service.employeeService.EmployeeService;
import school.sptech.neosspringjava.service.filterService.FilterService;
import school.sptech.neosspringjava.service.integracaoImageApi.IntegracaoImageApiService;
import school.sptech.neosspringjava.service.productService.ProductService;
import school.sptech.neosspringjava.service.serviceService.ServiceService;

@Service
@RequiredArgsConstructor
public class EstablishmentService {

    private final EstablishmentRopository establishmentRopository;
    private final EstablishmentMapper establishmentMapper;
    private final LocalRepository localRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeService employeeService;
    private final FilterService filterService;
    private final ProductService productService;

    public EstablishmentRespose save(EstablishmentRequest establishmentRequest) {
        Establishment establishment = new Establishment();

        Integer localId = establishmentRequest.localId();
        if (localId == null) {
            throw new IllegalArgumentException("ID do local não pode ser nulo");
        }

        Local local = localRepository.findById(localId).orElseThrow(() -> new RuntimeException("Local não encontrado"));

        Integer companyId = establishmentRequest.companyId();
        if (companyId == null) {
            throw new IllegalArgumentException("ID da empresa não pode ser nulo");
        }

        Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        establishment.setName(establishmentRequest.name());
        establishment.setCompany(company);
        establishment.setLocal(local);
        establishment.setImgUrl(establishmentRequest.imgUrl());

        establishment = establishmentRopository.save(establishment);

        return establishmentMapper.toEstablishmentResponse(establishment);
    }


    private EstablishmentRespose getEstablishmentRespose(EstablishmentRequest establishmentRequest, Establishment establishment) {
        Local local = localRepository.findById(establishmentRequest.localId()).orElseThrow(() -> new RuntimeException("Local não encontrado"));

        Company company = companyRepository.findById(establishmentRequest.companyId()).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        establishment.setName(establishmentRequest.name());
        establishment.setCompany(company);
        establishment.setLocal(local);
        establishment.setImgUrl(establishmentRequest.imgUrl());

        establishment = establishmentRopository.save(establishment);

        return establishmentMapper.toEstablishmentResponse(establishment);
    }


    public EstablishmentRespose update(EstablishmentRequest establishmentResquest, Integer id) {
        Establishment establishment = establishmentRopository.findById(id).orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));
        return getEstablishmentRespose(establishmentResquest, establishment);
    }

    public EstablishmentRespose partialUpdate(EstablishmentRequest establishmentRequest, Integer id) {
        Establishment establishment = establishmentRopository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));

        if (establishmentRequest.name() != null) {
            establishment.setName(establishmentRequest.name());
        }
        if (establishmentRequest.localId() != null) {
            Local local = localRepository.findById(establishmentRequest.localId())
                    .orElseThrow(() -> new RuntimeException("Local não encontrado"));
            establishment.setLocal(local);
        }
        if (establishmentRequest.companyId() != null) {
            Company company = companyRepository.findById(establishmentRequest.companyId())
                    .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
            establishment.setCompany(company);
        }
        if (establishmentRequest.imgUrl() != null) {
            establishment.setImgUrl(establishmentRequest.imgUrl());
        }

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
    }


    public List<EstablishmentFullResponse> findAllFull(Integer id) {
        List<EstablishmentFullResponse> retorno = new ArrayList<>();

        try{
            Optional<Establishment> establishment = establishmentRopository.findById(id);


            if (id ==null) {
                throw new IllegalArgumentException("ID do estabelecimento não pode ser nulo");
            }

            if (establishment.isEmpty()) {
                throw new NotFound("Estabelecimento não encontrado");
            }
            EstablishmentRespose establishmentRespose = establishmentMapper.toEstablishmentResponse(establishment.get());

            List<EmployeeRelacionamento> employees = employeeService.findAllByEstablishment(id);
            if (establishment == null) {
                throw new NotFound("Estabelecimento não encontrado");
            }


            List<FilterResponse> filters = filterService.findAllByEstablishment(establishment.get());
            if (filters.isEmpty()) {
                throw new NotFound("Filtros não encontrados");
            }


            List<ProductResponse> products = productService.findAllByEstablishment(establishment.get());

            if (products.isEmpty()) {
                throw new NotFound("Produtos não encontrados");
                
            }



            EstablishmentFullResponse establishmentFullResponse = new EstablishmentFullResponse(establishmentRespose , employees, filters, products);
            retorno.add(establishmentFullResponse);
          return retorno;
        
        }catch (Exception e){
            throw new RuntimeException("Erro ao buscar estabelecimentos");
        }



    }
}
