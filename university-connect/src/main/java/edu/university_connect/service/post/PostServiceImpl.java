package edu.university_connect.service.post;

import edu.university_connect.config.ContextUser;
import edu.university_connect.domain.entity.discussionthread.Category;
import edu.university_connect.domain.entity.discussionthread.Post;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.PostDtoMapper;
import edu.university_connect.model.contract.dto.PostDto;
import edu.university_connect.model.contract.request.RequestUtils;
import edu.university_connect.model.contract.request.discussionthread.PostRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.CategoryRepository;
import edu.university_connect.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return postRepository.findAllUnblockedPosts()
                .stream()
                .map(PostDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public Page<PostDto> getPage(Pageable pageable) {
        return postRepository
                .findAllUnblockedPosts(pageable)
                .map(PostDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public Page<PostDto> getByUser(Long userId, Pageable pageable) {
        return postRepository
                .findByUserId(userId, pageable)
                .map(PostDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public Page<PostDto> search(String term, Pageable pageable) {
        String sanitizedTerm = term.replace("@", "");
        return postRepository
                .searchPosts(sanitizedTerm, sanitizedTerm, sanitizedTerm, pageable)
                .map(PostDtoMapper.MAPPER::entityToDto);
    }

    @Override
    public Page<PostDto> getPage(Long categoryId, Pageable pageable) {
        return postRepository
                .findByCategory(categoryId, RequestUtils.extractPagination(pageable))
                .map(PostDtoMapper.MAPPER::entityToDto);
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
        Post post = getPost(id);
        post.setContent(postRequest.getContent());
        Category category = validatePostCategory(postRequest.getCategoryId());
        post.setCategory(category);
        post = postRepository.save(post);
        return PostDtoMapper.MAPPER.entityToDto(post);
    }

    @Override
    public PostDto create(PostRequest postRequest) {
        Post post = PostDtoMapper.MAPPER.dtoToEntity(postRequest);
        Category category = validatePostCategory(postRequest.getCategoryId());
        post.setCategory(category);
        post.setUser(contextUser.getLoginUser().getUser());
        post = postRepository.save(post);
        return PostDtoMapper.MAPPER.entityToDto(post);
    }

    public Post getPost(Long id) {
        Optional<Post> postOpt = postRepository.findUnblockedPost(id);
        if (postOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "post", "id " + id);
        }
        return postOpt.get();
    }

    private Category validatePostCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "category", "id " + categoryId);
        }
        return category.get();
    }
}
