package com.didan.elearning.users.config;

import com.didan.elearning.users.constant.RoleConstants;
import com.didan.elearning.users.entity.Role;
import com.didan.elearning.users.entity.User;
import com.didan.elearning.users.entity.UserRoles;
import com.didan.elearning.users.entity.key.UserRoleId;
import com.didan.elearning.users.repository.RoleRepository;
import com.didan.elearning.users.repository.UserRepository;
import com.didan.elearning.users.repository.UserRolesRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RunningConfig implements CommandLineRunner {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserRolesRepository userRolesRepository;
  @Override
  public void run(String... args) throws Exception {
    Optional<Role> guest = roleRepository.findFirstByRoleName(RoleConstants.GUEST);
    if (guest.isEmpty()) {
      createRoles(RoleConstants.GUEST);
    }

    Optional<Role> admin = roleRepository.findFirstByRoleName(RoleConstants.ADMIN);
    if (admin.isEmpty()) {
      createRoles(RoleConstants.ADMIN);
    }

    Optional<Role> student = roleRepository.findFirstByRoleName(RoleConstants.STUDENT);
    if (student.isEmpty()) {
      createRoles(RoleConstants.STUDENT);
    }

    Optional<Role> instructor = roleRepository.findFirstByRoleName(RoleConstants.INSTRUCTOR);
    if (instructor.isEmpty()) {
      createRoles(RoleConstants.INSTRUCTOR);
    }

    Optional<Role> assistant = roleRepository.findFirstByRoleName(RoleConstants.ASSISTANT);
    if (assistant.isEmpty()) {
      createRoles(RoleConstants.ASSISTANT);
    }
  }

  void createRoles(String roleName) {
    Role role = new Role();
    role.setRoleName(roleName);
    roleRepository.save(role);
  }
}
