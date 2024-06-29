package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Report;
import universityconnect.dto.ReportDTO;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    ReportDTO reportToReportDTO(Report report);

    Report reportDTOToReport(ReportDTO reportDTO);
}

