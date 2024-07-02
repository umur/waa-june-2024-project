package com.waa.project.service.impl;

import com.waa.project.dto.DiscussionDto;
import com.waa.project.entity.Discussion;
import com.waa.project.repository.DiscussionRepository;
import com.waa.project.service.DiscussionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository repository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public List<DiscussionDto> getDiscussions(Pageable pageable) {
        List<Discussion> discussions = repository.findAll();

        return discussions.stream().map(data -> mapper.map(data, DiscussionDto.class)).toList();
    }

    @Override
    public DiscussionDto getDiscussionById(long id) {
        Discussion discussion = repository.findById(id).orElseThrow(() -> new RuntimeException("Discussion not found"));

        return mapper.map(discussion, DiscussionDto.class);
    }

    @Override
    public DiscussionDto createDiscussion(DiscussionDto discussionDto) {
        Discussion discussion = repository.save(mapper.map(discussionDto, Discussion.class));
        return mapper.map(discussion, DiscussionDto.class);
    }

    @Override
    public DiscussionDto updateDiscussion(long id, DiscussionDto discussionDto) {

        Discussion dataById = repository.findById(id).orElseThrow(() -> new RuntimeException("Discussion not found"));

        Discussion discussion = repository.save(mapper.map(discussionDto, Discussion.class));
        return mapper.map(discussion, DiscussionDto.class);
    }

    @Override
    public void deleteDiscussion(long id) {
        repository.deleteById(id);
    }
}
