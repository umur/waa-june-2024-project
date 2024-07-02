package com.waa.project.service;

import com.waa.project.dto.DiscussionDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiscussionService {

    List<DiscussionDto> getDiscussions(Pageable pageable);

    DiscussionDto getDiscussionById(long id);

    DiscussionDto createDiscussion(DiscussionDto discussionDto);

    DiscussionDto updateDiscussion(long id, DiscussionDto discussionDto);

    void deleteDiscussion(long id);
}
