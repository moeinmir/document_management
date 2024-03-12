package com.sts.document_management




import com.sts.common.constant.ObjectStatus
import com.sts.document_management.persistence.sql.repository.DocumentTypeAttributeRepository
import com.sts.document_management.persistence.sql.repository.DocumentTypeRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@ActiveProfiles("local")
@SpringBootTest
class DocumentManagementApplicationTests {
//
//    @Autowired
//    lateinit var documentTypeServiceImpl: DocumentTypeServiceImpl
//
//    @Test
//    fun createDocumentType(){
//        documentTypeServiceImpl.createDocType()
//    }

    @Autowired
    lateinit var documentTypeRepository: DocumentTypeRepository
    @Autowired
    lateinit var documentAttributeRepository: DocumentTypeAttributeRepository
    @Test
    fun contextLoads() {
//        val doesExist = documentTypeRepository.existsByNameAndDocumentGroupAndStatus("firtdoctype","firstgroup",ObjectStatus.ACTIVE)

        val docType = documentTypeRepository.findById("b7fee4e0-3faf-40f9-b118-c5a428e15bdc")
        val doesExist = documentAttributeRepository.existsByAttributeNameAndStatusAndDocumentType("firstatt",ObjectStatus.ACTIVE, docType.get() )
        println("does exist")
        println(doesExist)
    }

}
