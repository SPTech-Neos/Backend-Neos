package school.sptech.neosspringjava.api.mappers.serviceCategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryRequest;
import school.sptech.neosspringjava.api.dtos.serviceCategoryDto.ServiceCategoryResponse;
import school.sptech.neosspringjava.domain.model.serviceCategory.ServiceCategory;
import school.sptech.neosspringjava.domain.model.serviceType.ServiceType;

@Component
public class ServiceCategoryMapper {
        public static ServiceCategoryResponse toServiceCategoryResponse(ServiceCategory servicecategory) {
       return new ServiceCategoryResponse(servicecategory.getId(), servicecategory.getName());
    }

    public static List<ServiceCategoryResponse> toServiceResponseList(List<ServiceCategory> serviceCategoryList) {
        return serviceCategoryList.stream()
                              .map(ServiceCategoryMapper::toServiceCategoryResponse)
                              .collect(Collectors.toList());
    }

    public static ServiceCategory toServiceCategory(ServiceCategoryRequest serviceCategoryRequest) {
        return ServiceCategory.builder()
                              .name(serviceCategoryRequest.name())
                              .build();
    }

   public static  List<ServiceCategory> toServiceCategoryList(List<ServiceCategoryRequest> serviceCategoryRequestList) {
        return serviceCategoryRequestList.stream()
                              .map(ServiceCategoryMapper::toServiceCategory)
                              .collect(Collectors.toList());
    }

 
}
