package edu.miu.cs545.project.service;

import edu.miu.cs545.project.dto.ThreadPostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ThreadPostService {
    ResponseEntity<?> save(ThreadPostDto threadPostDto);
    ThreadPostDto  getThreadPostById(Long id);
    List<ThreadPostDto> getAllThreadPost();
    ThreadPostDto  updateThreadPost(ThreadPostDto threadPostDto);
    void deleteThreadPost(Long id);

}