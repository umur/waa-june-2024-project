package universityconnect.service;

import universityconnect.domain.DiscussionCategory;
import universityconnect.dto.DiscussionCategoryDTO;

import java.util.List;

public interface DiscussionCategoryService {
    DiscussionCategoryDTO createDiscussionCategory(DiscussionCategoryDTO discussionCategoryDTO);
    DiscussionCategoryDTO getDiscussionCategoryById(Long id);
    List<DiscussionCategoryDTO> getAllDiscussionCategory();
    DiscussionCategoryDTO updateDiscussionCategory(Long id, DiscussionCategoryDTO discussionCategoryDTO);
    void deleteDiscussionCategory(Long id);
    DiscussionCategory findById(Long id);
}
