package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Admin extends User {
    private String department;

    public Admin(){}

    @Override
    public Role getRole() {
        return Role.ADMIN;
    }
}
