For network security config
https://dev.to/enyason/how-to-fix-issue-of-ssl-handshake-exception-on-android-g6g

# Dagger ComponentProcessingStep was unable to process
> Task :features:search:kaptDebugKotlin FAILED
error: ComponentProcessingStep was unable to process 'com.search.di.components.SearchComponent' because 'retrofit2.Retrofit' could not be resolved.

Dependency trace:
=> element (INTERFACE): com.di.app.component.AppComponent
=> element (METHOD): retrofitClient()
=> type (EXECUTABLE method): ()retrofit2.Retrofit
=> type (ERROR return type): retrofit2.Retrofit

If type 'retrofit2.Retrofit' is a generated type, check above for compilation errors that may have prevented the type from being generated. Otherwise, ensure that type 'retrofit2.Retrofit' is on your classpath.

ComponentProcessingStep was unable to process 'com.search.di.components.SearchComponent' because 'retrofit2.Retrofit' could not be resolved.

Execution failed for task ':features:search:kaptDebugKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.gradle.internal.KaptWithoutKotlincTask$KaptExecutionWorkAction
> java.lang.reflect.InvocationTargetException (no error message)

* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

# Cannot expose Retrofit from base network module as it requires a base url 