package ua.antonleliuk.medialibrary.common.domain

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
@author Anton Leliuk
 */
@MappedSuperclass
open class Identifier {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) open val id: Long? = null
}