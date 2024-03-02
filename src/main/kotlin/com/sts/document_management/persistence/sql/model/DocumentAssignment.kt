package com.sts.document_management.persistence.sql.model

import com.sts.common.constant.RelatedObjectType
import jakarta.persistence.*

@Entity
data class DocumentAssignment(

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    val document: Document,

    val assignedById: Long,

    val assignedByEmail: String,

    val isDeleted: Boolean = false,

    val deletedById: Long?,

    val deletedByEmail: String?,

    val assigneeId: Long,

    val objectType: RelatedObjectType

    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}