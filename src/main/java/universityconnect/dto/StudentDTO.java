package universityconnect.dto;

import lombok.Data;
import universityconnect.domain.Role;

import java.util.List;

@Data
public class StudentDTO extends UserDTO {
    private int year;
    private String major;

    public StudentDTO() {}


}

