package edu.miu.cs545.project.service.impl;

import edu.miu.cs545.project.model.entity.Post;
import edu.miu.cs545.project.repository.PostRepository;
import edu.miu.cs545.project.repository.ThreadPostRepository;
import edu.miu.cs545.project.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends CrudServiceImpl<Post,Long> implements PostService {
    public PostServiceImpl(PostRepository postRepository) {
        super(postRepository);
    }

    @Autowired
    private  PostRepository postRepository;

    @Autowired
    private ThreadPostRepository threadPostRepository;

    @Override
    public Post create(Post post) {
        //very first post
        if(post.getParentPost() != null && post.getParentPost().getId() == null) {
            // Ensure that the associated ThreadPost is saved
            if (post.getThreadPost() != null && post.getThreadPost().getId() == null) {
                post.setThreadPost(threadPostRepository.save(post.getThreadPost()));
            }
        }
        return postRepository.save(post);
    }
}
