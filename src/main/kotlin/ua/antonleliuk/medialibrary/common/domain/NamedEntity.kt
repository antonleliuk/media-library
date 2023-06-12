package ua.antonleliuk.medialibrary.common.domain

import javax.persistence.MappedSuperclass

/**
@author Anton Leliuk
 */
@MappedSuperclass
open class NamedEntity : Identifier() {
    open val code: String? = null
    open val name: String? = null
}