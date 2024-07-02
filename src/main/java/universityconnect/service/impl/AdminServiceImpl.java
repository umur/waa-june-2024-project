package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.Admin;
import universityconnect.dto.AdminDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.AdminMapper;
import universityconnect.repository.AdminRepository;
import universityconnect.repository.SurveyRepository;
import universityconnect.service.AdminService;
import universityconnect.util.PasswordEncoderUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    private final SurveyRepository surveyRepository;

    private final AdminMapper adminMapper;

    private final PasswordEncoderUtil passwordEncoderUtil;


    @Override
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(adminMapper::adminToAdminDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin Not Found with ID: " + id));
        return adminMapper.adminToAdminDTO(admin);
    }

    @Override
    public AdminDTO updateAdmin(Long id, AdminDTO admin) {
        Admin existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin Not Found with ID: " + id));
        existingAdmin.setDepartment(admin.getDepartment());
        existingAdmin.setSurveys(surveyRepository.findAllById(admin.getSurveyIds()));

        Admin ad = adminRepository.save(existingAdmin);
        return adminMapper.adminToAdminDTO(ad);

    }

    @Override
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new ResourceNotFoundException("Admin with id " + id + " does not exist");
        }

        adminRepository.deleteById(id);
    }
}
