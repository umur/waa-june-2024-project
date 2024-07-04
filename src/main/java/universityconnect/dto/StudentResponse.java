package universityconnect.dto;

import lombok.Data;

@Data
public class StudentResponse extends UserResponse{

    private int year;
    private String major;
    public StudentResponse() {}
}
