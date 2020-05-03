package ua.antonleliuk.medialibrary.user.repository

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import ua.antonleliuk.medialibrary.user.domain.User
import java.util.*

/**
@author Anton Leliuk
 */
@Repository
interface UserRepository : PagingAndSortingRepository<User, Long> {

    fun findByUsername(username : String) : Optional<User>

}