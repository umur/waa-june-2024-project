package edu.university_connect.service.user;

import edu.university_connect.config.ContextUser;
import edu.university_connect.domain.entity.Profile;
import edu.university_connect.domain.entity.Student;
import edu.university_connect.domain.entity.User;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.ProfileDtoMapper;
import edu.university_connect.mapper.UserDtoMapper;
import edu.university_connect.model.contract.dto.ProfileDto;
import edu.university_connect.model.contract.request.auth.SignUpRequest;
import edu.university_connect.model.contract.request.profile.ProfileRequest;
import edu.university_connect.model.contract.request.user.BlockRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.model.contract.request.user.UserCreateRequest;
import edu.university_connect.model.contract.request.user.UserUpdateRequest;
import edu.university_connect.repository.UserRepository;
import edu.university_connect.service.profile.ProfileService;
import edu.university_connect.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final StudentService studentService;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;
    private final ContextUser contextUser;


    @Override
    public Page<UserDto> getPage(Pageable pageable) {
        Page<User> page = repository.findAll(pageable);
        return page.map(UserDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(UserDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> userOpt= getUserById(id);
        if(userOpt.isPresent()){
            return UserDtoMapper.MAPPER.entityToDto(userOpt.get());
        }
        else {
            log.error("User with id {} does not exist",id);
            throw ServiceException.of(AppStatusCode.E40000, "user");
        }
    }

    @Override
    public UserDto create(UserCreateRequest createRequest) {

        User user= UserDtoMapper.MAPPER.dtoToEntity(createRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser=repository.save(user);
        return UserDtoMapper.MAPPER.entityToDto(savedUser);
    }

    @Override
    public UserDto update(Long id, UserUpdateRequest updateRequest) {
        Optional<User> userOpt= getUserById(id);
        if (userOpt.isPresent()){
            User user=userOpt.get();
            user.setUsername(updateRequest.getUsername());
            user.setEmail(updateRequest.getEmail());
            user.setEnabled(updateRequest.isEnabled());
            User savedUser=repository.save(user);
            return UserDtoMapper.MAPPER.entityToDto(savedUser);
        }
        else {
            log.error("User with id {} does not exist",id);
            throw ServiceException.of(AppStatusCode.E40000, "user");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }

    private Optional<User> getUserById(Long id){
        return repository.findById(id);
    }



    @Override
    public UserDto signUp(SignUpRequest data) {
        if(!data.getPassword().equals(data.getConfirmPassword())){
            throw ServiceException.of(AppStatusCode.E40003);
        }
        Optional<Student> studentOpt=studentService.getStudentByEmail(data.getEmail());
        if(studentOpt.isEmpty()){
            log.error("Student with email {} does not exist",data.getEmail());
            throw ServiceException.of(AppStatusCode.E40000,"student", "email= "+data.getEmail());
        }
        if(Objects.nonNull(studentOpt.get().getUser())){
            log.error("Student with email {} is already associated with another user",data.getEmail());
            throw ServiceException.of(AppStatusCode.E40006,"user", "email= "+data.getEmail());
        }
        User user = UserDtoMapper.MAPPER.dtoToEntity(data);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        User savedUser=repository.save(user);
        Student student=studentOpt.get();
        student.setUser(savedUser);
        studentService.saveStudent(student);
        return UserDtoMapper.MAPPER.entityToDto(savedUser);
    }

    @Override
    public ProfileDto getUserProfile(Long id) {
        Optional<Profile> profileOpt=profileService.getProfileByUserId(id);
        Profile profile=null;
        if(profileOpt.isPresent()){
            profile=profileOpt.get();
        }
        return ProfileDtoMapper.MAPPER.entityToDto(profile);
    }

    @Override
    public ProfileDto updateUserProfile(Long id, ProfileRequest updateRequest) {
        Optional<User> userOpt=repository.findById(id);
        if(userOpt.isEmpty()){
            log.error("User with id {} does not exist",id);
            throw ServiceException.of(AppStatusCode.E40000,"user", "id= "+id);
        }
        Optional<Profile> profileOpt=profileService.getProfileByUserId(id);
        Profile profileReq=ProfileDtoMapper.MAPPER.dtoToEntity(updateRequest);
        Profile profile=profileReq;
        if(profileOpt.isPresent()){
            profile=profileOpt.get();
            profile.setAchievements(profileReq.getAchievements());
            profile.setInterests(profileReq.getInterests());
            profile.setExtraCurricularActivities(profileReq.getExtraCurricularActivities());
        }
        else{
            profile.setUser(userOpt.get());
        }
        profileService.saveUserProfile(profile);
        return ProfileDtoMapper.MAPPER.entityToDto(profile);
    }

    @Override
    public boolean blockUser(Long id, BlockRequest request) {
        Optional<User> blockerOpt=repository.findByUsername(contextUser.getUser().getUsername());
        Optional<User> blockedOpt=repository.findById(request.getUserId());
        if(blockerOpt.isEmpty() || blockedOpt.isEmpty()){
            throw ServiceException.of(AppStatusCode.E40000,request.getUserId().toString());
        }
        User blocker=blockerOpt.get();
        List<User> blockedUsers=blocker.getBlockedUsers();
        blockedUsers.add(blockedOpt.get());
        blocker.setBlockedUsers(blockedUsers);
        repository.save(blocker);
        return true;
    }

    @Override
    public boolean unblockUser(Long id, BlockRequest request) {
        Optional<User> blockerOpt=repository.findByUsername(contextUser.getUser().getUsername());
        Optional<User> blockedOpt=repository.findById(request.getUserId());
        if(blockerOpt.isEmpty() || blockedOpt.isEmpty()){
            throw ServiceException.of(AppStatusCode.E40000,request.getUserId().toString());
        }
        User blocker=blockerOpt.get();
        List<User> blockedUsers=blocker.getBlockedUsers();
        blockedUsers.remove(blockedOpt.get());
        blocker.setBlockedUsers(blockedUsers);
        repository.save(blocker);
        return true;
    }
}
