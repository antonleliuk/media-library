package ua.antonleliuk.medialibrary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MediaLibraryApplication

fun main(args: Array<String>) {
	runApplication<MediaLibraryApplication>(*args)
}
