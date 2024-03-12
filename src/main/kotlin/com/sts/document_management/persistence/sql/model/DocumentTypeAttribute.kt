package com.sts.document_management.persistence.sql.model


import com.sts.common.constant.ObjectStatus
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
data class DocumentTypeAttribute(

    val createdById: String,
    val createdAt: ZonedDateTime,

    @Enumerated(EnumType.STRING)
    val status: ObjectStatus = ObjectStatus.ACTIVE,
    val attributeName: String,
    var attributeDescription: String? = null,
    val isMandatory: Boolean = false,
    var attributeType: String? = null,
    var convertRegex: String? = null
    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null;
    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    lateinit var documentType: DocumentType

}