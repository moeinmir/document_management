package com.sts.document_management.service.documentassignement

import com.sts.common.utils.Helper


import com.sts.common.constant.ObjectStatus
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
    val alreadyAssignedMessage: String? = null

    @Value("\${codes.document-assignment-service.already-assigned}")
    val alreadyAssignedCode: Int? = null

    @Value("\${messages.document-assignment-service.assignment-not-found}")
    val assignmentNotFoundMessage: String? = null

    @Value("\${codes.document-assignment-service.assignment-not-found}")
    val assignmentNotFoundCode: Int? = null

    @Value("\${messages.document-assignment-service.assignment-not-active}")
    val assignmentNotActive: String? = null

    @Value("\${codes.document-assignment-service.assignment-not-active}")
    val assignmentNotActiveCode: Int? = null

    override fun save(documentAssignment: DocumentAssignment): DocumentAssignment? {
        documentAssignmentRepository.findByDocumentIdAndAssigneeIdAndStatus(
            documentAssignment.documentId,
            documentAssignment.assigneeId,
            documentAssignment.status
        )?.let { fetchedDocumentAssignment ->
            documentAssignment.id?.let {
                if (it != fetchedDocumentAssignment.id) {
                    return null
                }
            }
        }

        return documentAssignmentRepository.save(documentAssignment)
    }

    override fun <T> save(
        documentAssignment: DocumentAssignment,
        responseObserver: StreamObserver<T>
    ): DocumentAssignment? {
        documentAssignmentRepository.findByDocumentIdAndAssigneeIdAndStatus(
            documentAssignment.documentId,
            documentAssignment.assigneeId,
            documentAssignment.status
        )?.let { fetchedDocumentAssignment ->
            documentAssignment.id?.let {
                if (it != fetchedDocumentAssignment.id) {
                    val status = Status.NOT_FOUND.withDescription(
                        Helper.concatenateErrorMessageAndCode(
                            alreadyAssignedMessage!!,
                            alreadyAssignedCode!!
                        )
                    )
                    responseObserver.onError(StatusException(status))
                    return null
                }
            }
        }
        return documentAssignmentRepository.save(documentAssignment)
    }


    override fun <T> findActiveDocumentAssignmentById(
        documentAssignmentId: String,
        responseObserver: StreamObserver<T>
    ): DocumentAssignment? {
        val documentAssignment = documentAssignmentRepository.findById(documentAssignmentId)
        if (!documentAssignment.isPresent) {
            val status = Status.NOT_FOUND.withDescription(
                Helper.concatenateErrorMessageAndCode(
                    assignmentNotFoundMessage!!,
                    assignmentNotFoundCode!!
                )
            )
            responseObserver.onError(StatusException(status))
            return null
        }
        val fetchedDocumentAssignment = documentAssignment.get()
        if (fetchedDocumentAssignment.status != ObjectStatus.ACTIVE) {
            val status = Status.NOT_FOUND.withDescription(
                Helper.concatenateErrorMessageAndCode(
                    assignmentNotFoundMessage!!,
                    assignmentNotActiveCode!!
                )
            )
            responseObserver.onError(StatusException(status))
            return null
        }
        return fetchedDocumentAssignment
    }
}