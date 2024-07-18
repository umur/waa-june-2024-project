package edu.university_connect.service.category;
import edu.university_connect.model.contract.dto.CategoryDto;
import edu.university_connect.model.contract.request.category.CategoryCreateRequest;
import edu.university_connect.model.contract.request.category.CategoryUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoryService {
    Page<CategoryDto> getPage(Pageable pageable);
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    CategoryDto create(CategoryCreateRequest createRequest);
    CategoryDto update(Long id, CategoryUpdateRequest updateRequest);
    boolean delete(Long id);

}
