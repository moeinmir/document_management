package com.sts.document_management.service.documentattributehistory

import com.sts.document_management.persistence.sql.model.DocumentAttributeHistory
import com.sts.document_management.persistence.sql.repository.DocumentAttributeHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DocumentAttributeHistoryServiceImpl:DocumentAttributeHistoryService {

    @Autowired
    lateinit var documentAttributeHistoryRepository: DocumentAttributeHistoryRepository

    override fun save(documentAttributeHistory: DocumentAttributeHistory): DocumentAttributeHistory{
        return documentAttributeHistoryRepository.save(documentAttributeHistory)
    }

}