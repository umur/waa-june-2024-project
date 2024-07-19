package universityconnect.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Student extends User {
    private int year;
    private String major;

    public Student() {

    }

    @Override
    public Role getRole() {
        return Role.STUDENT;
    }
}
