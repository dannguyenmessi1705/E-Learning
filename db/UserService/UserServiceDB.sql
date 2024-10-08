-- MySQL Script generated by MySQL Workbench
-- Wed Aug 21 23:33:01 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema e_learning_user_service
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `e_learning_user_service` ;

-- -----------------------------------------------------
-- Schema e_learning_user_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `e_learning_user_service` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `e_learning_user_service` ;

-- -----------------------------------------------------
-- Table `e_learning_user_service`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_learning_user_service`.`users` ;

CREATE TABLE IF NOT EXISTS `e_learning_user_service`.`users` (
  `id` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `full_name` VARCHAR(255) NOT NULL,
  `date_of_birth` DATE NULL DEFAULT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `profile_picture` VARCHAR(255) NULL DEFAULT NULL,
  `phone_number` VARCHAR(255) NULL DEFAULT NULL,
  `gender` ENUM('MALE', 'FEMALE') NOT NULL,
  `is_active` ENUM('TRUE', 'FALSE') NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `e_learning_user_service`.`password_reset_request`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_learning_user_service`.`password_reset_request` ;

CREATE TABLE IF NOT EXISTS `e_learning_user_service`.`password_reset_request` (
  `id` VARCHAR(255) NOT NULL,
  `user_id` VARCHAR(255) NOT NULL,
  `token` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `expired_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `password_reset_request_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `password_reset_request_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `e_learning_user_service`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `e_learning_user_service`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_learning_user_service`.`roles` ;

CREATE TABLE IF NOT EXISTS `e_learning_user_service`.`roles` (
  `id` VARCHAR(255) NOT NULL,
  `role_name` ENUM('ADMIN', 'INSTRUCTOR', 'ASSISTANT', 'STUDENT', 'GUEST') NOT NULL DEFAULT 'GUEST',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `e_learning_user_service`.`student_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_learning_user_service`.`student_details` ;

CREATE TABLE IF NOT EXISTS `e_learning_user_service`.`student_details` (
  `id` VARCHAR(255) NOT NULL,
  `student_code` VARCHAR(255) NOT NULL,
  `major` VARCHAR(255) NOT NULL,
  `start_year` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `student_details_fk`
    FOREIGN KEY (`id`)
    REFERENCES `e_learning_user_service`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `e_learning_user_service`.`user_activity_logs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_learning_user_service`.`user_activity_logs` ;

CREATE TABLE IF NOT EXISTS `e_learning_user_service`.`user_activity_logs` (
  `id` VARCHAR(255) NOT NULL,
  `user_id` VARCHAR(255) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_activity_logs_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_activity_logs_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `e_learning_user_service`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `e_learning_user_service`.`user_notifications`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_learning_user_service`.`user_notifications` ;

CREATE TABLE IF NOT EXISTS `e_learning_user_service`.`user_notifications` (
  `id` VARCHAR(255) NOT NULL,
  `user_id` VARCHAR(255) NOT NULL,
  `message` VARCHAR(255) NOT NULL,
  `is_read` ENUM('TRUE', 'FALSE') NOT NULL DEFAULT 'FALSE',
  `created_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_notifications_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_notifications_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `e_learning_user_service`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `e_learning_user_service`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_learning_user_service`.`user_roles` ;

CREATE TABLE IF NOT EXISTS `e_learning_user_service`.`user_roles` (
  `user_id` VARCHAR(255) NOT NULL,
  `role_id` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `user_roles_fk_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `user_roles_fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `e_learning_user_service`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_roles_fk_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `e_learning_user_service`.`roles` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
