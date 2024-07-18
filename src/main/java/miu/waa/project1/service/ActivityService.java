package miu.waa.project1.service;

import miu.waa.project1.model.Activity;
import miu.waa.project1.model.User;

public interface ActivityService {
    Activity getActivityById(Long id);
    Activity createActivity(Activity activity);
    Activity updateActivity(Long id, Activity activity);
    void deleteActivity(Long id);
    Activity createActivityByUser(Activity activity, User user);
}
