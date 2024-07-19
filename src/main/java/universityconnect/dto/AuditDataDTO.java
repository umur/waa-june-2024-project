package universityconnect.dto;
import lombok.Data;
import java.time.LocalDate;

@Data
public class AuditDataDTO {
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private String createdBy;
    private String updatedBy;

    public AuditDataDTO() {}

    public AuditDataDTO(LocalDate createdOn, LocalDate updatedOn, String createdBy, String updatedBy) {
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}

