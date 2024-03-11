CREATE TABLE `document_attribute_history` (
                                              `id` varchar(255) NOT NULL,
                                              `attribute_value` varchar(255) DEFAULT NULL,
                                              `filled_at` datetime(6) DEFAULT NULL,
                                              `filled_by_id` varchar(255) DEFAULT NULL,
                                              `document_assignment_id` varchar(255) NOT NULL,
                                              `document_type_attribute_id` varchar(255) NOT NULL,
                                              PRIMARY KEY (`id`),
                                              KEY `FK8b8wl52gj6t44dms8ihp59tfx` (`document_assignment_id`),
                                              KEY `FKdnb7r0bvp0h1n0n1t0q1tmkst` (`document_type_attribute_id`),
                                              CONSTRAINT `FK8b8wl52gj6t44dms8ihp59tfx` FOREIGN KEY (`document_assignment_id`) REFERENCES `document_assignment` (`id`),
                                              CONSTRAINT `FKdnb7r0bvp0h1n0n1t0q1tmkst` FOREIGN KEY (`document_type_attribute_id`) REFERENCES `document_type_attribute` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;