import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.6.10"
	id("com.github.node-gradle.node") version "3.2.1"
	id("org.hidetake.ssh") version "2.10.1"
}

group = "ua.antonleliuk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

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
	implementation("com.github.csueiras.acme:spring-boot-starter-acme:0.1.0")
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
		jvmTarget = "17"
	}
}

tasks.withType<ProcessResources> {
	exclude("static/ui")
}

tasks.withType<Jar> {
	dependsOn("bundle")
}

node {
	// Version of node to use.
	version.set("16.14.2")

	// Version of npm to use.
//	npmVersion = "6.13.6"
	npmVersion.set("8.7.0")

	// Override the install command used by npmInstall
	npmInstallCommand.set("install")

	// Base URL for fetching node distributions (change if you have a mirror).
	// Or set to null if you want to add the repository on your own.
	distBaseUrl.set("https://nodejs.org/dist")

	// If true, it will download node using above parameters.
	// If false, it will try to use globally installed node.
	download.set(true)

	// Set the work directory for unpacking node
	workDir.set(file("${project.projectDir}/.cache/nodejs"))

	// Set the work directory for NPM
	npmWorkDir.set(file("${project.buildDir}/.cache/npm"))

	// Set the work directory where node_modules should be located
	nodeProjectDir.set(file("${project.projectDir}"))
}

//tasks.register<com.moowork.gradle.node.npm.NpmTask>("bundle") {
//	setArgs(listOf("run", "build-prod"))
//}


tasks.create(name = "pi-deploy") {
	group = "deploy"
	val piServer = remotes.create("pi") {
		host = "192.168.1.36"
		user = "pi"
		password = "raspberry"
	}

	doLast {
		ssh.run(delegateClosureOf<org.hidetake.groovy.ssh.core.RunHandler> {
			session(piServer, delegateClosureOf<org.hidetake.groovy.ssh.session.SessionHandler> {
				put(
					hashMapOf(
						"from" to "$buildDir/libs/media-library-$version.jar",
						"into" to "/home/pi/workspace/media-library.jar"
					)
				)
			})
		})
	}
}