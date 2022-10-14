package com.login.presentation

import com.login.domain.models.LoginDomainState
import com.login.domain.models.LoginDomainStateFail
import com.login.domain.models.LoginDomainStateSuccess
import com.login.presentation.ui.UiState
import com.login.presentation.ui.UiStateSuccess
import javax.inject.Inject

class UiMapper @Inject constructor() {
    fun toUiState(oldUiState: UiState, loginDomainState: LoginDomainState): UiState {
        return when (loginDomainState) {
            is LoginDomainStateSuccess -> UiStateSuccess(true)
            is LoginDomainStateFail -> oldUiState
        }
    }
}