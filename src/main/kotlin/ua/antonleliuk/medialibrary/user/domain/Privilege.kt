package ua.antonleliuk.medialibrary.user.domain

import org.springframework.security.core.GrantedAuthority
import ua.antonleliuk.medialibrary.common.domain.NamedEntity
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
@author Anton Leliuk
 */
@Entity
@Table(name = "priv", schema = "public")
class Privilege : NamedEntity(), GrantedAuthority {

    @ManyToMany(mappedBy = "privileges")
    val roles: Set<Role>? = HashSet()

    override fun getAuthority(): String {
        return "PRIV_$code"
    }
}