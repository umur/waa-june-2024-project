package miu.waa.project1.service.impl;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.model.Activity;
import miu.waa.project1.model.User;
import miu.waa.project1.repository.ActivityRepository;
import miu.waa.project1.service.ActivityService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    @Override
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public Activity updateActivity(Long id, Activity activity) {
        Activity existactivity = activityRepository.findById(id).orElse(null);
        if (existactivity == null) return null;
        existactivity.setName(activity.getName());
        return activityRepository.save(existactivity);
    }

    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public Activity createActivityByUser(Activity activity, User user) {
        activity.setUser(user);
        return activityRepository.save(activity);
    }
}
