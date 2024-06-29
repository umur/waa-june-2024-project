package edu.university_connect.mapper;

import edu.university_connect.model.contract.dto.SystemRoleDto;
import edu.university_connect.model.contract.request.role.SystemRoleCreateRequest;
import edu.university_connect.model.contract.request.role.SystemRoleUpdateRequest;
import edu.university_connect.model.entity.SystemRole;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-28T17:44:06-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class SystemRoleDtoMapperImpl implements SystemRoleDtoMapper {

    @Override
    public SystemRole dtoToEntity(SystemRoleCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        SystemRole systemRole = new SystemRole();

        systemRole.setName( request.getName() );
        systemRole.setCode( request.getCode() );
        systemRole.setDescription( request.getDescription() );
        Set<String> set = request.getActions();
        if ( set != null ) {
            systemRole.setActions( new LinkedHashSet<String>( set ) );
        }

        return systemRole;
    }

    @Override
    public SystemRole dtoToEntity(SystemRoleUpdateRequest request) {
        if ( request == null ) {
            return null;
        }

        SystemRole systemRole = new SystemRole();

        systemRole.setName( request.getName() );
        systemRole.setCode( request.getCode() );
        systemRole.setDescription( request.getDescription() );
        Set<String> set = request.getActions();
        if ( set != null ) {
            systemRole.setActions( new LinkedHashSet<String>( set ) );
        }

        return systemRole;
    }

    @Override
    public SystemRoleDto entityToDto(SystemRole role) {
        if ( role == null ) {
            return null;
        }

        SystemRoleDto systemRoleDto = new SystemRoleDto();

        systemRoleDto.setId( role.getId() );
        systemRoleDto.setName( role.getName() );
        systemRoleDto.setCode( role.getCode() );
        Set<String> set = role.getActions();
        if ( set != null ) {
            systemRoleDto.setActions( new LinkedHashSet<String>( set ) );
        }
        systemRoleDto.setDescription( role.getDescription() );
        systemRoleDto.setCreatedAt( role.getCreatedAt() );
        systemRoleDto.setLastModified( role.getLastModified() );
        systemRoleDto.setEnabled( role.isEnabled() );

        return systemRoleDto;
    }
}
