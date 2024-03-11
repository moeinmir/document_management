CREATE TABLE `document_type` (
                                 `id` varchar(255) NOT NULL,
                                 `created_at` datetime(6) DEFAULT NULL,
                                 `created_by_id` varchar(255) DEFAULT NULL,
                                 `description` varchar(255) DEFAULT NULL,
                                 `name` varchar(255) DEFAULT NULL,
                                 `object_status` enum('ACTIVE','DELETED') DEFAULT NULL,
                                 `document_group` enum(
                                     'COMPANY',
                                     'REQUEST',
                                     'COMPANY_REPRESENTATIVE') DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;