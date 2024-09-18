package com.didan.elearning.materials.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ClassFolder extends SuperClass {
  @Id
  private String classId;
  @Column
  private String classCode;
  @OneToMany(mappedBy = "classFolder")
  private List<ClassMaterials> classMaterials;
  @OneToMany(mappedBy = "classFolder")
  private List<ClassLivestream> classLivestreams;
}
