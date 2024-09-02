package com.didan.elearning.grades.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter @Setter @ToString
@EntityListeners(AuditingEntityListener.class)
public class SuperClass {
  @CreatedDate
  private LocalDateTime createdAt;
  @LastModifiedDate
  private LocalDateTime updatedAt;
}
