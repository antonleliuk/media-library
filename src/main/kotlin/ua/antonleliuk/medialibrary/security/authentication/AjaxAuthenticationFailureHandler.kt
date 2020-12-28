package ua.antonleliuk.medialibrary.security.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.security.authentication.*
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import ua.antonleliuk.medialibrary.common.web.ErrorMessage
import ua.antonleliuk.medialibrary.common.web.ErrorMessageContainer
import ua.antonleliuk.medialibrary.common.web.SimpleResponse
import java.io.OutputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
@author Anton Leliuk
 */
class AjaxAuthenticationFailureHandler : AuthenticationFailureHandler {

    private val defaultMessage: String = "An unexpected error has occurred."

    @Autowired
    lateinit var objectMapper: ObjectMapper

    private val exceptionMapping: Map<Class<out Exception>, String> =
            mapOf(
                    AuthenticationServiceException::class.java to defaultMessage,
                    BadCredentialsException::class.java to "User with this username and password does not exist.",
                    CredentialsExpiredException::class.java to "No role assigned to the specified user.",
                    LockedException::class.java to "User account is temporarily locked.",
                    DisabledException::class.java to "User is disabled."
            )

    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException) {
        val errorResponse = SimpleResponse()
        errorResponse.errors = ErrorMessageContainer()
        errorResponse.errors!!.messages.add(ErrorMessage("login", exceptionMapping.getOrDefault(exception.javaClass, defaultMessage)))

        objectMapper.writeValue(response.outputStream, errorResponse)
        response.contentType = MediaType.APPLICATION_JSON_VALUE
    }
}