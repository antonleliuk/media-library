package ua.antonleliuk.medialibrary.user.domain

import ua.antonleliuk.medialibrary.common.domain.Identifier
import javax.persistence.*

/**
@author Anton Leliuk
 */
@Entity
@Table(name = "user", schema = "public")
class User : Identifier() {

    val username: String? = null
    val password: String? = null
    val enabled: Boolean = false
    val email: String? = null
    @Column(name = "full_name")
    val fullName: String? = null
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = [JoinColumn(name = "id_user")],
            inverseJoinColumns = [JoinColumn(name = "id_role")])
    val roles: List<Role> = ArrayList()
    val locked: Boolean = false
}