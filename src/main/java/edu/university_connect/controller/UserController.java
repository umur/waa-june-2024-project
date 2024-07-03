package edu.university_connect.controller;

import edu.university_connect.model.contract.dto.ProfileDto;
import edu.university_connect.model.contract.request.action.ActionUpdateRequest;
import edu.university_connect.model.contract.request.profile.ProfileRequest;
import edu.university_connect.model.contract.request.user.BlockRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.model.contract.request.user.UserCreateRequest;
import edu.university_connect.model.contract.request.user.UserUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.user.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Slf4j
public class UserController {
    private final UserService service;

    private final MessagingService messagingService;

    public UserController(UserService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAll() {
        List<UserDto> response= service.getAll();
        ApiResponse<List<UserDto>> apiResponse =  new ApiResponse<List<UserDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<UserDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<UserDto> response= service.getPage(pageable);
        ApiResponse<Page<UserDto>> apiResponse =  new ApiResponse<Page<UserDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<UserDto>> create(@Valid @RequestBody UserCreateRequest createRequest) {
        UserDto response= service.create(createRequest);
        ApiResponse<UserDto> apiResponse =  new ApiResponse<UserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> get(@PathVariable Long id) {
        UserDto response= service.getById(id);
        ApiResponse<UserDto> apiResponse =  new ApiResponse<UserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> update(@Valid @RequestBody UserUpdateRequest updateRequest,
                                                       @PathVariable Long id) {
        UserDto response= service.update(id,updateRequest);
        ApiResponse<UserDto> apiResponse =  new ApiResponse<UserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<ApiResponse<ProfileDto>> getProfile(@PathVariable Long id) {
        ProfileDto response= service.getUserProfile(id);
        ApiResponse<ProfileDto> apiResponse =  new ApiResponse<ProfileDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"profile"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/{id}/profile")
    @PreAuthorize("hasAuthority('update_profile')")
    public ResponseEntity<ApiResponse<ProfileDto>> updateProfile(@Valid @RequestBody ProfileRequest updateRequest,
                                                          @PathVariable Long id) {
        ProfileDto response= service.updateUserProfile(id,updateRequest);
        ApiResponse<ProfileDto> apiResponse =  new ApiResponse<ProfileDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"profile"}));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{id}/blocked-users")
    public ResponseEntity<ApiResponse<Boolean>> blockUser(@Valid @RequestBody BlockRequest request,
                                                                 @PathVariable Long id) {
        boolean response= service.blockUser(id,request);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20000));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}/blocked-users")
    public ResponseEntity<ApiResponse<Boolean>> unBlockUser(@Valid @RequestBody BlockRequest request,
                                                             @PathVariable Long id) {
        boolean response= service.unblockUser(id,request);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20000));
        return ResponseEntity.ok(apiResponse);
    }


}
