package com.sts.document_management.persistence.sql.model


import jakarta.persistence.*
import java.time.ZonedDateTime
import java.util.UUID

@Entity
data class DocumentAttributeHistory(

    @ManyToOne
    @JoinColumn(name = "document_type_attribute_id", nullable = false)
    val documentTypeAttribute: DocumentTypeAttribute,

    val assigneeId: String,

    val filledById: String,

    val filledAt: ZonedDateTime,

    val attributeValue: String
){
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null

}