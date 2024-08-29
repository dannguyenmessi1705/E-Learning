package com.didan.elearning.users.entity;

import com.didan.elearning.users.constants.CheckBoolean;
import com.didan.elearning.users.constants.GenderConstants;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
public class User extends SuperClass {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String userId;
  @Column
  private String username;
  @Column
  private String email;
  @Column
  private String password;
  @Column
  private String fullName;
  @Column
  private Date dateOfBirth;
  @Column
  private String address;
  @Column
  private String profilePicture;
  @Column
  private String phoneNumber;
  @Column
  private String gender;
  @Column
  private String isActive;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<UserRoles> roles;

  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<UserNotifications> notifications;

  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<ActivityLogs> activityLogs;

  @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<PasswordRequest> passwordRequests;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private StudentDetails studentDetails;

  @PrePersist
  public void setDefaultGender() {
    this.gender = GenderConstants.UNDERTERMINED;
    this.isActive = CheckBoolean.FALSE;
  }
}
