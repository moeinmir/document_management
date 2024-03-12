package com.sts.document_management.service.documentassignement

import com.sts.document_management.persistence.sql.model.DocumentAssignment
import com.sts.document_management.service.documenttype.DocumentTypeService
import com.sts.proto.document_management.DocumentAssignmentServiceGrpc
import com.sts.proto.document_management.Documentmanagement
import io.grpc.stub.StreamObserver
import com.sts.document_management.utils.Mapper
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired

@GRpcService
class DocumentAssignmentServiceGrpc(private val mapper: Mapper): DocumentAssignmentServiceGrpc.DocumentAssignmentServiceImplBase() {


    @Autowired
    lateinit var documentTypeService: DocumentTypeService;

    @Autowired
    lateinit var documentAssignmentService: DocumentAssignmentService;

    override fun assignDocument(
        request: Documentmanagement.AssignDocumentRequest,
        responseObserver: StreamObserver<Documentmanagement.AssignDocumentResponse>
    ) {
        val documentType = documentTypeService.getDocumentType(request.documentTypeId,responseObserver) ?: return
        val documentAssignment = mapper.map(request,DocumentAssignment::class)
        documentAssignment.documentType = documentType
        documentAssignmentService.save(documentAssignment,responseObserver)?:return
        val response = mapper.map(documentAssignment,Documentmanagement.AssignDocumentResponse::class)
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }
}