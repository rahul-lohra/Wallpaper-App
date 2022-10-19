package com.search.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dispatchers.DispatcherQualifiers
import com.search.domain.FollowersUseCase
import com.search.domain.SearchUseCase
import com.search.domain.models.FollowDomainDataNotLoggedIn
import com.search.domain.models.FollowDomainDataPaginated
import com.search.ui.models.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

class SearchViewModel @Inject constructor(
    @Named(DispatcherQualifiers.IO) private val io: CoroutineDispatcher,
    @Named(DispatcherQualifiers.DEFAULT) private val default: CoroutineDispatcher,
    private val searchUseCase: SearchUseCase,
    private val followersUseCase: FollowersUseCase,
) : ViewModel() {

    private val mutableFollowing: Flow<FollowUiEntity> = MutableStateFlow(FollowUiEntityInitial)

    @OptIn(ExperimentalCoroutinesApi::class)
    val followersFlow: Flow<FollowUiEntity> = mutableFollowing.flatMapLatest {
        getFollowings()
    }
    private suspend fun getFollowings(): Flow<FollowUiEntity> {
        /*
         Cases
         1. user logs in
         2. user logs out
         */
        return followersUseCase.getFollowersData()
            .flowOn(default)
            .map { followDomainData ->
                when (followDomainData) {
                    is FollowDomainDataPaginated -> {
                        FollowUiEntitySuccess(followDomainData.data.map { followDomainEntity ->
                            (followDomainEntity.name)
                        })
                    }
                    is FollowDomainDataNotLoggedIn -> FollowUiEntityNotLoggedIn
                }
            }.catch {
                emit(FollowUiEntityError(it))
            }
    }

    private val mutableSharedFlow = MutableSharedFlow<Int>()
        .distinctUntilChanged()
        .flowOn(io)
        .onStart {
            emit(1)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    val photosFlow: Flow<PagingData<String>> = mutableSharedFlow
        .flowOn(io)
        .flatMapLatest {
            searchUseCase.getPagingData()
        }.catch {

            val errorState = LoadState.Error(RecomposeRequiredException(it))
            emit(
                PagingData.from(
                    emptyList(),
                    sourceLoadStates = LoadStates(
                        refresh = errorState,
                        prepend = errorState,
                        append = errorState
                    ),
                )
            )
        }
        .cachedIn(viewModelScope)
}