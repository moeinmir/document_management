package com.sts.document_management.persistence.sql.model

import com.sts.common.constant.RelatedObjectType
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
data class DocumentType(

    val name:String,

    val description: String? = null,


    @Enumerated(EnumType.STRING)
    val objectType: RelatedObjectType? = null,

    val createdById: Long,

    val createdByEmail: String,

    val createdAt: ZonedDateTime,

    var isDeleted: Boolean = false,

    val updatedById: String? = null,

    val updatedByEmail: String? = null,

    val updatedAt: ZonedDateTime? = null


) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}