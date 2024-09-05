package com.didan.elearning.users.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
@Schema(
    name = "RoleResponseDto",
    description = "Response body for a role"
)
public class RoleResponseDto {
  @Schema(
      name = "roleName",
      description = "The name of the role",
      example = "ADMIN"
  )
  private String roleName;
  @Schema(
      name = "userId",
      description = "The ID of the user with the role",
      example = "123456"
  )
  private String userId;
}
