package com.waa.project.service.impl;

import com.waa.project.dto.DiscussionCommentsDto;
import com.waa.project.entity.DiscussionComments;
import com.waa.project.repository.DiscussionCommentsRepository;
import com.waa.project.repository.DiscussionRepository;
import com.waa.project.security.contract.AuthUserResponse;
import com.waa.project.service.SubCommentService;
import com.waa.project.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubCommentServiceImpl implements SubCommentService {

    @Autowired
    private DiscussionCommentsRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DiscussionRepository discusRepository;

    @Override
    public Page<DiscussionCommentsDto> getSubCommentsByDiscussionId(Long commentId, Pageable pageable) {
        return repository.findByParentCommentId(commentId, pageable)
                         .map(data -> mapper.map(data, DiscussionCommentsDto.class));
    }

    @Override
    public DiscussionCommentsDto createSubDiscussionComments(DiscussionCommentsDto commentsDto, User user) {
        DiscussionComments requestData = mapper.map(commentsDto, DiscussionComments.class);
        requestData.setStudent(getUserId(user));

        DiscussionComments parentComment = repository.findById(commentsDto.getParentComment_id())
                                                     .orElseThrow(() -> new RuntimeException(
                                                             "Comment ID not found"));

        requestData.setParentCommentId(parentComment);

        DiscussionComments responseData = repository.save(requestData);

        return mapper.map(responseData, DiscussionCommentsDto.class);
    }

    @Override
    public DiscussionCommentsDto updateSubDiscussionComments(long id, DiscussionCommentsDto commentsDto, User user) {
        Long               userId      = getUserId(user);
        DiscussionComments requestData = mapper.map(commentsDto, DiscussionComments.class);
        requestData.setStudent(userId);
        requestData.setId(id);

        DiscussionComments parentComment = repository.findByIdAndStudentId(id, userId)
                                                     .orElseThrow(() -> new RuntimeException(
                                                             "Comment ID not found"));
        requestData.setParentCommentId(parentComment.getParentCommentId());

        DiscussionComments responseData = repository.save(requestData);

        return mapper.map(responseData, DiscussionCommentsDto.class);
    }

    @Override
    @Transactional
    public DiscussionCommentsDto deleteSubDiscussionComments(long id, User user) {
        Long userId = getUserId(user);

        DiscussionComments dataById = repository.findByIdAndStudentId(id, userId)
                                                .orElseThrow(() -> new RuntimeException("Comment ID not found"));

        repository.deleteByIdAndStudentId(id, userId);

        return mapper.map(dataById, DiscussionCommentsDto.class);
    }

    private Long getUserId(User user) {
        AuthUserResponse userData = userService.findByUsername(user.getUsername());
        return userData.getId();
    }
}
