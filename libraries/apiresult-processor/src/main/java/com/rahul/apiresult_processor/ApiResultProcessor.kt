package com.rahul.apiresult_processor

import com.rahul.apiresult_processor.annotations.ApiResultWrapper
import com.rahul.apiresult_processor.codegen.ApiResultCodeBuilder
import com.squareup.kotlinpoet.FileSpec
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

class ApiResultProcessor : AbstractProcessor() {
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment
    ): Boolean {
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        if (kaptKotlinGeneratedDir.isNullOrEmpty()) {
            processingEnv.messager.printMessage(
                Diagnostic.Kind.ERROR,
                "Can't find the target directory for generated Kotlin files."
            )
            return false
        }

        roundEnv.getElementsAnnotatedWith(ApiResultWrapper::class.java)
            .forEach {
                val modelData = toModelData(it)

                val fileName = "HelloWorld"
                FileSpec.builder(modelData.packageName, fileName)
                    .addType(ApiResultCodeBuilder(fileName, modelData).build())
                    .build()
                    .writeTo(File(kaptKotlinGeneratedDir))
            }
        return true
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(ApiResultWrapper::class.java.canonicalName)
    }

    fun toModelData(element: Element): ModelData {
        val packageName = processingEnv.elementUtils.getPackageOf(element).toString()
        val modelName = element.simpleName.toString()
        return ModelData(
            packageName,
            modelName,
            element.javaClass,
            element.enclosedElements.filter { it.kind == ElementKind.INTERFACE }.first().javaClass,
            element.enclosedElements
                .filter { it.kind == ElementKind.METHOD }
                .map { it as ExecutableElement }
        )
    }
}