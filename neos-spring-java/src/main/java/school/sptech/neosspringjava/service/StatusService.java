package school.sptech.neosspringjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import school.sptech.neosspringjava.domain.model.Status;
import school.sptech.neosspringjava.domain.repository.StatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public Status findStatusByName(String status){
        return statusRepository.findByName(status).orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }

    public Status findById(Integer id){
        return statusRepository.findById(id).get();
    }

    public List<Status> findAll(){
        return statusRepository.findAll();
    }

    public Status save(Status status){
        return statusRepository.save(status);
    }

    public void delete(Integer id){
        statusRepository.deleteById(id);
    }



}
