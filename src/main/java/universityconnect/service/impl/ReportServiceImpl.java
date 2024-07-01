package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.Report;
import universityconnect.domain.User;
import universityconnect.dto.ReportDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.ReportMapper;
import universityconnect.repository.ReportRepository;
import universityconnect.repository.UserRepository;
import universityconnect.service.ReportService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    private final ReportMapper reportMapper;
    @Override
    public ReportDTO createReport(ReportDTO reportDTO) {
        Report report = reportMapper.reportDTOToReport(reportDTO);
        Report createdReport = reportRepository.save(report);
        return reportMapper.reportToReportDTO(createdReport);
    }

    @Override
    public List<ReportDTO> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .map(reportMapper::reportToReportDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReportDTO getReportById(long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Report ID is not found, id:  " + id));
        return reportMapper.reportToReportDTO(report);
    }

    @Override
    public ReportDTO updateReport(long id, ReportDTO reportDTO) {
        Report existingReport = reportRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Report ID is not found, id:  " + id));

       if(reportDTO.getReportedUserId() != null){
           User user = userRepository.findById(reportDTO.getReportedUserId())
                   .orElseThrow(()->new ResourceNotFoundException("Reported User ID is not found, id: " + id));
           existingReport.setReportedUser(user);
       }

        if(reportDTO.getReporterUserId() != null){
            User user = userRepository.findById(reportDTO.getReporterUserId())
                    .orElseThrow(()->new ResourceNotFoundException("Reporting User ID is not found, id: " + id));
            existingReport.setReportingUser(user);
        }
        Report report = reportRepository.save(existingReport);
        return reportMapper.reportToReportDTO(report);
    }

    @Override
    public void deleteReport(long id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Report ID is not found, id:  " + id));
        reportRepository.delete(report);
    }
}