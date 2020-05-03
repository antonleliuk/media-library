package ua.antonleliuk.medialibrary.common.web

/**
@author Anton Leliuk
 */
class ErrorMessage(val code: String?, var message: String) {

    constructor(message: String) : this(null, message)
}