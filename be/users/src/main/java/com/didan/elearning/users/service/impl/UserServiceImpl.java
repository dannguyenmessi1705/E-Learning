package com.didan.elearning.users.service.impl;

import com.didan.elearning.users.constant.RoleConstants;
import com.didan.elearning.users.dto.request.CreateUserRequestDto;
import com.didan.elearning.users.dto.request.UpdateUserRequestDto;
import com.didan.elearning.users.dto.response.CreateUserResponseDto;
import com.didan.elearning.users.dto.response.UpdateUserDetailResponseDto;
import com.didan.elearning.users.entity.Role;
import com.didan.elearning.users.entity.StudentDetails;
import com.didan.elearning.users.entity.User;
import com.didan.elearning.users.entity.UserRoles;
import com.didan.elearning.users.entity.key.UserRoleId;
import com.didan.elearning.users.exception.ResourceNotFoundException;
import com.didan.elearning.users.exception.UserAlreadyExistException;
import com.didan.elearning.users.mapper.Mapper;
import com.didan.elearning.users.repository.RoleRepository;
import com.didan.elearning.users.repository.StudentDetailsRepository;
import com.didan.elearning.users.repository.UserRepository;
import com.didan.elearning.users.repository.UserRolesRepository;
import com.didan.elearning.users.service.IUserService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
  private final UserRepository userRepository;
  private final UserRolesRepository userRolesRepository;
  private final RoleRepository roleRepository;
  private final StudentDetailsRepository studentDetailsRepository;
  @Override
  public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
    log.info("Creating user...");
    if (userRepository.existsUserByEmail(createUserRequestDto.getEmail())) {
      log.error("Email already exists");
      throw new UserAlreadyExistException("Email already exists");
    } else if (userRepository.existsUserByUsername(createUserRequestDto.getUsername())) {
      log.error("Username already exists");
      throw new UserAlreadyExistException("Username already exists");
    } else if (userRepository.existsUserByPhoneNumber(createUserRequestDto.getPhoneNumber())) {
      log.error("Phone number already exists");
      throw new UserAlreadyExistException("Phone number already exists");
    }
    User user = Mapper.mapToDto(createUserRequestDto, User.class);
    userRepository.save(user);
    CreateUserResponseDto resUser = Mapper.mapToDto(user, CreateUserResponseDto.class);
    log.info("User created successfully, {}", resUser);
    return resUser;
  }

  @Override
  public UpdateUserDetailResponseDto updateUser(String userId,
      UpdateUserRequestDto updateUserRequestDto) {
    log.info("Finding userId: {}", userId);
    Optional<User> user = userRepository.findById(userId);
    if (user.isEmpty()) {
      log.error("User not found");
      throw new ResourceNotFoundException("User not found");
    }
    StudentDetails mappedStudent = Mapper.mapToDto(updateUserRequestDto, StudentDetails.class);
    if (StringUtils.hasText(updateUserRequestDto.getEmail())) {
      if (userRepository.existsUserByEmail(updateUserRequestDto.getEmail())) {
        log.error("Email already exists");
        throw new UserAlreadyExistException("Email already exists");
      }
      user.get().setEmail(updateUserRequestDto.getEmail());
    }
    if (StringUtils.hasText(updateUserRequestDto.getUsername())) {
      if (userRepository.existsUserByUsername(updateUserRequestDto.getUsername())) {
        log.error("Username already exists");
        throw new UserAlreadyExistException("Username already exists");
      }
      user.get().setUsername(updateUserRequestDto.getUsername());
    }
    if (StringUtils.hasText(updateUserRequestDto.getPhoneNumber())) {
      if (userRepository.existsUserByPhoneNumber(updateUserRequestDto.getPhoneNumber())) {
        log.error("Phone number already exists");
        throw new UserAlreadyExistException("Phone number already exists");
      }
      user.get().setPhoneNumber(updateUserRequestDto.getPhoneNumber());
    }
    if (StringUtils.hasText(updateUserRequestDto.getAddress())) {
      user.get().setAddress(updateUserRequestDto.getAddress());
    }
    if (StringUtils.hasText(updateUserRequestDto.getDateOfBirth())) {
      user.get().setDateOfBirth(updateUserRequestDto.getDateOfBirth());
    }
    if (StringUtils.hasText(updateUserRequestDto.getPassword())){
      user.get().setPassword(updateUserRequestDto.getPassword());
    }
    if (StringUtils.hasText(updateUserRequestDto.getProfilePicture())) {
      user.get().setProfilePicture(updateUserRequestDto.getProfilePicture());
    }


    if (StringUtils.hasText(updateUserRequestDto.getMajor()) || StringUtils.hasText(
        String.valueOf(updateUserRequestDto.getStartYear()))) {
      Optional<StudentDetails> studentDetails = studentDetailsRepository
          .findFirstByUser_UserId(userId);
      if (studentDetails.isEmpty()) {
        List<StudentDetails> studentDetailsList = studentDetailsRepository.findAll();
        mappedStudent.setStudentCode("D" + mappedStudent.getStartYear() + (studentDetailsList.size() + 1));
        mappedStudent.setUserId(userId);
        mappedStudent.setUser(user.get());
        user.get().setStudentDetails(mappedStudent);
      } else {
        mappedStudent.setUserId(studentDetails.get().getUserId());
        user.get().setStudentDetails(mappedStudent);
      }
    }
    userRepository.save(user.get());
    UpdateUserDetailResponseDto resUser = Mapper.mapToDto(user.get(), UpdateUserDetailResponseDto.class);
    log.info("User updated successfully, {}", resUser);
    return resUser;
  }

  public void setRoleUser(User user, String roleName) {
    Optional<Role> role = roleRepository.findFirstByRoleName(roleName);
    if (role.isEmpty()) {
      log.error("Role not found");
      throw new ResourceNotFoundException("Role not found");
    }
    UserRoles userRoles = new UserRoles();
    userRoles.setUserRoleId(new UserRoleId(user.getUserId(), role.get().getRoleId()));
    userRolesRepository.save(userRoles);
  }
}
