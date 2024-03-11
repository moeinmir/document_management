package com.sts.document_management.service.documenttemplate

import com.sts.proto.document_management.DocumentTemplateServiceGrpc
import com.sts.proto.document_management.Documentmanagement
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.beans.factory.annotation.Autowired

@GRpcService
class DocumentTemplateServiceGrpc: DocumentTemplateServiceGrpc.DocumentTemplateServiceImplBase() {

    @Autowired
    lateinit var documentTemplateService: DocumentTemplateService;



    override fun fillTemplate(
        request: Documentmanagement.FillTemplateRequest,
        responseObserver: StreamObserver<Documentmanagement.FillTemplateResponse>
    ) {


        val filledTemplate = documentTemplateService.fillTemplate(
            request.pairsMap,
            request.documentAssignmentId,
            request.createdById,
            responseObserver
        )
        if (filledTemplate != null) {
            val responseBuilder = Documentmanagement.FillTemplateResponse.newBuilder()
            filledTemplate.forEach { key, value ->
                val stringValue = value?.toString() ?: ""
                responseBuilder.putPairs(key, stringValue)
            }
            responseObserver.onNext(responseBuilder.build())
            responseObserver.onCompleted()
        }
    }
}