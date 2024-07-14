package edu.university_connect.service.post;

import edu.university_connect.domain.entity.discussionthread.Post;
import edu.university_connect.model.contract.dto.PostDto;
import edu.university_connect.model.contract.request.discussionthread.PostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<PostDto> getAll();

    Page<PostDto> getPage(Pageable pageable);

    Page<PostDto> getByUser(Long userId, Pageable pageable);

    Page<PostDto> search(String term, Pageable pageable);

    Page<PostDto> getPage(Long categoryId, Pageable pageable);

    Post getPost(Long id);

    PostDto getById(Long id);

    boolean delete(Long id);

    PostDto update(Long id, PostRequest postRequest);

    PostDto create(PostRequest postRequest);
}
