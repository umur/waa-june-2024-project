package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Admin;
import universityconnect.dto.AdminDTO;

@Mapper
public interface AdminMapper {
    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    @Mapping(target = "surveyIds", source = "surveys.id")
    AdminDTO adminToAdminDTO(Admin admin);

    @Mapping(target = "surveys", ignore = true)
    Admin adminDTOToAdmin(AdminDTO adminDTO);
}

