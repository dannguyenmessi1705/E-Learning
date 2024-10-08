package com.didan.elearning.users.repository;

import com.didan.elearning.users.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
  Optional<Role> findFirstByRoleName(String name);
}
