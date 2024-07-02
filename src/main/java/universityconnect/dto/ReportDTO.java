package universityconnect.dto;

import lombok.Data;

@Data
public class ReportDTO {
    private Long id;
    private Long reportedUserId;
    private String reportedUserName;
    private Long reporterUserId;
    private String reporterUserName;

    public ReportDTO() {}

    public ReportDTO(Long id, Long reportedUserId,Long reporterUserId,String reportedUserName,String reporterUserName) {
        this.id = id;
        this.reportedUserId = reportedUserId;
        this.reporterUserId = reporterUserId;
        this.reportedUserName = reportedUserName;
        this.reporterUserName = reporterUserName;
    }
}

