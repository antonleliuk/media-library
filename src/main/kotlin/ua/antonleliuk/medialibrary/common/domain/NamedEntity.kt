package ua.antonleliuk.medialibrary.common.domain

import javax.persistence.MappedSuperclass

/**
@author Anton Leliuk
 */
@MappedSuperclass
open class NamedEntity : Identifier() {
    val code: String? = null
    val name: String? = null
}