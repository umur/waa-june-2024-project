package universityconnect.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student extends User {
    private int year;
    private String major;

    @ManyToMany
    @JoinTable(
            name = "student_friends",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Student> friends;

    @Override
    public Role getRole() {
        return Role.STUDENT;
    }
}
