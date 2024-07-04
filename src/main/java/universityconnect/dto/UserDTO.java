package universityconnect.dto;

import lombok.Data;
import universityconnect.domain.Role;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String address;
    private Role role;
    private int year;
    private String major;
    private String department;

    public UserDTO() {}

}

