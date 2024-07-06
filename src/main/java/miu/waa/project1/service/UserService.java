package miu.waa.project1.service;

import miu.waa.project1.common.AccountStatus;
import miu.waa.project1.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
	Page<User> findByMajorEntryYearAndRelevant(String major, Integer entryYear, String keyword, Pageable pageable);

	User getUserById(Long id);

	User getUserByEmail(String email);

	User createUser(User user);

	User updateUser(Long id, User user);

	User updateStatus(Long id, AccountStatus status);

	List<User> getAll();

	void reportUser(Long id);
}
