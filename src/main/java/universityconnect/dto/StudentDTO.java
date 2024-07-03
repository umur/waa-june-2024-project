package universityconnect.dto;

import lombok.Data;

@Data
public class StudentDTO extends UserDTO {
    private int year;
    private String major;

    public StudentDTO() {}

}

