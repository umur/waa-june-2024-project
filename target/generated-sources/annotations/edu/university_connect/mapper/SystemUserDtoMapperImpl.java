package edu.university_connect.mapper;

import edu.university_connect.model.contract.dto.SystemUserDto;
import edu.university_connect.model.contract.request.user.SystemUserCreateRequest;
import edu.university_connect.model.contract.request.user.SystemUserUpdateRequest;
import edu.university_connect.model.entity.SystemUser;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-28T17:44:06-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class SystemUserDtoMapperImpl implements SystemUserDtoMapper {

    @Override
    public SystemUser dtoToEntity(SystemUserCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        SystemUser systemUser = new SystemUser();

        systemUser.setEnabled( request.isEnabled() );
        systemUser.setUsername( request.getUsername() );
        systemUser.setPassword( request.getPassword() );
        systemUser.setEmail( request.getEmail() );

        return systemUser;
    }

    @Override
    public SystemUser dtoToEntity(SystemUserUpdateRequest request) {
        if ( request == null ) {
            return null;
        }

        SystemUser systemUser = new SystemUser();

        systemUser.setEnabled( request.isEnabled() );
        systemUser.setUsername( request.getUsername() );
        systemUser.setEmail( request.getEmail() );

        return systemUser;
    }

    @Override
    public SystemUserDto entityToDto(SystemUser user) {
        if ( user == null ) {
            return null;
        }

        SystemUserDto systemUserDto = new SystemUserDto();

        systemUserDto.setId( user.getId() );
        systemUserDto.setUsername( user.getUsername() );
        systemUserDto.setEmail( user.getEmail() );
        systemUserDto.setEnabled( user.isEnabled() );
        systemUserDto.setCreatedAt( user.getCreatedAt() );
        systemUserDto.setLastModified( user.getLastModified() );

        return systemUserDto;
    }
}
