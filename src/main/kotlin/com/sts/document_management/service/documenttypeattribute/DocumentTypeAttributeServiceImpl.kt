package com.sts.document_management.service.documenttypeattribute

import com.sts.common.utils.Helper
import com.sts.document_management.persistence.sql.model.DocumentType
import com.sts.document_management.persistence.sql.model.DocumentTypeAttribute
import com.sts.document_management.persistence.sql.repository.DocumentTypeAttributeRepository
import io.grpc.Status
import io.grpc.StatusException
import io.grpc.stub.StreamObserver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DocumentTypeAttributeServiceImpl : DocumentTypeAttributeService {

    @Autowired
    lateinit var documentTypeAttributeRepository: DocumentTypeAttributeRepository;

    @Value("\${messages.document-attribute-service.already-exist}")
    val alreadyExistMessage: String? = null

    @Value("\${codes.document-attribute-service.already-exist}")
    val alreadyExistCode: Int? = null

    override fun save(documentTypeAttribute: DocumentTypeAttribute): DocumentTypeAttribute? {
        documentTypeAttributeRepository.findByAttributeNameAndStatusAndDocumentType(
            documentTypeAttribute.attributeName,
            documentTypeAttribute.status,
            documentTypeAttribute.documentType
        )?.let { fetchedDocumentTypeAttribute ->
            documentTypeAttribute.id?.let {
                if (it != fetchedDocumentTypeAttribute.id) {
                    return null
                }
            }
            return null
        }
        return documentTypeAttributeRepository.save(documentTypeAttribute)
    }

    override fun <T> save(
        documentTypeAttribute: DocumentTypeAttribute,
        responseObserver: StreamObserver<T>
    ): DocumentTypeAttribute? {
        documentTypeAttributeRepository.findByAttributeNameAndStatusAndDocumentType(
            documentTypeAttribute.attributeName,
            documentTypeAttribute.status,
            documentTypeAttribute.documentType
        )?.let { fetchedDocumentTypeAttribute ->
            documentTypeAttribute.id?.let {
                if (it != fetchedDocumentTypeAttribute.id) {
                    val status = Status.NOT_FOUND.withDescription(
                        Helper.concatenateErrorMessageAndCode(
                            alreadyExistMessage!!,
                            alreadyExistCode!!
                        )
                    )
                    responseObserver.onError(StatusException(status))
                    return null
                }
            }
        }
        return documentTypeAttributeRepository.save(documentTypeAttribute)
    }

    override fun findByDocumentType(documentType: DocumentType): List<DocumentTypeAttribute> {
        return documentTypeAttributeRepository.findByDocumentType(documentType)
    }

}