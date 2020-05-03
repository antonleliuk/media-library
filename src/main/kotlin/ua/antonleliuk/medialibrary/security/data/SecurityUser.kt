package ua.antonleliuk.medialibrary.security.data

import org.springframework.security.core.userdetails.UserDetails
import ua.antonleliuk.medialibrary.user.domain.Privilege
import ua.antonleliuk.medialibrary.user.domain.User

/**
@author Anton Leliuk
 */
class SecurityUser(var user: User) : UserDetails {

    val privileges: MutableList<Privilege> = ArrayList()

    init {
        this.user.roles.forEach { r -> this.privileges.addAll(r.privileges) }
    }
    override fun getAuthorities(): Collection<Privilege> {
        return privileges
    }

    override fun isEnabled(): Boolean {
        return user.enabled
    }

    override fun getUsername(): String {
        return user.username!!
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return user.password!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return user.locked
    }
}