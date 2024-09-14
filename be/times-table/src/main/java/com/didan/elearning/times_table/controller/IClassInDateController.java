package com.didan.elearning.times_table.controller;

import com.didan.elearning.times_table.dto.error.ErrorDto;
import com.didan.elearning.times_table.dto.request.ClassInDateRequestDto;
import com.didan.elearning.times_table.dto.response.ClassInDateResponseDto;
import com.didan.elearning.times_table.dto.response.GeneralResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${spring.application.name}/v1/class-date")
@Validated
@Tag(
    name = "Class In Date Controller",
    description = "APIs for managing class in date"
)
public interface IClassInDateController {
  @Operation(
      summary = "Create class in date",
      description = "Create class in date",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Class in date created",
              content = @Content(
                  schema = @Schema(
                      implementation = GeneralResponse.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(
                      implementation = ErrorDto.class
                  )
              )
          )
      }
  )
  @PostMapping("/create")
  ResponseEntity<GeneralResponse<ClassInDateResponseDto>> createClassInDate(
      @Valid @RequestBody ClassInDateRequestDto classInDateRequestDto);

  @Operation(
      summary = "Update class in date",
      description = "Update class in date",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Class in date updated",
              content = @Content(
                  schema = @Schema(
                      implementation = GeneralResponse.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(
                      implementation = ErrorDto.class
                  )
              )
          )
      }
  )
  @PutMapping("/update/{classInDateId}")
  ResponseEntity<GeneralResponse<ClassInDateResponseDto>> updateClassInDate(
      @NotBlank(message = "Class in date ID is required") @PathVariable("classInDateId") String classInDateId,
      @Valid @RequestBody ClassInDateRequestDto classInDateRequestDto);

  @Operation(
      summary = "Get all class in date by class code and date",
      description = "Get all class in date by class code and date",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Class in date list",
              content = @Content(
                  schema = @Schema(
                      implementation = GeneralResponse.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(
                      implementation = ErrorDto.class
                  )
              )
          )
      }
  )
  @GetMapping("/get/{classCode}/{date}")
  ResponseEntity<GeneralResponse<List<ClassInDateResponseDto>>> getAllClassInDateByClassCodeAndDate(
      @NotBlank(message = "Class code is required") @PathVariable("classCode") String classCode,
      @NotBlank(message = "Date is required") @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date must be in format yyyy-MM-dd") @PathVariable("date") String date
  );

  @Operation(
      summary = "Delete class in date",
      description = "Delete class in date",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Class in date deleted",
              content = @Content(
                  schema = @Schema(
                      implementation = GeneralResponse.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "500",
              description = "Internal server error",
              content = @Content(
                  schema = @Schema(
                      implementation = ErrorDto.class
                  )
              )
          )
      }
  )
  @DeleteMapping("/delete/{classInDateId}")
  ResponseEntity<GeneralResponse<Void>> deleteClassInDate(
      @NotBlank(message = "Class in date ID is required") @PathVariable("classInDateId") String classInDateId);

}
