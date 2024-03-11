package com.sts.document_management.service.documenttypeattribute

import com.sts.document_management.persistence.sql.model.DocumentType
import com.sts.document_management.persistence.sql.model.DocumentTypeAttribute
import io.grpc.stub.StreamObserver

interface DocumentTypeAttributeService {

    fun save(documentTypeAttribute: DocumentTypeAttribute):DocumentTypeAttribute?;

    fun <T> save(documentTypeAttribute: DocumentTypeAttribute, responseObserver: StreamObserver<T>): DocumentTypeAttribute?

    fun findByDocumentType(documentType:DocumentType):List<DocumentTypeAttribute>

}