package universityconnect.dto;

import lombok.Data;

@Data
public class ReportDTO {
    private Long id;
    private Long reportedId;

    public ReportDTO() {}

    public ReportDTO(Long id, Long reportedId) {
        this.id = id;
        this.reportedId = reportedId;
    }
}

