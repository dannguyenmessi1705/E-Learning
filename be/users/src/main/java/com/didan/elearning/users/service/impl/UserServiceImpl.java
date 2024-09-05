package com.didan.elearning.users.service.impl;

import com.didan.elearning.users.constant.CheckBoolean;
import com.didan.elearning.users.constant.GenderConstants;
import com.didan.elearning.users.constant.MessageConstant;
import com.didan.elearning.users.constant.RoleConstants;
import com.didan.elearning.users.dto.request.CreateUserRequestDto;
import com.didan.elearning.users.dto.request.UpdateUserRequestDto;
import com.didan.elearning.users.dto.response.CreateUserResponseDto;
import com.didan.elearning.users.dto.response.RoleResponseDto;
import com.didan.elearning.users.dto.response.UpdateUserDetailResponseDto;
import com.didan.elearning.users.entity.Role;
import com.didan.elearning.users.entity.StudentDetails;
import com.didan.elearning.users.entity.User;
import com.didan.elearning.users.entity.UserRoles;
import com.didan.elearning.users.entity.key.UserRoleId;
import com.didan.elearning.users.exception.FieldErrorException;
import com.didan.elearning.users.exception.ResourceNotFoundException;
import com.didan.elearning.users.exception.UserAlreadyExistException;
import com.didan.elearning.users.mapper.Mapper;
import com.didan.elearning.users.repository.RoleRepository;
import com.didan.elearning.users.repository.StudentDetailsRepository;
import com.didan.elearning.users.repository.UserRepository;
import com.didan.elearning.users.repository.UserRolesRepository;
import com.didan.elearning.users.service.IPasswordRequestService;
import com.didan.elearning.users.service.IUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
  private final UserRepository userRepository;
  private final UserRolesRepository userRolesRepository;
  private final RoleRepository roleRepository;
  private final StudentDetailsRepository studentDetailsRepository;
  private final IPasswordRequestService passwordRequestService;
  @Override
  public CreateUserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
    if (checkExist("email", createUserRequestDto.getEmail())) {
      log.info(MessageConstant.EMAIL_ALREADY_EXISTS);
      throw new UserAlreadyExistException(MessageConstant.EMAIL_ALREADY_EXISTS);
    } else if (checkExist("username", createUserRequestDto.getUsername())) {
      log.info(MessageConstant.USERNAME_ALREADY_EXISTS);
      throw new UserAlreadyExistException(MessageConstant.USERNAME_ALREADY_EXISTS);
    } else if (checkExist("phoneNumber", createUserRequestDto.getPhoneNumber())) {
      log.info(MessageConstant.PHONE_NUMBER_ALREADY_EXISTS);
      throw new UserAlreadyExistException(MessageConstant.PHONE_NUMBER_ALREADY_EXISTS);
    }
    User user = Mapper.map(createUserRequestDto, User.class);
    userRepository.save(user);
    CreateUserResponseDto resUser = Mapper.map(user, CreateUserResponseDto.class);
    log.info(MessageConstant.USER_CREATED_SUCCESSFULLY + ": {}", resUser);
    return resUser;
  }

  @Override
  public UpdateUserDetailResponseDto updateUser(String userId,
      UpdateUserRequestDto updateUserRequestDto) {
    log.info("Finding userId: {}", userId);
    User user = userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND);
    });

    StudentDetails mappedStudent = Mapper.map(updateUserRequestDto, StudentDetails.class);
    if (StringUtils.hasText(updateUserRequestDto.getEmail())) {
      if (checkExist("email", updateUserRequestDto.getEmail())) {
        log.info(MessageConstant.EMAIL_ALREADY_EXISTS);
        throw new UserAlreadyExistException(MessageConstant.EMAIL_ALREADY_EXISTS);
      }
      user.setEmail(updateUserRequestDto.getEmail());
    }
    if (StringUtils.hasText(updateUserRequestDto.getUsername())) {
      if (checkExist("username", updateUserRequestDto.getUsername())) {
        log.info("Username already exists");
        throw new UserAlreadyExistException("Username already exists");
      }
      user.setUsername(updateUserRequestDto.getUsername());
    }
    if (StringUtils.hasText(updateUserRequestDto.getPhoneNumber())) {
      if (checkExist("phoneNumber", updateUserRequestDto.getPhoneNumber())) {
        log.info("Phone number already exists");
        throw new UserAlreadyExistException("Phone number already exists");
      }
      user.setPhoneNumber(updateUserRequestDto.getPhoneNumber());
    }
    if (StringUtils.hasText(updateUserRequestDto.getAddress())) {
      user.setAddress(updateUserRequestDto.getAddress());
    }
    if (StringUtils.hasText(updateUserRequestDto.getDateOfBirth())) {
      user.setDateOfBirth(updateUserRequestDto.getDateOfBirth());
    }
    if (StringUtils.hasText(updateUserRequestDto.getPassword())){
      user.setPassword(updateUserRequestDto.getPassword());
    }
    if (StringUtils.hasText(updateUserRequestDto.getProfilePicture())) {
      user.setProfilePicture(updateUserRequestDto.getProfilePicture());
    }
    if (StringUtils.hasText(updateUserRequestDto.getGender())) {
      String gender = updateUserRequestDto.getGender().toUpperCase();
      List<String> genderList = List.of(GenderConstants.UNDERTERMINED, GenderConstants.FEMALE, GenderConstants.MALE);
      if (!genderList.contains(gender)) {
        throw new FieldErrorException("The gender field is not valid");
      }
      user.setGender(updateUserRequestDto.getGender());
    }

    if (StringUtils.hasText(updateUserRequestDto.getMajor()) || StringUtils.hasText(String.valueOf(updateUserRequestDto.getStartYear()))) {
      Optional<StudentDetails> studentDetails = studentDetailsRepository.findFirstByUser_UserId(userId);
      if (studentDetails.isEmpty()) {
        mappedStudent.setStudentCode(setStudentCode(updateUserRequestDto.getMajor(), updateUserRequestDto.getStartYear()));
        mappedStudent.setUserId(userId);
        mappedStudent.setUser(user);
        user.setStudentDetails(mappedStudent);
        setRoleUser(user, RoleConstants.STUDENT);
      } else {
        mappedStudent.setUserId(studentDetails.get().getUserId());
        user.setStudentDetails(mappedStudent);
      }
    }
    userRepository.save(user);
    UpdateUserDetailResponseDto resUser = Mapper.map(user, UpdateUserDetailResponseDto.class);
    resUser = Mapper.map(mappedStudent, resUser);
    log.info("User updated successfully, {}", resUser);
    return resUser;
  }

  public boolean checkExist(String fieldName, String value) {
    return switch (fieldName) {
      case "email" -> userRepository.existsUserByEmail(value);
      case "username" -> userRepository.existsUserByUsername(value);
      case "phoneNumber" -> userRepository.existsUserByPhoneNumber(value);
      case "studentCode" -> studentDetailsRepository.existsByStudentCode(value);
      default -> false;
    };
  }
  public void setRoleUser(User user, String roleName) {
    Role role = roleRepository.findFirstByRoleName(roleName).orElseThrow(() -> {
      log.info(MessageConstant.ROLE_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.ROLE_NOT_FOUND);
    });
    if (user.getRoles().stream().anyMatch(userRoles -> userRoles.getRole().getRoleName().equals(roleName))) {
      log.info(MessageConstant.USER_ALREADY_HAS_ROLE + " " + roleName);
      throw new UserAlreadyExistException(MessageConstant.USER_ALREADY_HAS_ROLE + " " + roleName);
    }
    UserRoles userRoles = new UserRoles();
    userRoles.setUserRoleId(new UserRoleId(user.getUserId(), role.getRoleId()));
    userRolesRepository.save(userRoles);
  }
  public void unSetRoleUser(User user, String roleName) {
    roleRepository.findFirstByRoleName(roleName).orElseThrow(() -> {
      log.info(MessageConstant.ROLE_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.ROLE_NOT_FOUND);
    });
    if (user.getRoles().stream().noneMatch(userRoles -> userRoles.getRole().getRoleName().equals(roleName))) {
      log.info(MessageConstant.USER_DOES_NOT_HAVE_ROLE + " " + roleName);
      throw new UserAlreadyExistException(MessageConstant.USER_DOES_NOT_HAVE_ROLE + " " + roleName);
    }
    user.getRoles().removeIf(userRoles -> userRoles.getRole().getRoleName().equals(roleName));
    userRepository.save(user);
  }
  public String setStudentCode(String major, int startYear) {
    int random = (int) (Math.random() * 1000);
    String[] majorSplit = major.split("\\s+");
    StringBuilder majorCode = new StringBuilder();
    for (String s : majorSplit) {
      majorCode.append(s.charAt(0));
    }
    String studentCode = "D" + startYear + majorCode + random;
    while (checkExist("studentCode", studentCode)) {
      studentCode = "D" + startYear + majorCode.toString().toUpperCase() + random;
    }
    return studentCode;
  }

  @Override
  public RoleResponseDto assignRole(String userId, String roleName) {
    User user = userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND);
    });
    setRoleUser(user, roleName);
    RoleResponseDto resRole = new RoleResponseDto();
    resRole.setRoleName(roleName);
    resRole.setUserId(userId);
    return resRole;
  }

  @Override
  public RoleResponseDto unassignRole(String userId, String roleName) {
    User user = userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND);
    });
    unSetRoleUser(user, roleName);
    RoleResponseDto resRole = new RoleResponseDto();
    resRole.setRoleName(roleName);
    resRole.setUserId(userId);
    return resRole;
  }

  @Override
  public boolean deleteUser(String userId) {
    userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND);
    });
    userRepository.deleteUserByUserId(userId);
    return true;
  }

  @Override
  public List<UpdateUserDetailResponseDto> searchUser(String searchValue) {
    log.info("Searching for: {}", searchValue);
    List<User> users = userRepository.findUserByStudentDetails_StudentCodeOrderByFullName(searchValue);
    if (users.isEmpty()) {
      log.info("No studentCode found for search value: {}", searchValue);
      users = userRepository.findUserByFullNameIsContainingIgnoreCaseOrderByFullName(searchValue);
        if (users.isEmpty()) {
          log.info("No fullName found for search value: {}", searchValue);
          users = userRepository.findUserByEmailIsContainingIgnoreCaseOrderByFullName(searchValue);
        }
    }
    List<UpdateUserDetailResponseDto> resUsers = new ArrayList<>();
    log.info("Start mapping...");
    for (User user : users) {
      resUsers.add(Mapper.map(user, UpdateUserDetailResponseDto.class));
    }
    log.info("Completed search for: {}", searchValue);
    return resUsers;
  }

  @Override
  public UpdateUserDetailResponseDto getUserDetails(String userId) {
    log.info("Finding userId: {}", userId);
    User user = userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND);
    });
    UpdateUserDetailResponseDto resUser = Mapper.map(user, UpdateUserDetailResponseDto.class);
    resUser = Mapper.map(user.getStudentDetails(), resUser);
    log.info("User found: {}", resUser);
    return resUser;
  }

  @Override
  public boolean activateUser(String userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND);
    });
    user.setIsActive(CheckBoolean.TRUE);
    userRepository.save(user);
    return true;
  }

  @Override
  public boolean deactivateUser(String userId) {
    User user = userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND);
    });
    user.setIsActive(CheckBoolean.FALSE);
    userRepository.save(user);
    return true;
  }

  @Override
  public boolean changePassword(String token, String newPassword) {
    String userId = passwordRequestService.validateToken(token);
    User user = userRepository.findById(userId).orElseThrow(() -> {
      log.info(MessageConstant.USER_NOT_FOUND);
      return new ResourceNotFoundException(MessageConstant.USER_NOT_FOUND);
    });
    user.setPassword(newPassword);
    userRepository.save(user);
    log.info("Password changed successfully for userId: {}", userId);
    return true;
  }
}
