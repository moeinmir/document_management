package com.sts.document_management.service.documentattributehistory

import com.sts.document_management.persistence.sql.model.DocumentAttributeHistory

interface DocumentAttributeHistoryService {

    fun save(documentAttributeHistory: DocumentAttributeHistory): DocumentAttributeHistory

}