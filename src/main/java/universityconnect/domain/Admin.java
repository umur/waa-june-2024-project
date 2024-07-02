package universityconnect.domain;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Admin extends User {
    private String department;

    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<Survey> surveys;

    public Admin(){

    }

    public Admin(String department) {
        this.department = department;
    }

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
