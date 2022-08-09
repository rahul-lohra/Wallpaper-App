package com.rahul.notificationstest

sealed class State<T>

class Success<T> : State<T>()
class Fail<T>(th: Throwable) : State<T>()
class Loading<T>(progress: Int = 100) : State<T>()
