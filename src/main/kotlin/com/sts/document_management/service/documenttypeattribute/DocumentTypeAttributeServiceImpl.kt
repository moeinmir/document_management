package com.sts.document_management.service.documenttypeattribute

import com.sts.document_management.persistence.sql.model.DocumentTypeAttribute
import com.sts.document_management.persistence.sql.repository.DocumentTypeAttributeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DocumentTypeAttributeServiceImpl:DocumentTypeAttributeService {

    @Autowired
    lateinit var documentTypeAttributeRepository: DocumentTypeAttributeRepository;
    override fun save(documentTypeAttribute: DocumentTypeAttribute): DocumentTypeAttribute {
        return documentTypeAttributeRepository.save(documentTypeAttribute)
    }

}