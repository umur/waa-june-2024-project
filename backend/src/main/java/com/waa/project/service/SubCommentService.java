package com.waa.project.service;

import com.waa.project.dto.DiscussionCommentsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;

public interface SubCommentService {

    Page<DiscussionCommentsDto> getSubCommentsByDiscussionId(Long commentId, Pageable pageable);

    DiscussionCommentsDto createSubDiscussionComments(DiscussionCommentsDto commentsDto, User user);

    DiscussionCommentsDto updateSubDiscussionComments(long id, DiscussionCommentsDto commentsDto, User user);

    DiscussionCommentsDto deleteSubDiscussionComments(long id, User user);
}
