package universityconnect.dto;

import lombok.Data;
import universityconnect.domain.Role;


@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String address;
    private Role role;
    public UserResponse() {}
}
