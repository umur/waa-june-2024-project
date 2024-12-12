package edu.university_connect.service.report;

import edu.university_connect.model.contract.dto.ModerationReportDto;
import edu.university_connect.model.contract.request.report.ModerationReportRequest;
import edu.university_connect.model.contract.request.report.ReportUpdateRequest;

import java.util.List;

public interface ModerationReportService {
    public ModerationReportDto createReport(ModerationReportRequest moderationReportRequest);

    public List<ModerationReportDto> getAll();

    public ModerationReportDto update(Long id, ReportUpdateRequest reportUpdateRequest);

    public boolean delete(Long id);
}
