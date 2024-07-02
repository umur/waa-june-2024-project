package edu.university_connect.controller;
import edu.university_connect.model.contract.dto.CategoryDto;
import edu.university_connect.model.contract.request.category.CategoryCreateRequest;
import edu.university_connect.model.contract.request.category.CategoryUpdateRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/categories")
@CrossOrigin
@Slf4j
public class CategoryController {
    private final CategoryService service;

    private final MessagingService messagingService;

    public CategoryController(CategoryService service, MessagingService messagingService) {
        this.service = service;
        this.messagingService = messagingService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAll() {
        List<CategoryDto> response= service.getAll();
        ApiResponse<List<CategoryDto>> apiResponse =  new ApiResponse<List<CategoryDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"category"}));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<Page<CategoryDto>>> getPage(Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber()>0? pageableReq.getPageNumber()-1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort());
        Page<CategoryDto> response= service.getPage(pageable);
        ApiResponse<Page<CategoryDto>> apiResponse =  new ApiResponse<Page<CategoryDto>>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001,new String[]{"category"}));
        return ResponseEntity.ok(apiResponse);

    }
    @PostMapping("")
    public ResponseEntity<ApiResponse<CategoryDto>> create(@Valid @RequestBody CategoryCreateRequest createRequest) {
        CategoryDto response= service.create(createRequest);
        ApiResponse<CategoryDto> apiResponse =  new ApiResponse<CategoryDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20002,new String[]{"category"}));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> get(@PathVariable Long id) {
        CategoryDto response= service.getById(id);
        ApiResponse<CategoryDto> apiResponse =  new ApiResponse<CategoryDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20003,new String[]{"category"}));
        return ResponseEntity.ok(apiResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> update(@Valid @RequestBody CategoryUpdateRequest updateRequest,
                                                         @PathVariable Long id) {
        CategoryDto response= service.update(id,updateRequest);
        ApiResponse<CategoryDto> apiResponse =  new ApiResponse<CategoryDto>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20004,new String[]{"category"}));
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response= service.delete(id);
        ApiResponse<Boolean> apiResponse =  new ApiResponse<Boolean>();
        apiResponse.setResponseData(response);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20005,new String[]{"category"}));
        return ResponseEntity.ok(apiResponse);
    }

}
