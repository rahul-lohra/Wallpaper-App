package com.search.domain.models

sealed class FollowDomainData
class FollowDomainDataPaginated(val data: List<FollowDomainEntity>) : FollowDomainData()
object FollowDomainDataNotLoggedIn : FollowDomainData()

data class FollowDomainEntity(val name: String, val url: String?)