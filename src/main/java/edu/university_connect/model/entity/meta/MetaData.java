package edu.university_connect.model.entity.meta;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class MetaData implements Serializable {

    @CreatedDate
    @Column(name = "created_at", updatable=false)
    LocalDateTime createdAt;

    @CreatedBy
    @AttributeOverride(name = "username", column = @Column(name = "created_by", updatable=false))
    @Embedded
    Username createdBy;

    @LastModifiedDate
    @Column(name = "last_modified_at",insertable=false)
    LocalDateTime lastModifiedAt;

    @LastModifiedBy
    @AttributeOverride(name = "username", column = @Column(name = "last_modified_by",insertable=false))
    @Embedded
    Username lastModifiedBy;

}
