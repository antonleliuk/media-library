package ua.antonleliuk.medialibrary.user.domain

import ua.antonleliuk.medialibrary.common.domain.NamedEntity
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
@author Anton Leliuk
 */
@Entity
@Table(name = "privilege", schema = "public")
class Privilege : NamedEntity() {

    @ManyToMany(mappedBy = "privileges")
    val roles: Set<Role>? = HashSet()

}