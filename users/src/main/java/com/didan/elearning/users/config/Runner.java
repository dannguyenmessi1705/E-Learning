package com.didan.elearning.users.config;

import com.didan.elearning.users.constants.RoleConstants;
import com.didan.elearning.users.entity.Role;
import com.didan.elearning.users.entity.User;
import com.didan.elearning.users.entity.UserRoles;
import com.didan.elearning.users.entity.key.UserRoleId;
import com.didan.elearning.users.repository.RoleRepository;
import com.didan.elearning.users.repository.UserRepository;
import com.didan.elearning.users.repository.UserRolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final UserRolesRepository userRolesRepository;
  @Override
  public void run(String... args) throws Exception {


    Role role = new Role();
    role.setRoleName(RoleConstants.ADMIN);
    roleRepository.save(role);

    User user = new User();
    user.setUsername("Dan");
    userRepository.save(user);

    UserRoles userRoles = new UserRoles();
    userRoles.setUserRoleId(new UserRoleId(user.getUserId(), role.getRoleId()));
    userRolesRepository.save(userRoles);
  }
}
