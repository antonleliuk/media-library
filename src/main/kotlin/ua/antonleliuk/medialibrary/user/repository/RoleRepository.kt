package ua.antonleliuk.medialibrary.user.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ua.antonleliuk.medialibrary.user.domain.Role

/**
@author Anton Leliuk
 */
interface RoleRepository : PagingAndSortingRepository<Role, Long> {
}