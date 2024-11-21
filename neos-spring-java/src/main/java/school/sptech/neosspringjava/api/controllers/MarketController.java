package school.sptech.neosspringjava.api.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.neosspringjava.api.dtos.marketDto.MarketResponse;
import school.sptech.neosspringjava.api.mappers.MarketMapper;
import school.sptech.neosspringjava.service.MarketService;

import java.util.List;

@RestController
@RequestMapping("markets")
@RequiredArgsConstructor
public class MarketController {
    private final MarketService mService;

    @GetMapping
    public ResponseEntity<List<MarketResponse>> findAll(){
        return ResponseEntity.ok(MarketMapper.toResponse(mService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(MarketMapper.toResponse(mService.findById(id)));
    }

    @GetMapping("/establishmentId/{id}")
    public ResponseEntity <List<MarketResponse>> findByEstablishmentId(@PathVariable Integer id){
        List<MarketResponse> listMarketResponses = MarketMapper.toResponse(mService.findByEstablishmentId(id));
        System.out.println(listMarketResponses.toString());
        System.out.println(listMarketResponses.isEmpty());
        if (listMarketResponses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
        return ResponseEntity.ok(listMarketResponses);
    }
}

    @GetMapping("/clientId/{id}")
    public ResponseEntity<List<MarketResponse>> findByClientId(@PathVariable Integer id){
        List<MarketResponse> listMarketResponses = MarketMapper.toResponse(mService.findByClientId(id)); 
        if (listMarketResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(listMarketResponses);
        }else{
        return ResponseEntity.ok(listMarketResponses);
    }
}
    

}
