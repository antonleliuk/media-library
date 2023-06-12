package ua.antonleliuk.medialibrary.user.domain

import ua.antonleliuk.medialibrary.common.domain.NamedEntity
import javax.persistence.*

/**
@author Anton Leliuk
 */
@Entity
@Table(name = "role", schema = "public")
open class Role : NamedEntity() {
    @ManyToMany
    @JoinTable(
            name = "role_privilege",
            joinColumns = [JoinColumn(name = "id_role")],
            inverseJoinColumns = [JoinColumn(name = "id_privilege")])
    open val privileges: List<Privilege> = ArrayList()
}