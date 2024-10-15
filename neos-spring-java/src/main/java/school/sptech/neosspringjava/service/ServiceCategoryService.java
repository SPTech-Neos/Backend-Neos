package school.sptech.neosspringjava.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.schedulingDto.ServiceCategoryQuantityDTO;
import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryRequest;
import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryResponse;
import school.sptech.neosspringjava.api.mappers.ServiceCategoryMapper;
import school.sptech.neosspringjava.domain.model.Establishment;
import school.sptech.neosspringjava.domain.model.ServiceCategory;
import school.sptech.neosspringjava.domain.repository.EstablishmentRepository;
import school.sptech.neosspringjava.domain.repository.SchedulingRepository;
import school.sptech.neosspringjava.domain.repository.ServiceCategoryRepository;

@Service
@RequiredArgsConstructor
public class ServiceCategoryService {

    private final ServiceCategoryRepository serviceCategoryRepository;
    private final SchedulingRepository schedulingRepository;
    private final EstablishmentRepository establishmentRepository;

    public List<ServiceCategoryResponse> findAll() {
        List<ServiceCategory> serviceCategories = serviceCategoryRepository.findAll();
        return ServiceCategoryMapper.toServiceResponseList(serviceCategories);
    }

    public ServiceCategoryResponse save(ServiceCategoryRequest serviceCategoryRequest) {
        ServiceCategory serviceCategory = ServiceCategoryMapper.toServiceCategory(serviceCategoryRequest);
        serviceCategory = serviceCategoryRepository.save(serviceCategory);
        return ServiceCategoryMapper.toServiceCategoryResponse(serviceCategory);
    }

    public ServiceCategoryResponse update(ServiceCategoryRequest serviceCategoryRequest, Integer id) {
        Optional<ServiceCategory> serviceCategoryOptional = serviceCategoryRepository.findById(id);
        if (serviceCategoryOptional.isPresent()) {
            ServiceCategory serviceCategory = serviceCategoryOptional.get();
            serviceCategory.setName(serviceCategoryRequest.name());
            serviceCategory = serviceCategoryRepository.save(serviceCategory);
            return ServiceCategoryMapper.toServiceCategoryResponse(serviceCategory);
        }
        return null;
    }

    public void delete(Integer id) {
        serviceCategoryRepository.deleteById(id);

    }

    public ServiceCategoryResponse findById(Integer id) {
        Optional<ServiceCategory> serviceCategoryOptional = serviceCategoryRepository.findById(id);
        if (serviceCategoryOptional.isPresent()) {
            ServiceCategory serviceCategory = serviceCategoryOptional.get();
            return ServiceCategoryMapper.toServiceCategoryResponse(serviceCategory);
        }
        return null;
    }

    public List<ServiceCategoryQuantityDTO> findServiceCategoryQuantityByEstablishment(Integer establishmentId, Integer limit) {
        Establishment e = establishmentRepository.findById(establishmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado"));
    
        List<ServiceCategoryQuantityDTO> result = schedulingRepository.findServiceCategoryQuantityByEstablishment(establishmentId);
    
        // Limite manual no resultado
        if (result.size() > limit) {
            return result.subList(0, limit);
        } else if (result.size() == 0) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estabelecimento não encontrado");
        }
    
        return result;
    }
    

}

