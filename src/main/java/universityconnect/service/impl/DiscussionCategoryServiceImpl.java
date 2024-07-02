package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.DiscussionCategory;
import universityconnect.dto.DiscussionCategoryDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.DiscussionCategoryMapper;
import universityconnect.repository.DiscussionCategoryRepository;
import universityconnect.service.DiscussionCategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscussionCategoryServiceImpl implements DiscussionCategoryService {
    private final DiscussionCategoryRepository discussionCategoryRepository;
    private final DiscussionCategoryMapper discussionCategoryMapper;

    @Override
    public DiscussionCategoryDTO createDiscussionCategory(DiscussionCategoryDTO discussionCategoryDTO) {
        DiscussionCategory category = discussionCategoryMapper.discussionCategoryDTOToDiscussionCategory(discussionCategoryDTO);
        DiscussionCategory saveCategory = discussionCategoryRepository.save(category);
        return discussionCategoryMapper.discussionCategoryToDiscussionCategoryDTO(saveCategory);
    }

    @Override
    public DiscussionCategoryDTO getDiscussionCategoryById(Long id) {
        DiscussionCategory category = discussionCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Discussion Category not found with id: "+id));
        return discussionCategoryMapper.discussionCategoryToDiscussionCategoryDTO(category);
    }

    @Override
    public List<DiscussionCategoryDTO> getAllDiscussionCategory() {
        return discussionCategoryRepository.findAll().stream().map(discussionCategoryMapper::discussionCategoryToDiscussionCategoryDTO).toList();
    }

    @Override
    public DiscussionCategoryDTO updateDiscussionCategory(Long id, DiscussionCategoryDTO discussionCategoryDTO) {
        DiscussionCategory category = discussionCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Discussion Category not found with id: "+id));
        category.setDescription(discussionCategoryDTO.getDescription());
        category.setName((discussionCategoryDTO.getName()));
        discussionCategoryRepository.save(category);
        return discussionCategoryMapper.discussionCategoryToDiscussionCategoryDTO(category);
    }

    @Override
    public void deleteDiscussionCategory(Long id) {
        if(discussionCategoryRepository.existsById(id)) {
            discussionCategoryRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("Discussion Category not found with id: "+id);
        }
    }

    @Override
    public DiscussionCategory findById(Long id) {
        return discussionCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Discussion Category not found with id: "+id));
    }
}
