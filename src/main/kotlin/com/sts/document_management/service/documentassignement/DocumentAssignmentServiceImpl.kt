package com.sts.document_management.service.documentassignement

import com.sts.common.utils.Helper
import com.sts.document_management.constant.ObjectStatus
import com.sts.document_management.persistence.sql.model.DocumentAssignment
import com.sts.document_management.persistence.sql.repository.DocumentAssignmentRepository
import io.grpc.Status
import io.grpc.StatusException
import io.grpc.stub.StreamObserver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DocumentAssignmentServiceImpl : DocumentAssignmentService {

    @Autowired
    lateinit var documentAssignmentRepository: DocumentAssignmentRepository

    @Value("\${messages.document-assignment-service.already-assigned}")
    val alreadyAssignedMessage:String? = null

    @Value("\${codes.document-assignment-service.already-assigned}")
    val alreadyAssignedCode: Int? = null

    override fun save(documentAssignment: DocumentAssignment): DocumentAssignment? {
        if (documentAssignmentRepository.existsByDocumentIdAndAssigneeIdAndStatus(
                documentAssignment.documentId,
                documentAssignment.assigneeId,
                documentAssignment.status
            )
        ) {
            return null
        }
        return documentAssignmentRepository.save(documentAssignment)
    }

    override fun <T> save(
        documentAssignment: DocumentAssignment,
        responseObserver: StreamObserver<T>
    ): DocumentAssignment? {
        if (documentAssignmentRepository.existsByDocumentIdAndAssigneeIdAndStatus(
                documentAssignment.documentId,
                documentAssignment.assigneeId,
                documentAssignment.status
            )
        ) {
            val status = Status.NOT_FOUND.withDescription(
                Helper.concatenateErrorMessageAndCode(
                    alreadyAssignedMessage!!,
                    alreadyAssignedCode!!
                )
            )
            responseObserver.onError(StatusException(status))
            return null
        }
        return documentAssignmentRepository.save(documentAssignment)
    }


    override fun <T> findActiveDocumentAssignmentById(documentAssignmentId: String, responseObserver: StreamObserver<T>): DocumentAssignment? {
        val documentAssignment = documentAssignmentRepository.findById(documentAssignmentId)
        if (!documentAssignment.isPresent){
            return null
        }
        val fetchedDocumentAssignment = documentAssignment.get()
        if(fetchedDocumentAssignment.status != ObjectStatus.ACTIVE){
            return null
        }
        return fetchedDocumentAssignment
    }
}