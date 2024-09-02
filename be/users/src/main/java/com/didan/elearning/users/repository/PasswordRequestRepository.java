package com.didan.elearning.users.repository;

import com.didan.elearning.users.entity.PasswordRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRequestRepository extends JpaRepository<PasswordRequest, String> {

}
