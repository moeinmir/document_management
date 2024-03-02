package com.sts.document_management.persistence.sql.repository

import com.sts.document_management.persistence.sql.model.DocumentTypeAttribute
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentTypeAttributeRepository:JpaRepository<DocumentTypeAttribute,Long> {
}