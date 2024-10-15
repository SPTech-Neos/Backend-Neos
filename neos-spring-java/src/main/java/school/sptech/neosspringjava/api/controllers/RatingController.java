package school.sptech.neosspringjava.api.controllers;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.neosspringjava.api.dtos.ratingDto.*;
import school.sptech.neosspringjava.api.mappers.RatingMapper;
import school.sptech.neosspringjava.service.RatingService;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    
    @PostMapping("/avaliate/establishment")
    public ResponseEntity<RatingEstablishmentResponse> ratingEstablishment(@RequestBody RatingEstablishmentRequest ratingRequest) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(RatingMapper.toResponseEstablishment(ratingService.avaliateEstablishment(ratingRequest)));
    }
//    @GetMapping("/avaliate/establishment/id")
//    public ResponseEntity<RatingEstablishmentResponse> findAvgRatingsById(@PathVariable Integer id) {
//        return ResponseEntity.ok(RatingMapper.toResponseEstablishment(ratingService.findAvgEstablishmentRating(id)));
//    }

    @PostMapping("/avaliate/employee")
    public ResponseEntity<RatingEmployeeResponse> ratingEmployee(@RequestBody RatingEmployeeRequest ratingRequest) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(RatingMapper.toResponseEmployee(ratingService.avaliateEmployee(ratingRequest)));
    }

    @PostMapping("/avaliate/product")
    public ResponseEntity<RatingProductResponse> ratingProduct(@RequestBody RatingProductRequest ratingRequest) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(RatingMapper.toResponseProduct(ratingService.avaliateProduct(ratingRequest)));
    }

    @PostMapping("/avaliate/service")
    public ResponseEntity<RatingServiceResponse> ratingService(@RequestBody RatingServiceRequest ratingRequest) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(RatingMapper.toResponseService(ratingService.avaliateService(ratingRequest)));
    }

    @GetMapping
    public ResponseEntity<List<RatingResponse>> listAllRatings() {
        return ResponseEntity.ok(ratingService.listAllRatings());
    }

    @PostMapping
    public ResponseEntity<RatingResponse> saveRating(@RequestBody RatingRequest ratingRequest) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(RatingMapper.toResponse(ratingService.saveRating(ratingRequest)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> findRatingById(@PathVariable Integer id) {
        return ResponseEntity.ok(ratingService.findRatingById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Integer id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingResponse> updateRating(@PathVariable Integer id, @RequestBody RatingRequest ratingRequest) {
        return ResponseEntity.ok(ratingService.updateRating(id, ratingRequest));
    }

    
}
