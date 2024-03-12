package com.sts.document_management.persistence.sql.repository



import com.sts.common.constant.ObjectStatus
import com.sts.document_management.persistence.sql.model.DocumentAssignment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface DocumentAssignmentRepository:JpaRepository<DocumentAssignment,String> {


    fun findByDocumentIdAndAssigneeIdAndStatus(documentId:String, assigneeId: String, status:ObjectStatus):DocumentAssignment?


}