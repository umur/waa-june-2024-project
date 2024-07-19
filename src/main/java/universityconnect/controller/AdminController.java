package universityconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.domain.response.AdminResponse;
import universityconnect.dto.AdminDTO;
import universityconnect.mapper.AdminResponseMapper;
import universityconnect.service.AdminService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminResponseMapper adminResponseMapper;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(adminResponseMapper.adminDTOsToAdminResponses(admins));

    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> getAdminById(@PathVariable Long id) {
        AdminDTO adminDTO = adminService.getAdminById(id);
        return ResponseEntity.ok(adminResponseMapper.adminDTOToAdminResponse(adminDTO));
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<AdminResponse> updateAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDetails) {
//        AdminDTO  adminDTO =  adminService.updateAdmin(id, adminDetails);
//        return ResponseEntity.ok(adminResponseMapper.adminDTOToAdminResponse(adminDTO));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
//        adminService.deleteAdmin(id);
//        return ResponseEntity.noContent().build();
//    }
}
