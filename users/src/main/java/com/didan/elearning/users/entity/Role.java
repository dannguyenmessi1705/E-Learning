package com.didan.elearning.users.entity;

import com.didan.elearning.users.constants.RoleConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String roleId;

  @Column
  private Enum<RoleConstants> roleName;

  @OneToMany(mappedBy = "role")
  private Set<UserRoles> roles;
}
