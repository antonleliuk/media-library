package ua.antonleliuk.medialibrary.user.repository

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import ua.antonleliuk.medialibrary.user.domain.Role

/**
@author Anton Leliuk
 */
@Repository
interface RoleRepository : PagingAndSortingRepository<Role, Long> {
}