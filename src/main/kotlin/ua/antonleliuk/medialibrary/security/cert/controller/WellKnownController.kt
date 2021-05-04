package ua.antonleliuk.medialibrary.security.cert.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
@author Anton Leliuk
 */
@RestController
@RequestMapping(".well-known/acme-challenge")
class WellKnownController {

    @GetMapping("/GVgprKTR_kdwHb0NC5RTHuQxclPis__-Ggz6vRpjeuM")
    fun getToken(): String {
        return "GVgprKTR_kdwHb0NC5RTHuQxclPis__-Ggz6vRpjeuM.ULAyvqYKD4s5ZEgHPdlWh20TXMIumG_8HAXM9gfsN9c"
    }
}