package universityconnect.dto;

import lombok.Data;
import universityconnect.domain.Role;

import java.util.List;

@Data
public class StudentDTO extends UserDTO {
    private int year;
    private String major;

    public StudentDTO() {}

    public StudentDTO(Long id, String username, String password, String email, String address, AuditDataDTO auditData, List<Long> blockIds, List<Long> reportedListIds, List<Long> discussionIds, List<Long> resourceIds, List<Long> eventIds, Role role, int year, String major) {
        super(id, username, password, email, address, auditData, blockIds, reportedListIds, discussionIds, resourceIds, eventIds, role);
        this.year = year;
        this.major = major;
    }
}

