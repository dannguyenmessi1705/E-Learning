-- MySQL Script generated by MySQL Workbench
-- Thu Aug 22 23:55:41 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema e_learning_attendance_service
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `e_learning_attendance_service` ;

-- -----------------------------------------------------
-- Schema e_learning_attendance_service
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `e_learning_attendance_service` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `e_learning_attendance_service` ;

-- -----------------------------------------------------
-- Table `e_learning_attendance_service`.`attendances`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_learning_attendance_service`.`attendances` ;

CREATE TABLE IF NOT EXISTS `e_learning_attendance_service`.`attendances` (
  `id` VARCHAR(255) NOT NULL,
  `class_code` VARCHAR(255) NOT NULL,
  `course_code` VARCHAR(255) NOT NULL,
  `semester_code` VARCHAR(255) NOT NULL,
  `attendence_time` TIMESTAMP NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `e_learning_attendance_service`.`student_attends_records`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `e_learning_attendance_service`.`student_attends_records` ;

CREATE TABLE IF NOT EXISTS `e_learning_attendance_service`.`student_attends_records` (
  `id` VARCHAR(255) NOT NULL,
  `attendance_id` VARCHAR(255) NOT NULL,
  `student_code` VARCHAR(255) NOT NULL,
  `attend_at` TIMESTAMP NOT NULL,
  `status` ENUM('ABSENT', 'PRESENT', 'LATE') NOT NULL DEFAULT 'ABSENT',
  `created_at` TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `student_attends_records_attendance_id_foreign` (`attendance_id` ASC) VISIBLE,
  CONSTRAINT `student_attends_records_attendance_id_foreign`
    FOREIGN KEY (`attendance_id`)
    REFERENCES `e_learning_attendance_service`.`attendances` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
