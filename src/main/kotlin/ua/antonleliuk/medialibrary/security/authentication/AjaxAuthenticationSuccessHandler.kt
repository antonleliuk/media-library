package ua.antonleliuk.medialibrary.security.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import ua.antonleliuk.medialibrary.common.web.SimpleResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
@author Anton Leliuk
 */
class AjaxAuthenticationSuccessHandler(private val redirectUrl: String) : AuthenticationSuccessHandler {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        val successResponse = SimpleResponse()
        successResponse.redirectUrl = redirectUrl

        objectMapper.writeValue(response.outputStream, successResponse)
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.OK.value()
    }
}