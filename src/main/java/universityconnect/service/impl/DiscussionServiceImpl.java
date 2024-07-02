package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.Discussion;
import universityconnect.domain.DiscussionCategory;
import universityconnect.domain.DiscussionThread;
import universityconnect.domain.User;
import universityconnect.dto.DiscussionDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.DiscussionMapper;
import universityconnect.repository.DiscussionRepository;
import universityconnect.repository.DiscussionThreadRepository;
import universityconnect.service.DiscussionCategoryService;
import universityconnect.service.DiscussionService;
import universityconnect.service.UserService;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DiscussionServiceImpl implements DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final DiscussionMapper mapper;
    private final DiscussionCategoryService discussionCategoryService;
    private final UserService userService;
    private final DiscussionThreadRepository discussionThreadRepository;
    @Override
    public DiscussionDTO createDiscussion(DiscussionDTO discussionDTO) {
        Discussion discussion = mapper.discussionDTOToDiscussion(discussionDTO);
        DiscussionCategory category = discussionCategoryService.findById(discussionDTO.getCategoryId());
        discussion.setCategory(category);
        List<DiscussionThread> discussionThreads = new ArrayList<>();
        discussionDTO.getDiscussionThreadIds().forEach(thread->{
            discussionThreads.add(discussionThreadRepository.findById(thread).orElseThrow(()->new ResourceNotFoundException("Discussion Thread not found with id: "+thread)));
        });
        discussion.setDiscussionThreads(discussionThreads);
        discussion.setUser(userService.findById(discussionDTO.getUserId()));
        Discussion savedDiscussion = discussionRepository.save(discussion);
        return mapper.discussionToDiscussionDTO(savedDiscussion);
    }

    @Override
    public DiscussionDTO getDiscussionById(Long id) {
        Discussion discussion = discussionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Discussion not found with id: "+id));
        return mapper.discussionToDiscussionDTO(discussion);
    }

    @Override
    public List<DiscussionDTO> getAllDiscussion() {
        return discussionRepository.findAll().stream().map(mapper::discussionToDiscussionDTO).toList();
    }

    @Override
    public DiscussionDTO updateDiscussion(Long id, DiscussionDTO discussionDTO) {
        Discussion discussion = discussionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Discussion not found with id: "+id));
        discussion.setTopic(discussionDTO.getTopic());
        discussion.setDescription(discussionDTO.getDescription());
        DiscussionCategory category = discussionCategoryService.findById(discussionDTO.getCategoryId());
        discussion.setCategory(category);
        User user = userService.findById(discussionDTO.getUserId());
        discussion.setUser(user);
        Discussion savedDiscussion = discussionRepository.save(discussion);
        return mapper.discussionToDiscussionDTO(savedDiscussion);
    }

    @Override
    public void deleteDiscussion(Long id) {
        if(discussionRepository.existsById(id)){
            discussionRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Discussion not found with id: "+id);
        }
    }

    @Override
    public Discussion findById(Long id) {
        return discussionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Discussion not found with id: "+id));
    }
}
