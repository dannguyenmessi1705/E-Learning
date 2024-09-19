package com.didan.elearning.materials.controller;

import com.didan.elearning.materials.dto.error.ErrorDto;
import com.didan.elearning.materials.dto.request.ClassMaterialRequestDto;
import com.didan.elearning.materials.dto.response.GeneralResponse;
import com.didan.elearning.materials.entity.ClassMaterials;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

@Tag(
    name = "Class Material Controller",
    description = "Class material controller"
)
@Validated
@RequestMapping("${spring.application.name}/v1")
public interface IClassMaterialController {
  @Operation(
      summary = "Create class material",
      description = "Create class material",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Created activity log successfully",
              content = @Content(
                  schema = @Schema(implementation = GeneralResponse.class)
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Http Status Internal Server Error",
              content = @Content(
                  schema = @Schema(implementation = ErrorDto.class)
              )
          )
      }
  )
  @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  ResponseEntity<GeneralResponse<ClassMaterials>> createClassMaterial(
      @Valid @ModelAttribute ClassMaterialRequestDto requestDto
  );
}
