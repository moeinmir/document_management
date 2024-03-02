import java.io.ByteArrayOutputStream

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.google.cloud.tools.jib") version "3.0.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    `maven-publish`
}

fun getGitHash(): String {
    val stdout = ByteArrayOutputStream()
    try {
        exec {
            commandLine("git", "rev-parse", "--short", "HEAD")
            standardOutput = stdout
        }
    } catch (e: Exception) {
        println("Error while executing git command: ${e.message}")
    }
    return stdout.toString().trim()
}

var group = "com.sts"

if((project.findProperty("group")!="") && (project.findProperty("group")!=null)){
    group = project.findProperty("group") as String
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenLocal()
    maven {
        url = uri("http://repository.neobank.digital:8081/repository/maven-ga/")
        isAllowInsecureProtocol = true
    }
    maven {
        setUrl("http://repository.neobank.digital:8081/repository/maven-releases/")
        isAllowInsecureProtocol = true
    }
    maven {
        url = uri("http://repository.neobank.digital:8081/repository/gradle-plugins/")
        isAllowInsecureProtocol = true
    }
    maven {
        url = uri("http://repository.neobank.digital:8081/repository/maven-central/")
        isAllowInsecureProtocol = true
    }
    maven {
        url = uri("http://repository.neobank.digital:8081/repository/jcenter/")
        isAllowInsecureProtocol = true
    }
    maven {
        url = uri("http://repository.neobank.digital:8081/repository/maven-snapshots/")
        isAllowInsecureProtocol = true
    }
}

val coroutinesVersion = "1.5.2"

dependencies {
    implementation("com.sts:common:0.0.114")
    implementation("redis.clients:jedis:4.3.1")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.1.0")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.1.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.data:spring-data-redis:3.2.0")
    implementation ("io.micrometer:micrometer-registry-prometheus")
    implementation("io.micrometer:micrometer-core")
    implementation("org.modelmapper:modelmapper:2.1.1")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("io.micrometer:micrometer-registry-prometheus")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

}

tasks.withType<Test> {
    useJUnitPlatform()
}


tasks.named<Jar>("jar") {
    enabled = false
}

tasks.withType<ProcessResources> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = group as String
            artifactId = project.name
            version = getGitHash()
            from(components["java"])
        }
    }

    repositories {
        mavenLocal()
        if (!project.hasProperty("excludeRemotePublishing")) {
            maven {
                url = uri("http://repository.neobank.digital:8081/repository/maven-ga/")
                isAllowInsecureProtocol = true
                credentials {
                    username = System.getenv("REGISTRY_USERNAME")
                    password = System.getenv("REGISTRY_PASSWORD")
                }
            }
        }
    }
}

jib {
    from {
        image = "repo.neobank.digital/rancher/openjdk:17-jdk-alpine"
    }
    to {

        image = "repository.neobank.digital:8444/${group}.${rootProject.name}:${getGitHash()}"
        auth {
            username = System.getenv("REGISTRY_USERNAME")
            password = System.getenv("REGISTRY_PASSWORD")
        }
    }
    container {
        ports = listOf("8484")
        volumes = listOf("/logs")
    }
    setAllowInsecureRegistries(true)
}

