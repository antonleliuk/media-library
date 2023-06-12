package ua.antonleliuk.medialibrary.security.cert.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/**
@author Anton Leliuk
 */
@RestController
class WellKnownController {

//    @GetMapping("/.well-known/acme-challenge/GVgprKTR_kdwHb0NC5RTHuQxclPis__-Ggz6vRpjeuM")
//    @GetMapping("/.well-known/acme-challenge/{token}")
    fun getToken(@PathVariable token: String): String {
        return token
    }
}