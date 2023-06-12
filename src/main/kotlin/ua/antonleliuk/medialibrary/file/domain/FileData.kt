package ua.antonleliuk.medialibrary.file.domain

import ua.antonleliuk.medialibrary.common.domain.Identifier
import javax.persistence.Entity
import javax.persistence.Table

/**
@author Anton Leliuk
 */
@Entity
@Table(name = "file", schema = "public")
open class FileData: Identifier() {

    open val name: String = ""
    open val size: Int = -1
    open val path: String? = null
    open val content: ByteArray? = null
}