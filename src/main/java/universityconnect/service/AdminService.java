package universityconnect.service;

import universityconnect.dto.AdminDTO;

import java.util.List;

public interface AdminService {

    List<AdminDTO> getAllAdmins();

    AdminDTO getAdminById(Long id);

    AdminDTO updateAdmin(Long id, AdminDTO admin);

    void deleteAdmin(Long id);
}
