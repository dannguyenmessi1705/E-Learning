package com.didan.elearning.courses.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
@Schema(
      name = "ClassRequestDto",
      description = "Request DTO for creating a class"
)
public class ClassRequestDto {
    @Schema(
            name = "studentYear",
            description = "The year of the student being added to the university",
            example = "2020"
    )
    @NotNull(message = "Student year is required")
    @Min(value = 1960, message = "Student year must be at least 1960")
    @Max(value = 9999, message = "Student year must be at most 9999")
    private Integer studentYear;

    @Schema(
            name = "instructorId",
            description = "The ID of the instructor",
            example = "123456"
    )
    @NotEmpty(message = "Instructor ID is required")
    private String instructorId;

    @Schema(
            name = "assistantId",
            description = "The ID of the assistant",
            example = "123456"
    )
    private String assistantId;

    @Schema(
            name = "className",
            description = "The name of the class",
            example = "Introduction to Computer Science"
    )
    @NotEmpty(message = "Class name is required")
    private String className;

    @Schema(
            name = "capacity",
            description = "The capacity of the class",
            example = "30"
    )
    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 100, message = "Capacity must be at most 100")
    private Integer capacity;

    @Schema(
            name = "courseCode",
            description = "The code of the course",
            example = "CSA101"
    )
    @NotEmpty(message = "Course code is required")
    private String courseCode;
}
