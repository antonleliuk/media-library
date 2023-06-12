package ua.antonleliuk.medialibrary.media.domain

import ua.antonleliuk.medialibrary.common.domain.Identifier
import javax.persistence.Entity
import javax.persistence.Table

/**
@author Anton Leliuk
 */
@Entity
@Table(name = "library", schema = "public")
open class Library : Identifier() {

    open val name: String = ""
    open val description: String? = null
}