package com.sts.document_management.service.documenttype


import com.sts.common.utils.Helper
import com.sts.document_management.constant.ObjectStatus
import com.sts.document_management.persistence.sql.model.DocumentType
import com.sts.document_management.persistence.sql.repository.DocumentTypeRepository
import io.grpc.Status
import io.grpc.StatusException
import io.grpc.stub.StreamObserver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class DocumentTypeServiceImpl : DocumentTypeService {

    @Autowired
    lateinit var documentTypeRepository: DocumentTypeRepository

    @Value("\${messages.document-type-service.document-type-not-found}")
    val documentTypeNotFoundMessage: String? = null

    @Value("\${codes.document-type=service.document-type-not-found}")
    val documentTypeNotFoundCode: Int? = null

    @Value("\${messages.document-type-service.document-type-not-active}")
    val documentTypeNotActiveMessage: String? = null

    @Value("\${messages.document-type-service.already-exist}")
    val alreadyExistMessage: String? = null

    @Value("\${codes.document-type=service.already-exist}")
    val alreadyExistCode: Int? = null

    @Value("\${codes.document-type=service.document-type-not-active}")
    val documentTypeNotActiveCode: Int? = null

    override fun save(documentType: DocumentType): DocumentType? {

        if(documentTypeRepository.existsByNameAndDocumentGroupAndStatus(documentType.name,documentType.documentGroup,documentType.status)){
            return null
        }
        return documentTypeRepository.save(documentType)
    }

    override fun <T> save(documentType: DocumentType, responseObserver: StreamObserver<T>): DocumentType? {
        if(documentTypeRepository.existsByNameAndDocumentGroupAndStatus(documentType.name,documentType.documentGroup,documentType.status)){
            val status = Status.NOT_FOUND.withDescription(
                Helper.concatenateErrorMessageAndCode(
                    alreadyExistMessage!!,
                    alreadyExistCode!!
                )
            )
            responseObserver.onError(StatusException(status))
            return null
        }
        return documentTypeRepository.save(documentType)
    }


    override fun <T> getDocumentType(id: String, responseObserver: StreamObserver<T>): DocumentType? {
        val documentType = documentTypeRepository.findById(id)
        if (!documentType.isPresent) {
            val status = Status.NOT_FOUND.withDescription(
                Helper.concatenateErrorMessageAndCode(
                    documentTypeNotFoundMessage!!,
                    documentTypeNotFoundCode!!
                )
            )
            responseObserver.onError(StatusException(status))
            return null
        }
        val fetchedDocumentType = documentType.get()
        if (fetchedDocumentType.status != ObjectStatus.ACTIVE) {
            val status = Status.FAILED_PRECONDITION.withDescription(
                Helper.concatenateErrorMessageAndCode(
                    documentTypeNotActiveMessage!!,
                    documentTypeNotActiveCode!!
                )
            )
            responseObserver.onError(StatusException(status))
            return null
        }
        return fetchedDocumentType
    }
}

