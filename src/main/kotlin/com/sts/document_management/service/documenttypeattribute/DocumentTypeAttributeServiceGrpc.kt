package com.sts.document_management.service.documenttypeattribute

import com.sts.document_management.persistence.sql.model.DocumentTypeAttribute
import com.sts.document_management.service.documenttype.DocumentTypeService
import com.sts.document_management.utils.Mapper
import com.sts.proto.document_management.DocumentTypeAttributeServiceGrpc
import com.sts.proto.document_management.Documentmanagement
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired

@GRpcService
class DocumentTypeAttributeServiceGrpc(private val mapper: Mapper): DocumentTypeAttributeServiceGrpc.DocumentTypeAttributeServiceImplBase() {

    @Autowired
    lateinit var documentTypeService: DocumentTypeService;

    @Autowired
    lateinit var documentTypeAttributeService: DocumentTypeAttributeService

    override fun addDocumentTypeAttribute(
        request: Documentmanagement.AddDocumentTypeAttributeRequest,
        responseObserver: StreamObserver<Documentmanagement.AddDocumentTypeAttributeResponse>
    ) {
        val documentType = documentTypeService.getDocumentType(request.documentTypeId,responseObserver) ?: return
        val documentTypeAttribute = mapper.map(request,DocumentTypeAttribute::class)
        documentTypeAttribute.documentType = documentType
        documentTypeAttributeService.save(documentTypeAttribute,responseObserver)?:return
        val response = mapper.map(documentTypeAttribute,Documentmanagement.AddDocumentTypeAttributeResponse::class)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}