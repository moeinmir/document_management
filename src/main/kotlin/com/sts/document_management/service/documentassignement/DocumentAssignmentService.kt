package com.sts.document_management.service.documentassignement

import com.sts.document_management.persistence.sql.model.DocumentAssignment
import com.sts.document_management.persistence.sql.model.DocumentType

interface DocumentAssignmentService {
    fun save(documentAssignment: DocumentAssignment):DocumentAssignment
}