package miu.waa.project1.service.impl;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.common.AccountStatus;
import miu.waa.project1.common.Role;
import miu.waa.project1.exception.UserNotFound;
import miu.waa.project1.model.Thread;
import miu.waa.project1.model.User;
import miu.waa.project1.repository.UserRepository;
import miu.waa.project1.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public List<User> findByMajorEntryYearAndRelevant(
			String major,
			Integer entryYear,
			String keyword
	) {
		return userRepository.findByMajorEntryYearAndRelevant(major, entryYear, keyword);
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User createUser(User user) {
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.STUDENT);

		return userRepository.save(user);
	}

	public User updateUser(Long id, User user) {
		User existUser = userRepository.findById(id).orElse(null);
		if (existUser == null) return null;
		existUser.setFirstName(user.getFirstName());
		existUser.setLastName(user.getLastName());
		existUser.setBio(user.getBio());
		existUser.setAvatar(user.getAvatar());
		existUser.setAchievements(user.getAchievements());
		existUser.setInterests(user.getInterests());
		existUser.setExtracurricularActivities(user.getExtracurricularActivities());
		return userRepository.save(existUser);
	}

	@Override
	public User updateStatus(Long id, AccountStatus status) {
		User item = userRepository.findById(id).orElse(null);
		if (item == null) {
			throw new UserNotFound();
		}
		item.setStatus(status);
		return item;
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public boolean reportUser(Long id) {
		User item = userRepository.findById(id).orElse(null);
		if (item == null) {
			throw new UserNotFound();
		}
		item.setReports(item.getReports() + 1);
		userRepository.save(item);
		return true;
	}
}
