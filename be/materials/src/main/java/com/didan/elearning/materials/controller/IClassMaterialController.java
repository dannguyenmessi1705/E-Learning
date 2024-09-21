package com.didan.elearning.materials.controller;

import com.didan.elearning.materials.dto.error.ErrorDto;
import com.didan.elearning.materials.dto.request.ClassMaterialActionRequestDto;
import com.didan.elearning.materials.dto.request.ClassMaterialRequestDto;
import com.didan.elearning.materials.dto.response.ClassMaterialResponseDto;
import com.didan.elearning.materials.dto.response.GeneralResponse;
import com.didan.elearning.materials.entity.ClassMaterials;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
  ResponseEntity<GeneralResponse<ClassMaterialResponseDto>> createClassMaterial(
      @Valid @ModelAttribute ClassMaterialRequestDto requestDto
  );

  @Operation(
      summary = "Download class material",
      description = "Download class material",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Download class material successfully",
              content = @Content(
                  schema = @Schema(implementation = byte[].class)
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
  @PostMapping("/download")
  ResponseEntity<byte[]> downloadMaterial(
      @NotBlank(message = "File name is required") @RequestParam("fileName") String fileName,
      @NotBlank(message = "Class code is required") @RequestParam("classCode") String classCode
  );

  @Operation(
      summary = "Get url",
      description = "Get url",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get url successfully",
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
  @PostMapping("/get-url")
  ResponseEntity<GeneralResponse<String>> getUrl(
      @NotBlank(message = "File name is required") @RequestParam("fileName") String fileName,
      @NotBlank(message = "Class code is required") @RequestParam("classCode") String classCode
  );

  @Operation(
      summary = "Get detail class material",
      description = "Get detail class material",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get detail class material successfully",
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
  @GetMapping("/get")
  ResponseEntity<GeneralResponse<ClassMaterialResponseDto>> getClassMaterial(
      @NotBlank(message = "File name is required") @RequestParam("fileName") String fileName,
      @NotBlank(message = "Class code is required") @RequestParam("classCode") String classCode
  );

  @Operation(
      summary = "Get all materials in class",
      description = "Get all materials in class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Get all materials in class successfully",
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
  @GetMapping("/get/all")
  ResponseEntity<GeneralResponse<List<ClassMaterials>>> getAllMaterialsInClass(
      @NotBlank(message = "Class code is required") @RequestParam("classCode") String classCode
  );

  @Operation(
      summary = "Delete class material",
      description = "Delete class material",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Delete class material successfully",
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
  @DeleteMapping("/delete")
  ResponseEntity<GeneralResponse<Void>> deleteMaterial(
      @NotBlank(message = "File name is required") @RequestParam("fileName") String fileName,
      @NotBlank(message = "Class code is required") @RequestParam("classCode") String classCode
  );

  @Operation(
      summary = "Delete all materials in class",
      description = "Delete all materials in class",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Delete all materials in class successfully",
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
  @DeleteMapping("/delete/all")
  ResponseEntity<GeneralResponse<Void>> deleteAllMaterialsInClass(
      @NotBlank(message = "Class code is required") @RequestParam("classCode") String classCode
  );

  @Operation(
      summary = "Delete multiple materials",
      description = "Delete multiple materials",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Delete multiple materials successfully",
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
  @DeleteMapping("/delete/multiple")
  ResponseEntity<GeneralResponse<Void>> deleteMultipleMaterials(
      @RequestBody List<String> fileNames,
      @NotBlank(message = "Class code is required") @RequestParam("classCode") String classCode
  );

  @Operation(
      summary = "Copy file to other class code",
      description = "Copy file to other class code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Copy file to other class code successfully",
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
  @PostMapping("/copy")
  ResponseEntity<GeneralResponse<Void>> copyFile(@Valid @RequestBody ClassMaterialActionRequestDto requestDto);

  @Operation(
      summary = "Move file to other class code",
      description = "Move file to other class code",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Move file to other class code successfully",
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
  @PostMapping("/cut")
  ResponseEntity<GeneralResponse<Void>> cutFile(@Valid @RequestBody ClassMaterialActionRequestDto requestDto);
}
