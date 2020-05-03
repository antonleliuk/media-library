package ua.antonleliuk.medialibrary.user.domain

import ua.antonleliuk.medialibrary.common.domain.NamedEntity
import javax.persistence.*

/**
@author Anton Leliuk
 */
@Entity
@Table(name = "role", schema = "public")
class Role : NamedEntity() {
    @ManyToMany
    @JoinTable(
            name = "role_privilege",
            joinColumns = [JoinColumn(name = "id_role")],
            inverseJoinColumns = [JoinColumn(name = "id_privilege")])
    val privileges: List<Privilege> = ArrayList()
}