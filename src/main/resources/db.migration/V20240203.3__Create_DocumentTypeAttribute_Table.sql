CREATE TABLE `document_type_attribute` (
                                           `id` varchar(255) NOT NULL,
                                           `attribute_description` varchar(255) DEFAULT NULL,
                                           `attribute_name` varchar(255) DEFAULT NULL,
                                           `attribute_type` varchar(255) DEFAULT NULL,
                                           `convert_regex` varchar(255) DEFAULT NULL,
                                           `created_at` datetime(6) DEFAULT NULL,
                                           `created_by_id` varchar(255) DEFAULT NULL,
                                           `is_mandatory` bit(1) NOT NULL,
                                           `object_status` enum('ACTIVE','DELETED') DEFAULT NULL,
                                           `document_type_id` varchar(255) NOT NULL,
                                           PRIMARY KEY (`id`),
                                           KEY `FK75q5qwqp89evf11iifama59in` (`document_type_id`),
                                           CONSTRAINT `FK75q5qwqp89evf11iifama59in` FOREIGN KEY (`document_type_id`) REFERENCES `document_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;