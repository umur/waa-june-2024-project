package universityconnect.dto;

import lombok.Data;
import universityconnect.domain.Role;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String address;
    private AuditDataDTO auditData;
    private List<Long> blockIds;
    private List<Long> discussionIds;
    private List<Long> resourceIds;
    private List<Long> eventIds;
    private Role role;


    public UserDTO() {}

    public UserDTO(Long id, String username, String password, String email, String address, AuditDataDTO auditData, List<Long> blockIds, List<Long> discussionIds, List<Long> resourceIds, List<Long> eventIds, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.auditData = auditData;
        this.blockIds = blockIds;
        this.discussionIds = discussionIds;
        this.resourceIds = resourceIds;
        this.eventIds = eventIds;
        this.role = role;

    }
}

