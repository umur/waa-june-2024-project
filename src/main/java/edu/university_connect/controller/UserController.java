package edu.university_connect.controller;

import edu.university_connect.config.ContextUser;
import edu.university_connect.model.contract.dto.*;
import edu.university_connect.model.contract.request.RequestUtils;
import edu.university_connect.model.contract.request.profile.ProfileRequest;
import edu.university_connect.model.contract.request.user.BlockRequest;
import edu.university_connect.model.contract.request.user.UserCreateRequest;
import edu.university_connect.model.contract.request.user.UserUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.post.PostService;
import edu.university_connect.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService service;
    private final PostService postService;
    private final ContextUser contextUser;
    private final MessagingService messagingService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('view_user_list')")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAll() {
        List<UserDto> response= service.getAll();
        ApiResponse<List<UserDto>> apiResponse =  new ApiResponse<List<UserDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/posts")
    @PreAuthorize("hasAuthority('view_user_list')")
    public ResponseEntity<ApiResponse<Page<PostDto>>> getSelfPosts(Pageable pageable) {
        String message = messagingService.getResponseMessage(AppStatusCode.S20001, "post");
        return ResponseEntity.ok(ApiResponse.of(
                message,
                postService.getByUser(
                        contextUser.getLoginUser().getUser().getId(),
                        RequestUtils.extractPagination(pageable)
                )
        ));
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('view_user_list')")
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
    @PreAuthorize("hasAuthority('create_user')")
    public ResponseEntity<ApiResponse<UserDto>> create(@Valid @RequestBody UserCreateRequest createRequest) {
        UserDto response= service.create(createRequest);
        ApiResponse<UserDto> apiResponse =  new ApiResponse<UserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('view_user')")
    public ResponseEntity<ApiResponse<UserDto>> get(@PathVariable Long id) {
        UserDto response= service.getById(id);
        ApiResponse<UserDto> apiResponse =  new ApiResponse<UserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('modify_user')")
    public ResponseEntity<ApiResponse<UserDto>> update(@Valid @RequestBody UserUpdateRequest updateRequest,
                                                       @PathVariable Long id) {
        UserDto response= service.update(id,updateRequest);
        ApiResponse<UserDto> apiResponse =  new ApiResponse<UserDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('delete_user')")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"user"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}/profile")
    @PreAuthorize("hasAuthority('view_profile')")
    public ResponseEntity<ApiResponse<ProfileDto>> getProfile(@PathVariable Long id) {
        ProfileDto response= service.getUserProfile(id);
        ApiResponse<ProfileDto> apiResponse =  new ApiResponse<ProfileDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"profile"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/{id}/profile")
    @PreAuthorize("hasAuthority('modify_profile') && @contextUser.getLoginUser().getId() == #id")
    public ResponseEntity<ApiResponse<ProfileDto>> updateProfile(@Valid @RequestBody ProfileRequest updateRequest,
                                                          @PathVariable Long id) {
        ProfileDto response= service.updateUserProfile(id,updateRequest);
        ApiResponse<ProfileDto> apiResponse =  new ApiResponse<ProfileDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"profile"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<SearchDto>>> getAllByUserName(@RequestParam String username) {
        List<SearchDto> searchDtos = service.getAllStudentsByName(username);
        ApiResponse<List<SearchDto>> apiResponse = new ApiResponse<List<SearchDto>>();
        apiResponse.setResponseData(searchDtos);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"search"}));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/{id}/blocked-users")
    @PreAuthorize("hasAuthority('modify_profile')")
    public ResponseEntity<ApiResponse<Boolean>> blockUser(@Valid @RequestBody BlockRequest request,
                                                                 @PathVariable Long id) {
        boolean response= service.blockUser(id,request);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20000));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}/blocked-users")
    @PreAuthorize("hasAuthority('modify_profile')")
    public ResponseEntity<ApiResponse<Boolean>> unBlockUser(@Valid @RequestBody BlockRequest request,
                                                             @PathVariable Long id) {
        boolean response= service.unblockUser(id,request);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20000));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/{id}/blocked-users")
    @PreAuthorize("hasAuthority('view_user') && @contextUser.getLoginUser().getId() == #id")
    public ResponseEntity<ApiResponse<Page<UserDto>>> getBlockedUsers(@PathVariable Long id,Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<UserDto> response= service.getBlockedUsers(id,pageable);
        ApiResponse<Page<UserDto>> apiResponse =  new ApiResponse<Page<UserDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20000));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}/resources")
    @PreAuthorize("hasAuthority('view_resource') && @contextUser.getLoginUser().getId() == #id")
    public ResponseEntity<ApiResponse<Page<ResourceDto>>> fetchUserResources(@PathVariable Long id, Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<ResourceDto> response= service.getUserResources(id,pageable);
        ApiResponse<Page<ResourceDto>> apiResponse =  new ApiResponse<Page<ResourceDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20000));
        return ResponseEntity.ok(apiResponse);
    }
}
