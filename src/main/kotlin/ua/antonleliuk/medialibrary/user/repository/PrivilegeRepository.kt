package ua.antonleliuk.medialibrary.user.repository

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import ua.antonleliuk.medialibrary.user.domain.Privilege

/**
@author Anton Leliuk
 */
@Repository
interface PrivilegeRepository : PagingAndSortingRepository<Privilege, Long> {
}