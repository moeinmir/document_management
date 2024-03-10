CREATE TABLE `document` (
                            `id` varchar(255) NOT NULL,
                            `data` longblob,
                            `file_name` varchar(255) DEFAULT NULL,
                            `file_type` varchar(255) DEFAULT NULL,
                            `object_status` enum('ACTIVE','DELETED') DEFAULT NULL,
                            `uploaded_at` datetime(6) DEFAULT NULL,
                            `uploaded_by_id` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;