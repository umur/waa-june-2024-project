package edu.university_connect.service.post;

import edu.university_connect.config.ContextUser;
import edu.university_connect.domain.entity.discussionthread.Category;
import edu.university_connect.domain.entity.discussionthread.Post;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.PostDtoMapper;
import edu.university_connect.model.contract.dto.PostDto;
import edu.university_connect.model.contract.request.discussionthread.PostRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.CategoryRepository;
import edu.university_connect.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ContextUser contextUser;

    @Override
    public List<PostDto> getAll() {
        return postRepository.findAll()
                .stream()
                .map(PostDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public PostDto getById(Long id) {
        return PostDtoMapper.MAPPER.entityToDto(getPost(id));
    }

    @Override
    public boolean delete(Long id) {
        postRepository.deleteById(getPost(id).getId());
        return true;
    }

    @Override
    public PostDto update(Long id, PostRequest postRequest) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "id post");
        }
        Post post = postOpt.get();
        post.setContent(postRequest.getContent());
        Category category = validatePostCategory(postRequest.getCategoryId());
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return PostDtoMapper.MAPPER.entityToDto(savedPost);
    }

    @Override
    public PostDto create(PostRequest postRequest) {
        Post post = PostDtoMapper.MAPPER.dtoToEntity(postRequest);
        Category category = validatePostCategory(postRequest.getCategoryId());
        post.setCategory(category);
        post.setUser(contextUser.getLoginUser().getUser());
        Post savedPost = postRepository.save(post);
        return PostDtoMapper.MAPPER.entityToDto(savedPost);
    }

    public Post getPost(Long id) {
        Optional<Post> postOpt = postRepository.findById(id);
        if (postOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "id post", id.toString());
        }
        return postOpt.get();
    }

    private Category validatePostCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "id category", categoryId.toString());
        }
        return category.get();
    }
}
