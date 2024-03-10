package com.sts.document_management.service.documenttypeattribute

import com.sts.document_management.persistence.sql.model.DocumentTypeAttribute

interface DocumentTypeAttributeService {

    fun save(documentTypeAttribute: DocumentTypeAttribute):DocumentTypeAttribute;

}