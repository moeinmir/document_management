package com.sts.document_management.service.documenttemplate

import com.sts.document_management.persistence.sql.model.DocumentAttributeHistory
import com.sts.document_management.persistence.sql.model.DocumentTypeAttribute
import com.sts.document_management.service.documentassignement.DocumentAssignmentService
import com.sts.document_management.service.documentattributehistory.DocumentAttributeHistoryService
import com.sts.document_management.service.documenttypeattribute.DocumentTypeAttributeService
import io.grpc.Status
import io.grpc.stub.StreamObserver
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.bson.Document
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class DocumentTemplateServiceImpl:DocumentTemplateService {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    @Autowired
    lateinit var documentTypeAttributeService: DocumentTypeAttributeService;

    @Autowired
    lateinit var documentAssignmentService: DocumentAssignmentService;

    @Autowired
    lateinit var documentAttributeHistoryService: DocumentAttributeHistoryService;

    override fun  <T> fillTemplate(template: Map<String, String>, assignmentId: String,createdById:String, responseObserver: StreamObserver<T>):Document? {
        val document = Document(template)
//        val document = Document()
        val documentAssignment = documentAssignmentService.findActiveDocumentAssignmentById(assignmentId,responseObserver)?:return null
        val documentType = documentAssignment.documentType
        val documentAttributes = documentTypeAttributeService.findByDocumentType(documentType)

        for ((key, value) in template) {
            val attribute = documentAttributes.find { it.id == key }
            if (attribute != null) {
                val documentAttributeHistory = DocumentAttributeHistory(
                    documentAssignment = documentAssignment,
                    documentTypeAttribute = attribute!!,
                    filledAt = ZonedDateTime.now(),
                    filledById = createdById,
                    attributeValue = value
                )
                documentAttributeHistoryService.save(documentAttributeHistory)
                document[attribute.attributeName] = value
            } else {
                responseObserver.onError(
                    Status.INVALID_ARGUMENT
                    .withDescription("Attribute with ID $key not found")
                    .asRuntimeException())
                return null
            }
        }
        document["assignmentId"] = assignmentId
        document["createdById"] = createdById
        val query = Query(Criteria.where("assignmentId").`is`(assignmentId))
        val update = Update().set("template", document)
        mongoTemplate.upsert(query, update, "templates")
        return document;
    }

    override fun findTemplateByAssignmentId(assignmentId: String): Document? {
        val query = Query(Criteria.where("assignmentId").`is`(assignmentId))
        return mongoTemplate.findOne(query, Document::class.java, "templates")
    }

}