package com.sts.document_management.persistence.sql.model

import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
data class DocumentTypeAttribute(


    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    val documentType: DocumentType,

    val createdById: Long,

    val createdByEmail: String,

    val createdAt: ZonedDateTime,

    val isDeleted: Boolean = false,

    val deletedById: Long? = null,

    val deletedByEmail: String? = null,

    val deletedAt: ZonedDateTime? = null,

    val attributeName: String,

    val attributeDescription: String? = null


) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

}