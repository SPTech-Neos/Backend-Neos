package school.sptech.neosspringjava.service.localService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import school.sptech.neosspringjava.api.dtos.LocalDto.LocalRequest;
import school.sptech.neosspringjava.api.dtos.LocalDto.LocalResponse;
import school.sptech.neosspringjava.api.mappers.localMapper.LocalMapper;
import school.sptech.neosspringjava.domain.model.address.Address;
import school.sptech.neosspringjava.domain.model.local.Local;
import school.sptech.neosspringjava.domain.repository.adressRepository.AddressRepository;
import school.sptech.neosspringjava.domain.repository.localRepository.LocalRepository;

import java.util.Collections;
import java.util.List;

public class LocalServiceTest {

    @Mock
    private LocalRepository localRepository;


}
