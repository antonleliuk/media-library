import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.71"
	kotlin("plugin.spring") version "1.3.71"
	kotlin("plugin.jpa") version "1.3.71"
	id("com.google.cloud.tools.jib") version "2.1.0"
	id("com.github.node-gradle.node") version "2.2.3"
}

group = "ua.antonleliuk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-quartz")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.liquibase:liquibase-core")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

node {
	// Version of node to use.
	version = "13.7.0"

	// Version of npm to use.
	npmVersion = "6.13.6"

	// Override the install command used by npmInstall
	npmInstallCommand = "install"

	// Base URL for fetching node distributions (change if you have a mirror).
	// Or set to null if you want to add the repository on your own.
	distBaseUrl = "https://nodejs.org/dist"

	// If true, it will download node using above parameters.
	// If false, it will try to use globally installed node.
	download = true

	// Set the work directory for unpacking node
	workDir = file("${project.buildDir}/nodejs")

	// Set the work directory for NPM
	npmWorkDir = file("${project.buildDir}/npm")

	// Set the work directory where node_modules should be located
	nodeModulesDir = file("${project.projectDir}")
}

jib {
	from {
		image = "openjdk:alpine"
	}

	to {
		credHelper = "osxkeychain"
	}
}