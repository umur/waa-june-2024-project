package edu.university_connect.model.contract.request.report;

import lombok.Data;

@Data
public class ReportUpdateRequest {
    private boolean actionTaken;
    private String actionComment;
}
