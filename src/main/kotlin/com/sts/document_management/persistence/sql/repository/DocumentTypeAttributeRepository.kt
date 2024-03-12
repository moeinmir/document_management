package com.sts.document_management.persistence.sql.repository



import com.sts.common.constant.ObjectStatus
import com.sts.document_management.persistence.sql.model.DocumentType
import com.sts.document_management.persistence.sql.model.DocumentTypeAttribute
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentTypeAttributeRepository:JpaRepository<DocumentTypeAttribute,String> {
    fun findByAttributeNameAndStatusAndDocumentType(attributeName: String, status:ObjectStatus,documentType:DocumentType):DocumentTypeAttribute?

    fun findByDocumentType(documentType:DocumentType):List<DocumentTypeAttribute>
}