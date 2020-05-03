package ua.antonleliuk.medialibrary.user.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ua.antonleliuk.medialibrary.user.repository.UserRepository
import ua.antonleliuk.medialibrary.security.data.SecurityUser

/**
@author Anton Leliuk
 */
@Service
class UserService (private var userRepository: UserRepository) : UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username)
                .map { user -> SecurityUser(user) }
                .orElseThrow { throw UsernameNotFoundException("User with login $username not found") }
    }
}