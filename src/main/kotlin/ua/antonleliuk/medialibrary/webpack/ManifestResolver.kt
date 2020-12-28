package ua.antonleliuk.medialibrary.webpack

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * @author Anton Leliuk
 */
@Component
class ManifestResolver {
    lateinit var scriptAliases: Map<String, String>

    @Autowired
    lateinit var applicationContext: ApplicationContext

    @Autowired
    lateinit var objectMapper: ObjectMapper

    val manifestResourcePath: String = "classpath:static/dist/manifest.json"

    @PostConstruct
    fun initAliases() {
        val resource = applicationContext.getResource(manifestResourcePath)
        if (resource.exists()) {
            resource.inputStream.use {
                scriptAliases = objectMapper.readValue(it, object : TypeReference<Map<String, String>>() {})
            }
        }
    }

    fun getByAlias(alias: String) : String {
        return scriptAliases.getValue(alias)
    }

}