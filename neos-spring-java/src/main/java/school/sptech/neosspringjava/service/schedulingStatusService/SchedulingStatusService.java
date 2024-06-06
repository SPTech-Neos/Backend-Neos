package school.sptech.neosspringjava.service.schedulingStatusService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.schedulingStatusDto.schedulingStatusRequest;
import school.sptech.neosspringjava.domain.model.schedulingStatus.schedulingStatus;
import school.sptech.neosspringjava.domain.repository.schedulingStatusRepository.SchedulingStatusRepository;

@Service
@RequiredArgsConstructor
public class SchedulingStatusService {

    private final SchedulingStatusRepository schedulingStatusRepository;

    public List<schedulingStatus> findAll() {
        return schedulingStatusRepository.findAll();
    }

    public schedulingStatus save(schedulingStatusRequest schedulingStatusRequest) {
        schedulingStatus schedulingStatus = new schedulingStatus();
        schedulingStatus.setDescription(schedulingStatusRequest.description());
        return schedulingStatusRepository.save(schedulingStatus);
    }

    public schedulingStatus update(Integer id, schedulingStatusRequest schedulingStatusRequest) {
        schedulingStatus schedulingStatus = schedulingStatusRepository.findById(id).orElse(null);
        schedulingStatus.setDescription(schedulingStatusRequest.description());
        return schedulingStatusRepository.save(schedulingStatus);
    }


    public schedulingStatus findById(Integer id) {
        return schedulingStatusRepository.findById(id).orElse(null);
    }

    public void deleteByid(Integer id) {
        schedulingStatusRepository.deleteById(id);
    }

    public List<schedulingStatus> findAllByDescription(String description) {
        return schedulingStatusRepository.findAll().stream().filter(s -> s.getDescription().contains(description))
                .collect(Collectors.toList());
    }
}
