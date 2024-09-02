package com.didan.elearning.materials.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class ClassLivestream extends SuperClass{
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String livestreamId;
  @Column
  private String timeClassInDateId;
  @Column
  private String instructorId;
  @Column
  private String title;
  @Column
  private String description;
  @Column
  private String url;
  @Column
  private String materialType; // materialTypeConstant

}
