package universityconnect.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
public class AuditData {

    private LocalDate createdOn;
    private LocalDate updatedOn;
    private String createdBy;
    private String updatedBy;
}
