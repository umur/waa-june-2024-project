package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Admin;
import universityconnect.domain.Survey;
import universityconnect.dto.AdminDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    @Mapping(target = "surveyIds", source = "surveys")
    AdminDTO adminToAdminDTO(Admin admin);

    @Mapping(target = "surveys", ignore = true)
    Admin adminDTOToAdmin(AdminDTO adminDTO);

    default List<Long> mapSurveyIds(List<Survey> surveys) {
        return surveys.stream()
                .map(Survey::getId)
                .collect(Collectors.toList());
    }
}


