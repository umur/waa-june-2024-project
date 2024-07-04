package edu.university_connect.mapper;

import edu.university_connect.domain.entity.Action;
import edu.university_connect.domain.entity.discussionthread.Category;
import edu.university_connect.model.contract.dto.ActionDto;
import edu.university_connect.model.contract.dto.CategoryDto;
import edu.university_connect.model.contract.request.action.ActionCreateRequest;
import edu.university_connect.model.contract.request.action.ActionUpdateRequest;
import edu.university_connect.model.contract.request.category.CategoryCreateRequest;
import edu.university_connect.model.contract.request.category.CategoryUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoMapper {
    CategoryDtoMapper MAPPER =
            Mappers.getMapper(CategoryDtoMapper.class);

    Category dtoToEntity(CategoryCreateRequest request);
    Action dtoToEntity(CategoryUpdateRequest request);
    CategoryDto entityToDto(Category action);
}
