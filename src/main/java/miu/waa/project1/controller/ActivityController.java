package miu.waa.project1.controller;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.model.Activity;
import miu.waa.project1.service.impl.ActivityServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityServiceImpl activityService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getActivityById(@PathVariable Long id) {
        Activity activity = activityService.getActivityById(id);
        if(activity == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activity not found!");
        return ResponseEntity.ok().body(activity);
    }

    @PostMapping
    public Activity createUser(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        Activity updatedActivity = activityService.updateActivity(id, activity);
        if (updatedActivity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot update activity!");
        }
        return ResponseEntity.ok(updatedActivity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
