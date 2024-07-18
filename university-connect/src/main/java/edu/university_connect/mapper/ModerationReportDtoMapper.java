package edu.university_connect.mapper;

import edu.university_connect.domain.entity.ModerationReport;
import edu.university_connect.model.contract.dto.ModerationReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModerationReportDtoMapper {
    ModerationReportDtoMapper MAPPER =
            Mappers.getMapper(ModerationReportDtoMapper.class);
    ModerationReportDto entityToDto(ModerationReport moderationReport);
    ModerationReport dtoToEntity(ModerationReportDto moderationReportDto);
}
