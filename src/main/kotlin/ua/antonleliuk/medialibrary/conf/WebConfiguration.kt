package ua.antonleliuk.medialibrary.conf

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
@author Anton Leliuk
 */
@Configuration
@EnableWebMvc
class WebConfiguration : WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addRedirectViewController("/", "/home")
        registry.addViewController("/login").setViewName("login")
    }

}