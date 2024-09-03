package com.didan.elearning.users.config;

import com.didan.elearning.users.constant.RoleConstants;
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
public class RunningConfig implements CommandLineRunner {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserRolesRepository userRolesRepository;
  @Override
  public void run(String... args) throws Exception {
    Role role1 = new Role();
    role1.setRoleName(RoleConstants.GUEST);
    roleRepository.save(role1);

    Role role2 = new Role();
    role2.setRoleName(RoleConstants.ADMIN);
    roleRepository.save(role2);

    Role role3 = new Role();
    role3.setRoleName(RoleConstants.STUDENT);
    roleRepository.save(role3);

    Role role4 = new Role();
    role4.setRoleName(RoleConstants.INSTRUCTOR);
    roleRepository.save(role4);

    Role role5 = new Role();
    role5.setRoleName(RoleConstants.ASSISTANT);
    roleRepository.save(role5);
  }
}
