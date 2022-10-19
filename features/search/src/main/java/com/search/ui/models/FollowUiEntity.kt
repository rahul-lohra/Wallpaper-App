package com.search.ui.models

sealed class FollowUiEntity
object FollowUiEntityInitial : FollowUiEntity()
class FollowUiEntitySuccess(val data: List<String>) : FollowUiEntity() {}
object FollowUiEntityNotLoggedIn : FollowUiEntity() {}
class FollowUiEntityError(val th: Throwable) : FollowUiEntity() {}