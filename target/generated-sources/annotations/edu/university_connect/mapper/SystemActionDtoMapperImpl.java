package edu.university_connect.mapper;

import edu.university_connect.model.contract.dto.SystemActionDto;
import edu.university_connect.model.contract.request.action.SystemActionCreateRequest;
import edu.university_connect.model.contract.request.action.SystemActionUpdateRequest;
import edu.university_connect.model.entity.SystemAction;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-29T06:42:22-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class SystemActionDtoMapperImpl implements SystemActionDtoMapper {

    @Override
    public SystemAction dtoToEntity(SystemActionCreateRequest request) {
        if ( request == null ) {
            return null;
        }

        SystemAction systemAction = new SystemAction();

        systemAction.setName( request.getName() );
        systemAction.setCode( request.getCode() );
        systemAction.setDescription( request.getDescription() );

        return systemAction;
    }

    @Override
    public SystemAction dtoToEntity(SystemActionUpdateRequest request) {
        if ( request == null ) {
            return null;
        }

        SystemAction systemAction = new SystemAction();

        systemAction.setName( request.getName() );
        systemAction.setCode( request.getCode() );
        systemAction.setDescription( request.getDescription() );

        return systemAction;
    }

    @Override
    public SystemActionDto entityToDto(SystemAction action) {
        if ( action == null ) {
            return null;
        }

        SystemActionDto systemActionDto = new SystemActionDto();

        systemActionDto.setId( action.getId() );
        systemActionDto.setName( action.getName() );
        systemActionDto.setCode( action.getCode() );
        systemActionDto.setDescription( action.getDescription() );
        systemActionDto.setCreatedAt( action.getCreatedAt() );
        systemActionDto.setLastModifiedAt( action.getLastModifiedAt() );
        systemActionDto.setEnabled( action.isEnabled() );

        return systemActionDto;
    }
}
