package ua.antonleliuk.medialibrary.home

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView

/**
@author Anton Leliuk
 */
@Controller
@ResponseBody
@RequestMapping("/home")
class HomeController {

    @GetMapping
    fun index(): ModelAndView {
        return ModelAndView("home")
    }
}