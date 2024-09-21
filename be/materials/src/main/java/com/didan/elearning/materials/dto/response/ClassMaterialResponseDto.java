package com.didan.elearning.materials.dto.response;

import com.didan.elearning.materials.entity.SuperClass;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(
    name = "ClassMaterialResponseDto",
    description = "Class material response dto"
)
public class ClassMaterialResponseDto {
  @JsonProperty("id")
  private String materialId;
  @JsonProperty("classPeriodId")
  private String timeClassInDateId;
  private String classCode;
  @JsonProperty("creatorId")
  private String instructorId;
  private String title;
  private String description;
  private String path;
  private String materialType;
  private String size;
  private LocalDateTime createdAt;
}
