package miu.waa.project1.repository;

import lombok.Getter;
import lombok.Setter;
import miu.waa.project1.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findAllByUserId(Long userId);
}
