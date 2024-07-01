package universityconnect.dto;

import lombok.Data;
import universityconnect.domain.Report;
import universityconnect.domain.Role;

import java.util.List;

@Data
public class AdminDTO extends UserDTO {
    private String department;
    private List<Long> surveyIds;

    public AdminDTO() {}

    public AdminDTO(Long id, String username, String password, String email, String address, AuditDataDTO auditData, List<Long> blockIds,List<Long> discussionIds, List<Long> resourceIds, List<Long> eventIds, Role role, String department, List<Long> surveyIds) {
        super(id, username, password, email, address, auditData, blockIds,discussionIds, resourceIds, eventIds, role);
        this.department = department;
        this.surveyIds = surveyIds;
    }
}

