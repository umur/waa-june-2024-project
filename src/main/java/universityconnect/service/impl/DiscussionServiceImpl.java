package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityconnect.domain.*;
import universityconnect.dto.DiscussionDTO;
import universityconnect.domain.DiscussionSpecifications;
import universityconnect.dto.DiscussionSearchResponse;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.DiscussionMapper;
import universityconnect.repository.DiscussionRepository;
import universityconnect.repository.DiscussionThreadRepository;
import universityconnect.repository.UserRepository;
import universityconnect.service.DiscussionCategoryService;
import universityconnect.service.DiscussionService;
import universityconnect.service.UserService;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DiscussionServiceImpl implements DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final DiscussionMapper mapper;
    private final DiscussionCategoryService discussionCategoryService;
    private final UserService userService;
    private final DiscussionThreadRepository discussionThreadRepository;
    private final UserRepository userRepository;
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
        discussion.setCreatedOn(LocalDateTime.now());
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
        List<Discussion> discussions = discussionRepository.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            throw new RuntimeException("User is not authenticated!");
        }
        if(!authentication.getAuthorities().toString().equals("ROLE_ADMIN")){
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow(()->new ResourceNotFoundException("User not found with email"));

//            discussions = discussions.stream().filter(discussion ->
//                user.getBlockedUserLists().stream()
//                        .map(Block::getBlockedUser)
//                        .map(User::getId)
//                        .noneMatch(blockedUserId -> blockedUserId.equals(discussion.getUser().getId()))
//            ).toList();
//
//            discussions = discussions.stream().filter(discussion ->
//                    user.getBlockedUserLists().stream()
//                            .map(Block::getBlockerUser)
//                            .map(User::getId)
//                            .noneMatch(blockerUserId -> blockerUserId.equals(discussion.getUser().getId()))
//            ).toList();
            discussions = getBlockedFilter(discussions,user);

        }

        return discussions.stream().map(mapper::discussionToDiscussionDTO).toList();
    }

    private List<Discussion> getBlockedFilter(List<Discussion> discussions,User user){
        discussions = discussions.stream().filter(discussion ->
                user.getBlockedUserLists().stream()
                        .map(Block::getBlockedUser)
                        .map(User::getId)
                        .noneMatch(blockedUserId -> blockedUserId.equals(discussion.getUser().getId()))
        ).toList();

        discussions = discussions.stream().filter(discussion ->
                user.getBlockedUserLists().stream()
                        .map(Block::getBlockerUser)
                        .map(User::getId)
                        .noneMatch(blockerUserId -> blockerUserId.equals(discussion.getUser().getId()))
        ).toList();

        return discussions;
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
        discussion.setUpdatedOn(LocalDateTime.now());
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

    @Override
    public DiscussionSearchResponse getDiscussionByCategory(int categoryId,Pageable pageable){
        Page<Discussion> discussions = discussionRepository.findByCategoryId(categoryId,pageable);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            throw new RuntimeException("User is not authenticated!");
        }
        if(!authentication.getAuthorities().toString().equals("ROLE_ADMIN")) {
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found with email"));

            List<Discussion> filterDiscussions = getBlockedFilter(discussions.getContent(),user);
            discussions = new PageImpl<>(filterDiscussions,pageable,discussions.getTotalElements());
        }

        List<DiscussionDTO> discussionDTOs = discussions.getContent().stream()
                .map(DiscussionMapper.INSTANCE::discussionToDiscussionDTO)
                .collect(Collectors.toList());

        DiscussionSearchResponse response = new DiscussionSearchResponse();
        response.setContent(discussionDTOs);
        response.setTotalPages(discussions.getTotalPages());
        response.setTotalElements(discussions.getTotalElements());
        response.setNumber(discussions.getNumber());
        response.setSize(discussions.getSize());
        response.setFirst(discussions.isFirst());
        response.setLast(discussions.isLast());
        return response;
    }


    public DiscussionSearchResponse searchDiscussions(DiscussionSearchCriteria criteria, Pageable pageable) {
        Specification<Discussion> spec = DiscussionSpecifications.withCriteria(criteria);
        Page<Discussion> discussions = discussionRepository.findAll(spec, pageable);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated()){
            throw new RuntimeException("User is not authenticated!");
        }
        if(!authentication.getAuthorities().toString().equals("ROLE_ADMIN")) {
            User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found with email"));

            List<Discussion> filterDiscussions = getBlockedFilter(discussions.getContent(),user);
            discussions = new PageImpl<>(filterDiscussions,pageable,discussions.getTotalElements());
        }

        List<DiscussionDTO> discussionDTOs = discussions.getContent().stream()
                .map(DiscussionMapper.INSTANCE::discussionToDiscussionDTO)
                .collect(Collectors.toList());

        DiscussionSearchResponse response = new DiscussionSearchResponse();
        response.setContent(discussionDTOs);
        response.setTotalPages(discussions.getTotalPages());
        response.setTotalElements(discussions.getTotalElements());
        response.setNumber(discussions.getNumber());
        response.setSize(discussions.getSize());
        response.setFirst(discussions.isFirst());
        response.setLast(discussions.isLast());

        return response;
    }


}
