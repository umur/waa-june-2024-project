package universityconnect.dto;

import lombok.Data;

@Data
public class ReportDTO {
    private Long id;
    private Long reportedUserId;
    private Long reporterUserId;

    public ReportDTO() {}

    public ReportDTO(Long id, Long reportedUserId,Long reporterUserId) {
        this.id = id;
        this.reportedUserId = reportedUserId;
        this.reporterUserId = reporterUserId;
    }
}

