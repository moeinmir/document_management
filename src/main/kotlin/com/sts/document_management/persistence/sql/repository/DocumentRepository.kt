package com.sts.document_management.persistence.sql.repository

import com.sts.document_management.persistence.sql.model.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository:JpaRepository<Document,Long> {
}

