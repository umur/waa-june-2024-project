package universityconnect.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student extends User {
    private int year;
    private String major;

    @Override
    public Role getRole() {
        return Role.STUDENT;
    }
}
