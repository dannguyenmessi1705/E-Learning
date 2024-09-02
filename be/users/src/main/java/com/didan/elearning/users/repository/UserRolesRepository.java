package com.didan.elearning.users.repository;

import com.didan.elearning.users.entity.UserRoles;
import com.didan.elearning.users.entity.key.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UserRoleId> {

}
