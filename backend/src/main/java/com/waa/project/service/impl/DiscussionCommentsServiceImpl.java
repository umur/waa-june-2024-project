package com.waa.project.service.impl;

import com.waa.project.dto.DiscussionCommentsDto;
import com.waa.project.entity.Discussion;
import com.waa.project.entity.DiscussionComments;
import com.waa.project.exception.ResourceNotFoundException;
import com.waa.project.repository.DiscussionCommentsRepository;
import com.waa.project.repository.DiscussionRepository;
import com.waa.project.security.contract.AuthUserResponse;
import com.waa.project.service.DiscussionCommentsService;
import com.waa.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiscussionCommentsServiceImpl implements DiscussionCommentsService {

    @Autowired
    private DiscussionCommentsRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DiscussionRepository discusRepository;

    @Override
    public Page<DiscussionCommentsDto> getCommentsByDiscussionId(Long discussionId, Pageable pageable) {
        return repository.findByDiscussionId(discussionId, pageable)
                         .map(data -> mapper.map(data, DiscussionCommentsDto.class));
    }

    @Override
    public DiscussionCommentsDto createDiscussionComments(DiscussionCommentsDto commentsDto, User user) {
        DiscussionComments requestData = mapper.map(commentsDto, DiscussionComments.class);
        requestData.setStudent(getUserId(user));

        Discussion discussion = discusRepository.findById(commentsDto.getDiscussionId())
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Cannot Searchable for that Discussion"));
        requestData.setDiscussion(discussion);

        DiscussionComments responseData = repository.save(requestData);

        return mapper.map(responseData, DiscussionCommentsDto.class);
    }

    @Override
    public DiscussionCommentsDto updateDiscussionComments(long id, DiscussionCommentsDto commentsDto, User user) {
        Long userId = getUserId(user);

        DiscussionComments dataById = repository.findByIdAndStudentId(id, userId)
                                                .orElseThrow(
                                                        () -> new ResourceNotFoundException(
                                                                "Cannot Searchable for that Discussion"));
        DiscussionComments requestData = mapper.map(commentsDto, DiscussionComments.class);
        requestData.setStudent(userId);
        requestData.setId(id);

        Discussion discussion = discusRepository.findById(commentsDto.getDiscussionId())
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Cannot Searchable for that Discussion"));
        requestData.setDiscussion(discussion);

        DiscussionComments responseData = repository.save(requestData);

        return mapper.map(responseData, DiscussionCommentsDto.class);
    }

    @Override
    @Transactional
    public DiscussionCommentsDto deleteDiscussionComments(long id, User user) {
        Long userId = getUserId(user);

        DiscussionComments dataById = repository.findByIdAndStudentId(id, userId)
                                                .orElseThrow(
                                                        () -> new ResourceNotFoundException(
                                                                "Cannot Searchable for that Comments"));

        repository.deleteAllByParentCommentId(dataById);
        repository.deleteByIdAndStudentId(id, userId);

        return mapper.map(dataById, DiscussionCommentsDto.class);
    }

    @Override
    public void deleteAllCommentsByDiscussionId(Discussion discussion) {

        List<DiscussionComments> comments = repository.findAllByDiscussionId(discussion.getId());
        List<Long>               ids      = comments.stream().map(c -> c.getId()).toList();
        repository.deleteAllByParentCommentIds(ids);
        repository.deleteByDiscussionId(discussion.getId());
    }

    private Long getUserId(User user) {
        AuthUserResponse userData = userService.findByUsername(user.getUsername());
        return userData.getId();
    }
}
