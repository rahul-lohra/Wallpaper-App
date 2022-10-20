package com.search.ui.models

sealed class FollowUiEntity
object FollowUiEntityInitial : FollowUiEntity()
class FollowUiEntitySuccess(val data: List<FollowListItemEntity>) : FollowUiEntity() {}
object FollowUiEntityNotLoggedIn : FollowUiEntity() {}
class FollowUiEntityError(val th: Throwable) : FollowUiEntity() {}

data class FollowListItemEntity(val url:String?, val name:String)