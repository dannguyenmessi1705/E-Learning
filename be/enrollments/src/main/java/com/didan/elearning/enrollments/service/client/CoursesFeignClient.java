package com.didan.elearning.enrollments.service.client;

import com.didan.elearning.enrollments.dto.response.GeneralResponse;
import jakarta.validation.constraints.NotBlank;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "courses")
public interface CoursesFeignClient {
    @GetMapping({ "courses/v1/classes/check/{classCode}/{courseCode}/{semesterCode}" })
    ResponseEntity<GeneralResponse<Void>> checkExistClass(
            @PathVariable("classCode") @NotBlank(message = "Class code is required") String classCode,
            @PathVariable("courseCode") @NotBlank(message = "Course code is required") String courseCode,
            @PathVariable("semesterCode") @NotBlank(message = "Semester code is required") String semesterCode);
}
