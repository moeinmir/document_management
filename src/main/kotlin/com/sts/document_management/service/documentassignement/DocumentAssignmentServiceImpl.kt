package com.sts.document_management.service.documentassignement

import com.sts.document_management.persistence.sql.model.DocumentAssignment
import com.sts.document_management.persistence.sql.repository.DocumentAssignmentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DocumentAssignmentServiceImpl: DocumentAssignmentService {

    @Autowired
    lateinit var documentAssignmentRepository: DocumentAssignmentRepository
    override fun save(documentAssignment: DocumentAssignment): DocumentAssignment {
        return documentAssignmentRepository.save(documentAssignment
        )
    }
}