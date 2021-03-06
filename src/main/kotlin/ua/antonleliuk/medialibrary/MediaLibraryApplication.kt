package ua.antonleliuk.medialibrary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = ["ua.antonleliuk.medialibrary.**.repository"])
@EntityScan(basePackages = ["ua.antonleliuk.medialibrary.**.domain"])
@SpringBootApplication
class MediaLibraryApplication

fun main(args: Array<String>) {
	runApplication<MediaLibraryApplication>(*args) {
		applicationStartup = BufferingApplicationStartup(10000)
	}
}
