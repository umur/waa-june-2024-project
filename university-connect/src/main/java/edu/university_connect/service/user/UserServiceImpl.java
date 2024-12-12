package edu.university_connect.service.user;

import edu.university_connect.config.ContextUser;
import edu.university_connect.domain.entity.Profile;
import edu.university_connect.domain.entity.Role;
import edu.university_connect.domain.entity.Student;
import edu.university_connect.domain.entity.User;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.ProfileDtoMapper;
import edu.university_connect.mapper.UserDtoMapper;
import edu.university_connect.model.contract.dto.*;
import edu.university_connect.model.contract.request.auth.SignUpRequest;
import edu.university_connect.model.contract.request.profile.ProfileRequest;
import edu.university_connect.model.contract.request.user.BlockRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.model.contract.request.user.UserCreateRequest;
import edu.university_connect.model.contract.request.user.UserUpdateRequest;
import edu.university_connect.repository.UserRepository;
import edu.university_connect.service.profile.ProfileService;
import edu.university_connect.service.resource.ResourceService;
import edu.university_connect.service.role.RoleService;
import edu.university_connect.service.student.StudentService;
import edu.university_connect.service.survey.SurveyService;
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
    private final RoleService roleService;
    private final StudentService studentService;
    private final ResourceService resourceService;
    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;
    private final ContextUser contextUser;
    private final SurveyService surveyService;


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
        if(userOpt.isPresent()){
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
        Optional<User> existingUserOpt=repository.findByUsername(data.getUsername());
        if(existingUserOpt.isPresent()){
            log.error("User with username {} already exist",data.getUsername());
            throw ServiceException.of(AppStatusCode.E40006,"user", "username= "+data.getUsername());
        }
        Optional<Student> studentOpt=studentService.getStudentByEmail(data.getEmail());
        Optional<Role> roleOpt=roleService.getRoleByCode("normal-user");
        if(studentOpt.isEmpty()){
            log.error("Student with email {} does not exist",data.getEmail());
            throw ServiceException.of(AppStatusCode.E40000,"student", "email= "+data.getEmail());
        }
        if(roleOpt.isEmpty()){
            log.error("Role for normal users is not set up in db");
            throw ServiceException.of(AppStatusCode.E40000,"role");
        }
        if(Objects.nonNull(studentOpt.get().getUser())){
            log.error("Student with email {} is already associated with another user",data.getEmail());
            throw ServiceException.of(AppStatusCode.E40006,"user", "email= "+data.getEmail());
        }
        User user = UserDtoMapper.MAPPER.dtoToEntity(data);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setRoles( new HashSet<>() {{
            add(roleOpt.get());
        }});
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
    public List<SearchDto> getAllStudentsByName(String uname) {
        List<User> users = repository.findAllByUsername(uname);
        return users.stream().map(user -> {
            SearchDto searchDto = new SearchDto();
            searchDto.setUserDto(UserDtoMapper.MAPPER.entityToDto(user));
            Optional<Profile> profileOpt = profileService.getProfileByUserId(user.getId());
            if (profileOpt.isPresent()) {
                searchDto.setProfileDto(ProfileDtoMapper.MAPPER.entityToDto(profileOpt.get()));
            }
            return searchDto;
        }).toList();
    }

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

    @Override
    public Page<UserDto> getBlockedUsers(Long id,Pageable pageable) {
        Page<User> page = repository.findBlockedUsers(id,pageable);
        return page.map(UserDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public Page<ResourceDto> getUserResources(Long id, Pageable pageable) {
        return resourceService.getUserResourcePage(id,pageable);
    }

    @Override
    public Page<SurveyDto> getUserSurveys(Long id, Pageable pageable) {
        return surveyService.getUserSurveyPage(id,pageable);
    }
}
