package ua.antonleliuk.medialibrary.user.domain

import ua.antonleliuk.medialibrary.common.domain.Identifier
import javax.persistence.*

/**
@author Anton Leliuk
 */
@Entity
@Table(name = "user", schema = "public")
open class User : Identifier() {

    open val username: String? = null
    open val password: String? = null
    open val enabled: Boolean = false
    open val email: String? = null
    @Column(name = "full_name")
    open val fullName: String? = null
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = [JoinColumn(name = "id_user")],
            inverseJoinColumns = [JoinColumn(name = "id_role")])
    open val roles: List<Role> = ArrayList()
    open val locked: Boolean = false
}