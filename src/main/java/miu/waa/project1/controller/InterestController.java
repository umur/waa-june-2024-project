package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.model.Interest;
import miu.waa.project1.service.impl.InterestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/interests")
@RequiredArgsConstructor
public class InterestController {
    private final InterestServiceImpl interestService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getInterestById(@PathVariable Long id) {
        Interest interest = interestService.getInterestById(id);
        if(interest == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Interest not found!");
        return ResponseEntity.ok().body(interest);
    }

    @PostMapping
    public Interest createUser(@RequestBody Interest interest) {
        return interestService.createInterest(interest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInterest(@PathVariable Long id, @RequestBody Interest interest) {
        Interest updatedInterest = interestService.updateInterest(id, interest);
        if (updatedInterest == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot update interest!");
        }
        return ResponseEntity.ok(updatedInterest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInterest(@PathVariable Long id) {
        interestService.deleteInterest(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
