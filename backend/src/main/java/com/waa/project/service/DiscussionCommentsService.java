package com.waa.project.service;

import com.waa.project.dto.DiscussionCommentsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;

public interface DiscussionCommentsService {

    Page<DiscussionCommentsDto> getCommentsByDiscussionId(Long discussionId, Pageable pageable);

//    DiscussionCommentsDto getDiscussionCommentsById(long id, User user);

    DiscussionCommentsDto createDiscussionComments(DiscussionCommentsDto commentsDto, User user,Long discussionId);

    DiscussionCommentsDto updateDiscussionComments(long id, DiscussionCommentsDto commentsDto, User user);

    DiscussionCommentsDto deleteDiscussionComments(long id, User user);
}
