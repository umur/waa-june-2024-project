package universityconnect.dto;

import lombok.Data;
import universityconnect.domain.Role;
import universityconnect.domain.Student;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String address;
    private AuditDataDTO auditData;
    private Role role;
    private int year;
    private String major;
    private String department;

    public UserDTO() {}

}

