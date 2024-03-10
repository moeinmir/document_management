package com.sts.document_management.service.documenttype

import com.sts.document_management.persistence.sql.model.DocumentType
import com.sts.proto.document_management.Documentmanagement
import io.grpc.stub.StreamObserver

interface DocumentTypeService {

    fun save(documentType: DocumentType):DocumentType

    fun <T> getDocumentType (id:String,responseObserver: StreamObserver<T>):DocumentType?

}