package com.sts.document_management.service.documenttype

import com.sts.document_management.persistence.sql.model.DocumentType
import com.sts.document_management.utils.Mapper
import com.sts.proto.document_management.DocumentTypeServiceNewImplGrpc
import com.sts.proto.document_management.Documentmanagement
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired

@GRpcService
class DocumentTypeServiceGrpc(private val mapper: Mapper): DocumentTypeServiceNewImplGrpc.DocumentTypeServiceNewImplImplBase()  {

    @Autowired
    lateinit var documentTypeService:DocumentTypeService;

    override fun addDocumentType(
        request: Documentmanagement.AddDocumentTypeRequest,
        responseObserver: StreamObserver<Documentmanagement.AddDocumentTypeResponse>
    ) {
        val documentType = mapper.map(request,DocumentType::class)
        documentTypeService.save(documentType)
        val response = mapper.map(documentType,Documentmanagement.AddDocumentTypeResponse::class)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}

