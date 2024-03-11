package com.sts.document_management.persistence.sql.model


import com.sts.document_management.constant.ObjectStatus
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
data class DocumentType(

    val name:String,

    var description: String? = null,

    val documentGroup: String,

    val createdById: String,

    val createdAt: ZonedDateTime,

    @Enumerated(EnumType.STRING)
    val status: ObjectStatus = ObjectStatus.ACTIVE,

    var documentTemplateFileId: String? = null,

    val isMandatory: Boolean
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null
}