package com.login.domain.models

sealed class LoginDomainState
object LoginDomainStateSuccess : LoginDomainState()
class LoginDomainStateFail(th: Throwable) : LoginDomainState()
