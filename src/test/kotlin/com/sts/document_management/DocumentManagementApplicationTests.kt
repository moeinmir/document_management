package com.sts.document_management


import org.junit.jupiter.api.Test
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

    @Test
    fun contextLoads() {
        println("some")
    }

}
