package universityconnect.service;

import universityconnect.dto.ReportDTO;

import java.util.List;

public interface ReportService {

    ReportDTO createReport(ReportDTO reportDTO);

    List<ReportDTO> getAllReports();

    ReportDTO getReportById(long id);

    ReportDTO updateReport(long id,ReportDTO reportDTO);

    void deleteReport(long id);
}
