-- Create `roles` table
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` INT AUTO_INCREMENT PRIMARY KEY,
  `role_name` VARCHAR(50) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `created_by` VARCHAR(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` VARCHAR(50) DEFAULT NULL
);

-- Create `users` table
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `role_id` INT NOT NULL,
  `phone` VARCHAR(15),
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`role_id`) REFERENCES `roles`(`role_id`)
);

-- Create `appointment` table
CREATE TABLE IF NOT EXISTS `appointment` (
  `appointment_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `user_id` BIGINT NOT NULL,
  `appointment_date` DATE NOT NULL, -- Changed from TIMESTAMP to DATE to match form input
  `appointment_time` TIME NOT NULL, -- Added to store time
  `reason` VARCHAR(255) NOT NULL, -- This field can be repurposed for service
  `status` VARCHAR(20) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE
);

-- Create `contact_msg` table
CREATE TABLE IF NOT EXISTS `contact_msg` (
  `contact_id` INT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(10) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `subject` VARCHAR(100) NOT NULL,
  `message` VARCHAR(500) NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updated_by` VARCHAR(50) DEFAULT NULL
);
