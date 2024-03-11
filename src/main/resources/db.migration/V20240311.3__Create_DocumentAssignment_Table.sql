CREATE TABLE `document_assignment` (
                                       `id` varchar(255) NOT NULL,
                                       `assigned_at` datetime(6) DEFAULT NULL,
                                       `assigned_by_id` varchar(255) DEFAULT NULL,
                                       `assignee_id` varchar(255) DEFAULT NULL,
                                       `document_id` varchar(255) DEFAULT NULL,
                                       `status` enum('ACTIVE','INACTIVE','DELETED') DEFAULT NULL,
                                       `document_type_id` varchar(255) NOT NULL,
                                       PRIMARY KEY (`id`),
                                       KEY `FKhqrq06mqbnq4re0371wb1d1sx` (`document_type_id`),
                                       CONSTRAINT `FKhqrq06mqbnq4re0371wb1d1sx` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;