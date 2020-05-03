package ua.antonleliuk.medialibrary.user.repository

import org.springframework.data.repository.PagingAndSortingRepository
import ua.antonleliuk.medialibrary.user.domain.Privilege

/**
@author Anton Leliuk
 */
interface PrivilegeRepository : PagingAndSortingRepository<Privilege, Long> {
}