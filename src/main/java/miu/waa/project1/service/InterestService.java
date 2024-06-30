package miu.waa.project1.service;

import miu.waa.project1.model.Interest;
import miu.waa.project1.model.User;

public interface InterestService {
    Interest getInterestById(Long id);
    Interest createInterest(Interest interest);
    Interest updateInterest(Long id, Interest interest);
    void deleteInterest(Long id);
    Interest createInterestByUser(Interest interest, User user);
}
