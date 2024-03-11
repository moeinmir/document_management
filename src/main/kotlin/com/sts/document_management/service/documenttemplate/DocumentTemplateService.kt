package com.sts.document_management.service.documenttemplate
import io.grpc.stub.StreamObserver
import org.bson.Document

interface DocumentTemplateService {

        fun <T> fillTemplate(
                template: Map<String, String>,
                assignmentId: String,
                createdById:String,
                responseObserver: StreamObserver<T>
        ): Document?

        fun findTemplateByAssignmentId(assignmentId: String): Document?
}