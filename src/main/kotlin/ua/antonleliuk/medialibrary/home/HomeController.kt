package ua.antonleliuk.medialibrary.home

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

/**
@author Anton Leliuk
 */
@RestController
@RequestMapping("/home")
class HomeController {

    @GetMapping
    fun index(): ModelAndView {
        return ModelAndView("home")
    }
}