package com.didan.elearning.materials.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class ClassMaterials extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String materialId;
  @Column
  private String timeClassInDateId;
  @Column
  private String classCode;
  @Column
  private String instructorId;
  @Column
  private String title;
  @Column
  private String description;
  @Lob
  @Column(length = 16777216)
  private String url;
  @Column
  private String materialType; // materialTypeConstant
}
