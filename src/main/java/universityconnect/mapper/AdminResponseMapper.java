package universityconnect.mapper;

import org.mapstruct.Mapper;
import universityconnect.domain.response.AdminResponse;
import universityconnect.dto.AdminDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminResponseMapper {
    AdminResponse adminDTOToAdminResponse(AdminDTO adminDTO);

    List<AdminResponse> adminDTOsToAdminResponses(List<AdminDTO> adminDTO);

}
