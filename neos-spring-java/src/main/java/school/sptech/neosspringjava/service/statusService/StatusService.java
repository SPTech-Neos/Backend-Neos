package school.sptech.neosspringjava.service.statusService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.domain.model.status.Status;
import school.sptech.neosspringjava.domain.repository.status.StatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public Status buscarStatusPorNome(String status){
        return statusRepository.findByName(status).orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }

    public Status buscarStatusPorId(Integer id){
        return statusRepository.findById(id).get();
    }



}
