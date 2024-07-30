-- Insert data into `roles` table
INSERT INTO `roles` (`role_name`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    ('Admin', CURRENT_TIMESTAMP, 'system', NULL, NULL),
    ('User', CURRENT_TIMESTAMP, 'system', NULL, NULL),
    ('Manager', CURRENT_TIMESTAMP, 'system', NULL, NULL);

-- Insert data into `users` table
-- Make sure the `role_id` values match those in the `roles` table
INSERT INTO `users` (`username`, `password`, `email`, `role_id`, `phone`, `first_name`, `last_name`, `created_at`, `updated_at`)
VALUES
    ('admin', '$2y$10$gHpYt7Qbot1xJloGyhZqPeL85TNddoDsf7S3gtaoFoC6X.6RxBGN6', 'admin@example.com', 1, '1234567890', 'Admin', 'User', CURRENT_TIMESTAMP, NULL),
    ('user1', '$2y$10$gHpYt7Qbot1xJloGyhZqPeL85TNddoDsf7S3gtaoFoC6X.6RxBGN6', 'user1@example.com', 2, '0987654321', 'Regular', 'User', CURRENT_TIMESTAMP, NULL),
    ('user2', '$2y$10$gHpYt7Qbot1xJloGyhZqPeL85TNddoDsf7S3gtaoFoC6X.6RxBGN6', 'user2@example.com', 3, '9999999999', 'Regular', 'User', CURRENT_TIMESTAMP, NULL),
    ('manager_user', '$2y$10$gHpYt7Qbot1xJloGyhZqPeL85TNddoDsf7S3gtaoFoC6X.6RxBGN6', 'manager@example.com', 3, '5555555555', 'Manager', 'User', CURRENT_TIMESTAMP, NULL);

-- Insert data into `appointment` table
-- Ensure `user_id` values correspond to IDs from the `users` table
INSERT INTO `appointment` (`user_id`, `appointment_date`, `appointment_time`, `reason`, `status`, `created_at`, `updated_at`)
VALUES
    (1, '2024-07-30', '10:00:00', 'Consultation', 'Pending', CURRENT_TIMESTAMP, NULL),
    (2, '2024-08-01', '14:00:00', 'Follow-Up', 'Done', CURRENT_TIMESTAMP, NULL),
    (3, '2024-08-05', '09:30:00', 'Check-Up', 'Pending', CURRENT_TIMESTAMP, NULL),
    (2, '2024-08-10', '11:00:00', 'Consultation', 'Pending', CURRENT_TIMESTAMP, NULL);

-- Insert data into `contact_msg` table
INSERT INTO `contact_msg` (`name`, `phone`, `email`, `subject`, `message`, `status`, `created_at`, `updated_at`, `updated_by`)
VALUES
    ('John Doe', '1234567890', 'john.doe@example.com', 'Inquiry', 'I have a question about your services.', 'New', CURRENT_TIMESTAMP, NULL, NULL),
    ('Jane Smith', '0987654321', 'jane.smith@example.com', 'Feedback', 'Great service, keep it up!', 'Reviewed', CURRENT_TIMESTAMP, NULL, NULL),
    ('Alice Johnson', '5555555555', 'alice.johnson@example.com', 'Complaint', 'I had an issue with my last appointment.', 'Resolved', CURRENT_TIMESTAMP, NULL, 'system');
