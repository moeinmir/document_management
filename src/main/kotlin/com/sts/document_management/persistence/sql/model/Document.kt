package com.sts.document_management.persistence.sql.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.ZonedDateTime

@Entity
data class Document(

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    val documentType: DocumentType,

    @NotNull
    val fileName: String,

    @NotNull
    val fileType: String,

    @NotNull
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    val data: ByteArray,

    val uploadedById: Long,

    val uploadedByEmail: String,

    val uploadedAt: ZonedDateTime,

    val isDeleted: Boolean = false,

    val deletedById: Long? = null,

    val deletedByEmail: String? = null,

    val deletedAt: ZonedDateTime
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Document

        if (fileName != other.fileName) return false
        if (fileType != other.fileType) return false
        if (!data.contentEquals(other.data)) return false
        if (uploadedByEmail != other.uploadedByEmail) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileName.hashCode()
        result = 31 * result + fileType.hashCode()
        result = 31 * result + data.contentHashCode()
        result = 31 * result + uploadedByEmail.hashCode()
        result = 31 * result + (id?.hashCode() ?: 0)
        return result
    }
}