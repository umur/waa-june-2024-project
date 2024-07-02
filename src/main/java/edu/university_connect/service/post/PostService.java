package edu.university_connect.service.post;

import edu.university_connect.domain.entity.discussionthread.Post;
import edu.university_connect.model.contract.dto.PostDto;
import edu.university_connect.model.contract.request.discussionthread.PostRequest;

import java.util.List;

public interface PostService {
    List<PostDto> getAll();

    Post getPost(Long id);

    PostDto getById(Long id);

    boolean delete(Long id);

    PostDto update(Long id, PostRequest postRequest);

    PostDto create(PostRequest postRequest);
}
