package com.waa.project.service;

import com.waa.project.dto.DiscussionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;

public interface DiscussionService {

    Page<DiscussionDto> getDiscussions(Pageable pageable, User user);

    DiscussionDto getDiscussionById(long id, User user);

    DiscussionDto createDiscussion(DiscussionDto discussionDto, User user);

    DiscussionDto updateDiscussion(long id, DiscussionDto discussionDto, User user);

    DiscussionDto deleteDiscussion(long id, User user);

    Page<DiscussionDto> searching(Pageable pageable, String text);
}
