package com.sts.document_management.service.documentassignement

import com.sts.document_management.persistence.sql.model.DocumentAssignment
import com.sts.document_management.persistence.sql.model.DocumentType
import io.grpc.stub.StreamObserver

interface DocumentAssignmentService {
    fun save(documentAssignment: DocumentAssignment):DocumentAssignment?

    fun <T> save(documentAssignment: DocumentAssignment,responseObserver: StreamObserver<T>):DocumentAssignment?

    fun <T> findActiveDocumentAssignmentById(documentAssignmentId: String,responseObserver: StreamObserver<T>):DocumentAssignment?



}