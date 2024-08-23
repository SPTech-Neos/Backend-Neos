package school.sptech.neosspringjava.api.controllers.ratingController;

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
import school.sptech.neosspringjava.api.dtos.ratingDto.RatingRequest;
import school.sptech.neosspringjava.api.dtos.ratingDto.RatingResponse;
import school.sptech.neosspringjava.api.mappers.ratingMapper.RatingMapper;
import school.sptech.neosspringjava.service.ratingService.RatingService;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    
    @PostMapping("/avaliate/establishment")
    public ResponseEntity<RatingResponse> ratingEstablishment(@RequestBody RatingRequest ratingRequest) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(RatingMapper.toResponse(ratingService.avaliateEstablishment(ratingRequest)));
    }

    @PostMapping("/avaliate/employee")
    public ResponseEntity<RatingResponse> ratingEmployee(@RequestBody RatingRequest ratingRequest) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(RatingMapper.toResponse(ratingService.avaliateEmployee(ratingRequest)));
    }

    @PostMapping("/avaliate/product")
    public ResponseEntity<RatingResponse> ratingProduct(@RequestBody RatingRequest ratingRequest) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(RatingMapper.toResponse(ratingService.avaliateProduct(ratingRequest)));
    }

    @PostMapping("/avaliate/service")
    public ResponseEntity<RatingResponse> ratingService(@RequestBody RatingRequest ratingRequest) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(RatingMapper.toResponse(ratingService.avaliateService(ratingRequest)));
    }

    @GetMapping
    public ResponseEntity<List<RatingResponse>> listAllRatings() {
        return ResponseEntity.ok(ratingService.listAllRatings());
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
