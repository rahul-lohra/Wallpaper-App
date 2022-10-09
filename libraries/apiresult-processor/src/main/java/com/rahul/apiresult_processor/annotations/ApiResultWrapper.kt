package com.rahul.apiresult_processor.annotations

import kotlin.reflect.KClass

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class ApiResultWrapper(val arg1: KClass<*>)