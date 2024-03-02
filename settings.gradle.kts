rootProject.name = "document_management"
pluginManagement {
    repositories {
        maven {
            url = uri("http://repository.neobank.digital:8081/repository/gradle-plugins/")
            isAllowInsecureProtocol = true
        }
        maven {
            url = uri("http://repository.neobank.digital:8081/repository/jcenter/")
            isAllowInsecureProtocol = true
        }

    }
}
