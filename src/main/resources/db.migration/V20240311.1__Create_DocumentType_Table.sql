CREATE TABLE `document_type` (
                                 `id` varchar(255) NOT NULL,
                                 `created_at` datetime(6) DEFAULT NULL,
                                 `created_by_id` varchar(255) DEFAULT NULL,
                                 `description` varchar(255) DEFAULT NULL,
                                 `document_group` varchar(255) DEFAULT NULL,
                                 `document_template_file_id` varchar(255) DEFAULT NULL,
                                 `is_mandatory` bit(1) NOT NULL,
                                 `name` varchar(255) DEFAULT NULL,
                                 `status` enum('ACTIVE','INACTIVE','DELETED') DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;