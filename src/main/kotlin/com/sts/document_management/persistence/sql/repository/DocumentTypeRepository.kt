package com.sts.document_management.persistence.sql.repository



import com.sts.common.constant.ObjectStatus
import com.sts.document_management.persistence.sql.model.DocumentType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentTypeRepository:JpaRepository<DocumentType,String> {

    fun findByNameAndDocumentGroupAndStatus(name:String,documentGroup:String,status:ObjectStatus):DocumentType?




}