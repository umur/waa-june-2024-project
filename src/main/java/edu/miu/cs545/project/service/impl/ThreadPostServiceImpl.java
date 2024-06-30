package edu.miu.cs545.project.service.impl;

import edu.miu.cs545.project.dto.ThreadPostDto;
import edu.miu.cs545.project.mapper.ThreadPostMapper;
import edu.miu.cs545.project.model.entity.Category;
import edu.miu.cs545.project.model.entity.ThreadPost;
import edu.miu.cs545.project.model.entity.User;
import edu.miu.cs545.project.repository.CategoryRepo;
import edu.miu.cs545.project.repository.ThreadPostRepository;
import edu.miu.cs545.project.repository.UserRepo;
import edu.miu.cs545.project.service.ThreadPostService;
import edu.miu.cs545.project.utility.MessageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ThreadPostServiceImpl implements ThreadPostService {
    @Autowired
    private ThreadPostRepository threadPostRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private MessageUtility messageUtility;



    @Override
    public ResponseEntity<?> save(ThreadPostDto threadPostDto) {
        try{
            Optional<ThreadPost> threadPostOpt = threadPostRepository.findByTitle(threadPostDto.getTitle().trim().toLowerCase());
            if(threadPostOpt.isPresent()){
                return ResponseEntity.ok(messageUtility.validationMessage(threadPostDto.getTitle()));
            }

            Optional<User> userOpt = null;
            if(threadPostDto.getUserId() != null){
                userOpt = userRepo.findById(threadPostDto.getUserId());
                if(!userOpt.isPresent()){
                    return ResponseEntity.ok(messageUtility.idNotFoundMessage());
                }
            }

            Optional<Category> categoryOpt = null;
            if(threadPostDto.getCategoryId() != null){
                categoryOpt = categoryRepo.findById(threadPostDto.getCategoryId());
                if(!categoryOpt.isPresent()){
                    return ResponseEntity.ok(messageUtility.idNotFoundMessage());
                }
            }

            ThreadPost threadPost = convertToEntity(threadPostDto);
            threadPostRepository.save(threadPost);
            return ResponseEntity.ok(messageUtility.saveMessage(threadPostDto.getTitle()));
        }catch (Exception e){
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @Override
    public ThreadPostDto getThreadPostById(Long id) {
        try {
            Optional<ThreadPost> threadPostOpt = threadPostRepository.findById(id);
            if (!threadPostOpt.isPresent()) {
                throw new RuntimeException(HttpStatus.EXPECTATION_FAILED.toString());
            }
            return convertToDto(threadPostOpt.get());
        }catch (Exception e){
            throw new RuntimeException(HttpStatus.EXPECTATION_FAILED.toString());
        }

    }

    @Override
    public List<ThreadPostDto> getAllThreadPost() {
        List<ThreadPostDto> threadPostDtoList =  new ArrayList<>();
         List<ThreadPost> allThreadPost = threadPostRepository.findAll();

         for(ThreadPost thpost : allThreadPost){
             ThreadPostDto threadPostDto = convertToDto(thpost);
             threadPostDtoList.add(threadPostDto);
         }

        return  threadPostDtoList;
    }

    @Override
    public ThreadPostDto updateThreadPost(ThreadPostDto threadPostDto) {
        try{
                Optional<ThreadPost> threadPostOptional = threadPostRepository.findById(threadPostDto.getId());
                if(!threadPostOptional.isPresent()) {
                    throw new RuntimeException("Thread id not found");
                }

            Optional<User> userOpt = null;
            if(threadPostDto.getUserId() != null){
                userOpt = userRepo.findById(threadPostDto.getUserId());
                if(!userOpt.isPresent()){
                    throw new RuntimeException("User id not found");
                }
            }

            Optional<Category> categoryOpt = null;
            if(threadPostDto.getCategoryId() != null){
                categoryOpt = categoryRepo.findById(threadPostDto.getCategoryId());
                if(!categoryOpt.isPresent()){
                    throw new RuntimeException("Category id not found");
                }
            }

            ThreadPost threadPost = threadPostOptional.get();
            threadPost.setTitle(threadPostDto.getTitle());
            threadPost.setCategory(categoryOpt.get());
            threadPost.setUser(userOpt.get());
            threadPostRepository.save(threadPost);
          return threadPostDto;
        }catch (Exception e){
            throw new RuntimeException(HttpStatus.EXPECTATION_FAILED.toString());
        }
    }

    @Override
    public void deleteThreadPost(Long id) {
        try{
            Optional<ThreadPost> threadPostOptional = threadPostRepository.findById(id);
            if(!threadPostOptional.isPresent()) {
                throw new RuntimeException("Thread id not found");
            }

            threadPostRepository.deleteById(id);
        }catch (Exception e){
        throw new RuntimeException(HttpStatus.EXPECTATION_FAILED.toString());
    }
    }


    private ThreadPostDto convertToDto(ThreadPost threadPost) {
        return new ThreadPostDto(
                threadPost.getId(),
                threadPost.getTitle(),
                threadPost.getCreatedAt(),
                threadPost.getCategory() != null ? threadPost.getCategory().getId() : null,
                threadPost.getUser() != null ? threadPost.getUser().getId() : null
        );
    }

    private ThreadPost convertToEntity(ThreadPostDto threadPostDto) {
        ThreadPost threadPost = new ThreadPost();
      //  threadPost.setId(postThreadDto.getId());
        threadPost.setTitle(threadPostDto.getTitle());
        if (threadPostDto.getCategoryId() != null) {
            categoryRepo.findById(threadPostDto.getCategoryId()).ifPresent(threadPost::setCategory);
        }
        if (threadPostDto.getUserId() != null) {
            userRepo.findById(threadPostDto.getUserId()).ifPresent(threadPost::setUser);
        }
        return threadPost;
    }
}
