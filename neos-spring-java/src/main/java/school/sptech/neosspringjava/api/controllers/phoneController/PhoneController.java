package school.sptech.neosspringjava.api.controllers.phoneController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.neosspringjava.api.dtos.phoneDto.PhoneResponse;

@RequestMapping("/phones")
@RestController
@RequiredArgsConstructor
public class PhoneController {

    public PhoneResponse findAllPhones(){
        return null;
    }
}
