package edu.university_connect.service.category;
import edu.university_connect.domain.entity.discussionthread.Category;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.CategoryDtoMapper;
import edu.university_connect.model.contract.dto.CategoryDto;
import edu.university_connect.model.contract.request.category.CategoryCreateRequest;
import edu.university_connect.model.contract.request.category.CategoryUpdateRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@RequiredArgsConstructor
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public Page<CategoryDto> getPage(Pageable pageable) {
        Page<Category> actionPage = repository.findAll(pageable);
        return actionPage.map(CategoryDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public List<CategoryDto> getAll() {
        return repository.findAll()
                .stream()
                .map(CategoryDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        Optional<Category> categoryOpt= getCategoryById(id);
        if(categoryOpt.isPresent()){
            return CategoryDtoMapper.MAPPER.entityToDto(categoryOpt.get());
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "category","id = "+id.toString());
        }
    }

    @Override
    public CategoryDto create(CategoryCreateRequest createRequest) {
        Optional<Category> categoryWithTitle=repository.findByTitleIgnoreCase(createRequest.getTitle());
        if(categoryWithTitle.isPresent()){
            throw ServiceException.of(AppStatusCode.E40006,"category","code="+createRequest.getTitle());
        }
        Category category= CategoryDtoMapper.MAPPER.dtoToEntity(createRequest);
        Category save=repository.save(category);
        return CategoryDtoMapper.MAPPER.entityToDto(save);
    }

    @Override
    public CategoryDto update(Long id, CategoryUpdateRequest updateRequest) {
        Optional<Category> opt= getCategoryById(id);
        if(opt.isPresent()){
            Category category=opt.get();
            category.setTitle(updateRequest.getTitle());
            Category savedCategory=repository.save(category);
            return CategoryDtoMapper.MAPPER.entityToDto(savedCategory);
        }
        else {
            throw ServiceException.of(AppStatusCode.E40000, "role");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(Objects.nonNull(getById(id))){
            repository.deleteById(id);
        }
        return true;
    }
    private Optional<Category> getCategoryById(Long id){
        return repository.findById(id);
    }
    private Optional<Category> getCategoryByTitle(String title){
        return repository.findByTitleIgnoreCase(title);
    }
}
