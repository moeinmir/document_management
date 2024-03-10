package com.sts.document_management.persistence.sql.model


import com.sts.document_management.constant.ObjectStatus
import jakarta.persistence.*
import java.time.ZonedDateTime

@Entity
data class DocumentAssignment(

    val documentId: String,

    val assignedById: String,

    val assignedAt: ZonedDateTime,

    @Enumerated(EnumType.STRING)
    val status: ObjectStatus = ObjectStatus.ACTIVE,

    val assigneeId: String
    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: String? = null;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    lateinit var documentType: DocumentType

}