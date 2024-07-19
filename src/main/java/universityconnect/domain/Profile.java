package universityconnect.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> achievements;

    @ElementCollection
    private List<String> interests;

    @ElementCollection
    private List<String> activities;

    @OneToOne
    private User user;

    public Profile() {}
}
