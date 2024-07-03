package edu.university_connect.service.user;

import edu.university_connect.model.contract.dto.ProfileDto;
import edu.university_connect.model.contract.dto.SearchDto;
import edu.university_connect.model.contract.request.auth.SignUpRequest;
import edu.university_connect.model.contract.request.profile.ProfileRequest;
import edu.university_connect.model.contract.request.user.BlockRequest;
import edu.university_connect.model.contract.request.user.UserCreateRequest;
import edu.university_connect.model.contract.request.user.UserUpdateRequest;
import edu.university_connect.model.contract.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserDto> getPage(Pageable pageable);
    List<UserDto> getAll();
    UserDto getById(Long id);

    UserDto create(UserCreateRequest createRequest);

    UserDto update(Long id, UserUpdateRequest updateRequest);

    boolean delete(Long id);

    UserDto signUp(SignUpRequest data);

    ProfileDto getUserProfile(Long id);

    ProfileDto updateUserProfile(Long id, ProfileRequest updateRequest);

    List<SearchDto> getAllStudentsByName(String uname);

    boolean blockUser(Long id, BlockRequest request);

    boolean unblockUser(Long id, BlockRequest request);
}