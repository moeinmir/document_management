package com.sts.document_management.utils

import com.sts.document_management.constant.ObjectStatus
import com.sts.document_management.persistence.sql.model.DocumentAssignment
import com.sts.document_management.persistence.sql.model.DocumentType
import com.sts.document_management.persistence.sql.model.DocumentTypeAttribute
import com.sts.proto.document_management.Documentmanagement
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField


@Component
class MapperImpl : Mapper {
    override fun <T : Any, R : Any> map(source: T, destinationClass: KClass<R>): R {
        when (source::class to destinationClass) {
            Documentmanagement.AddDocumentTypeRequest::class to DocumentType::class -> {
                val request = source as Documentmanagement.AddDocumentTypeRequest
                val result = DocumentType(
                    name = request.name,
                    documentGroup = request.documentGroup,
                    createdById = request.createdById,
                    createdAt = ZonedDateTime.now(),
                    isMandatory = request.isMandatory
                )
                if (request.hasDescription()) result.description = request.description
                if (request.hasDocumentTemplateFileId()) result.documentTemplateFileId = request.documentTemplateFileId
                return result as R;
            }

            DocumentType::class to Documentmanagement.AddDocumentTypeResponse::class -> {
                val documentType = source as DocumentType
                val response = Documentmanagement.AddDocumentTypeResponse.newBuilder()
                response.setId(documentType.id)
                response.setName(documentType.name)
                documentType.description?.let { response.setDescription(it) }
                response.setDocumentGroup(documentType.documentGroup)
                response.setCreatedById(documentType.createdById)
                response.setCreatedAt(documentType.createdAt.toString())
                response.setStatus(documentType.status.toString())
                documentType.documentTemplateFileId?.let { response.setDocumentTemplateFileId(it) }
                response.setIsMandatory(documentType.isMandatory)
                return response.build() as R;
            }

            Documentmanagement.AddDocumentTypeAttributeRequest::class to DocumentTypeAttribute::class -> {
                val request = source as Documentmanagement.AddDocumentTypeAttributeRequest
                val result = DocumentTypeAttribute(
                    createdById = request.createdById,
                    createdAt = ZonedDateTime.now(),
                    attributeName = request.attributeName,
                    isMandatory = request.isMandatory
                )
                if (request.hasConvertRegex()) result.convertRegex = request.convertRegex
                if (request.hasAttributeType()) result.attributeType = request.attributeType
                if (request.hasAttributeDescription()) result.attributeDescription = request.attributeDescription
                return result as R;

            }

            DocumentTypeAttribute::class to Documentmanagement.AddDocumentTypeAttributeResponse::class -> {
                val documentTypeAttribute = source as DocumentTypeAttribute
                val response = Documentmanagement.AddDocumentTypeAttributeResponse.newBuilder();
                response.setId(documentTypeAttribute.id)
                response.setDocumentTypeId(documentTypeAttribute.documentType.id)
                response.setCreatedById(documentTypeAttribute.createdById)
                response.setCreatedAt(documentTypeAttribute.createdAt.toString())
                response.setStatus(documentTypeAttribute.status.toString())
                documentTypeAttribute.attributeDescription?.let { response.setAttributeDescription(it) }
                response.setIsMandatory(documentTypeAttribute.isMandatory)
                documentTypeAttribute.attributeType?.let { response.setAttributeType(it) }
                documentTypeAttribute.convertRegex?.let { response.setConvertRegex(it) }
                return response.build() as R
            }
            Documentmanagement.AssignDocumentRequest::class to DocumentAssignment::class ->{
                    val request = source as Documentmanagement.AssignDocumentRequest;
                    val result = DocumentAssignment(
                        documentId = request.documentId,
                        assignedById = request.assignedById,
                        assignedAt = ZonedDateTime.now(),
                        assigneeId = request.assigneeId
                    )

                    return result as R;
            }

            DocumentAssignment::class to Documentmanagement.AssignDocumentResponse::class -> {
                val documentAssignment = source as DocumentAssignment;
                val response = Documentmanagement.AssignDocumentResponse.newBuilder()
                response.setId(documentAssignment.id)
                response.setDocumentId(documentAssignment.documentId)
                response.setDocumentTypeId(documentAssignment.documentType.id)
                response.setAssignedById(documentAssignment.assignedById)
                response.setAssignedAt(documentAssignment.assignedAt.toString())
                response.setStatus(documentAssignment.status.toString())
                response.setAssigneeId(documentAssignment.assigneeId)
                return response.build() as R;
            }

            else -> {
                val destinationInstance = destinationClass.createInstance()
                val properties = source::class.declaredMemberProperties
                properties.forEach { property ->
                    val destinationProperty = destinationClass.memberProperties.find { it.name == property.name }
                    if (destinationProperty != null) {
                        destinationProperty.javaField?.let { field ->
                            field.isAccessible = true
                            field.set(destinationInstance, property.getter.call(source))
                        }
                    }
                }
                return destinationInstance
            }
        }
    }
}
