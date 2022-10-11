package com.search.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dispatchers.DispatcherQualifiers
import com.search.domain.FollowDomainData
import com.search.domain.SearchUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Named

class SearchViewModel @Inject constructor(
    @Named(DispatcherQualifiers.IO) private val io: CoroutineDispatcher,
    @Named(DispatcherQualifiers.DEFAULT) private val default: CoroutineDispatcher,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    //TODO Rahul fix below line
    val followersFlow: Flow<FollowDomainData> = flowOf()
    private val mutableSharedFlow = MutableSharedFlow<Int>()
        .distinctUntilChanged()
        .flowOn(io)
        .onStart {
            emit(1)
        }

    //    val photosFlow: Flow<PagingData<String>> = searchUseCase.getPagingData().cachedIn(viewModelScope)
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

class RecomposeRequiredException(th:Throwable):Exception(th)