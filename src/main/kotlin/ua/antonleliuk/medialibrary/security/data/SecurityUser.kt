package ua.antonleliuk.medialibrary.security.data

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ua.antonleliuk.medialibrary.user.domain.User
import kotlin.streams.toList

/**
@author Anton Leliuk
 */
class SecurityUser(user: User) : UserDetails {

    val privileges: MutableList<SimpleGrantedAuthority> = ArrayList()
    var _enabled: Boolean = false
    var _username: String = ""
    var _password: String = ""
    var _locked: Boolean = false

    init {
        user.roles.forEach { r -> this.privileges.addAll(r.privileges.stream().map { p -> SimpleGrantedAuthority("PRIV_${p.code}") }.toList()) }
        this._enabled = user.enabled
        this._username = user.username!!
        this._password = user.password!!
        this._locked = user.locked
    }

    override fun getAuthorities(): Collection<SimpleGrantedAuthority> {
        return privileges
    }

    override fun isEnabled(): Boolean {
        return _enabled
    }

    override fun getUsername(): String {
        return _username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return _password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return !_locked
    }
}