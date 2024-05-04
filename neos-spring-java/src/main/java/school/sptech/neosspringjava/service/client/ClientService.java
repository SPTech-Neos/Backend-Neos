package school.sptech.neosspringjava.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.neosspringjava.domain.repository.clientRepository.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
}
