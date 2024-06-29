package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Admin extends User {
    private String department;
    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<Survey> surveys;

    public Admin(){}

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
