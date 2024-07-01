package miu.waa.project1.service;

import miu.waa.project1.model.User;

import java.util.List;

public interface UserService {
    List<User> findByMajorEntryYearAndRelevant(String major, Integer entryYear, String keyword);
    User getUserById(Long id);
    User getUserByEmail(String email);
    User createUser(User user);
    User updateUser(Long id, User user);
}