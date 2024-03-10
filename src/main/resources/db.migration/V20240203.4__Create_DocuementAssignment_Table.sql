CREATE TABLE `document_assignment` (
                                       `id` varchar(255) NOT NULL,
                                       `assigned_at` datetime(6) DEFAULT NULL,
                                       `assigned_by_id` varchar(255) DEFAULT NULL,
                                       `assignee_id` varchar(255) DEFAULT NULL,
                                       `object_status` enum('ACTIVE','DELETED') DEFAULT NULL,
                                       `document_id` varchar(255) NOT NULL,
                                       `document_type_id` varchar(255) NOT NULL,
                                       PRIMARY KEY (`id`),
                                       KEY `FK7ngg5prsmt12ffkm17l3mqv10` (`document_id`),
                                       KEY `FKhqrq06mqbnq4re0371wb1d1sx` (`document_type_id`),
                                       CONSTRAINT `FK7ngg5prsmt12ffkm17l3mqv10` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`),
                                       CONSTRAINT `FKhqrq06mqbnq4re0371wb1d1sx` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;