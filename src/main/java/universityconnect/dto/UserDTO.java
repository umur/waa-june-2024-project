package universityconnect.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import universityconnect.domain.Role;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String address;

    private List<Long> blockIds;
    private List<Long> reportedListIds;
    private List<Long> discussionIds;
    private List<Long> resourceIds;
    private List<Long> eventIds;
    private Role role;

    public UserDTO() {}

}

