package com.didan.elearning.enrollments.service.impl;

import com.didan.elearning.enrollments.dto.response.GeneralResponse;
import com.didan.elearning.enrollments.service.IClientCourseService;
import com.didan.elearning.enrollments.service.client.CoursesFeignClient;
import java.util.Objects;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientCourseServiceImpl implements IClientCourseService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(ClientCourseServiceImpl.class);
    private final CoursesFeignClient coursesFeignClient;

    public boolean checkClassExisted(String classCode, String courseCode, String semesterCode) {
        try {
            ResponseEntity<GeneralResponse<Void>> checkResponse = this.coursesFeignClient.checkExistClass(classCode,
                    courseCode, semesterCode);
            return ((GeneralResponse) Objects.requireNonNull((GeneralResponse) checkResponse.getBody()))
                    .getStatusCode() == 200;
        } catch (Exception var5) {
            Exception e = var5;
            log.error("Error when check class existed", e);
            return false;
        }
    }

    @Generated
    public ClientCourseServiceImpl(final CoursesFeignClient coursesFeignClient) {
        this.coursesFeignClient = coursesFeignClient;
    }
}
