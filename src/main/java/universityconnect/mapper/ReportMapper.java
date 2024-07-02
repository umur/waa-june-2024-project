package universityconnect.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import universityconnect.domain.Report;
import universityconnect.dto.ReportDTO;
import universityconnect.repository.UserRepository;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    ReportDTO reportToReportDTO(Report report);

    Report reportDTOToReport(ReportDTO reportDTO);

    @AfterMapping
    default void setUserFields(@MappingTarget ReportDTO reportDTO, Report report) {
        if (report.getReportedUser() != null) {
            reportDTO.setReportedUserId(report.getReportedUser().getId());
            reportDTO.setReportedUserName(report.getReportedUser().getUsername());
        }
        if (report.getReporterUser() != null) {
            reportDTO.setReporterUserId(report.getReporterUser().getId());
            reportDTO.setReporterUserName(report.getReporterUser().getUsername());
        }
    }

    @BeforeMapping
    default void setUserEntities(@MappingTarget Report report, ReportDTO reportDTO, UserRepository userRepository) {
        if (reportDTO.getReportedUserId() != null) {
            report.setReportedUser(userRepository.findById(reportDTO.getReportedUserId()).orElse(null));
        }
        if (reportDTO.getReporterUserId() != null) {
            report.setReporterUser(userRepository.findById(reportDTO.getReporterUserId()).orElse(null));
        }
    }
}
