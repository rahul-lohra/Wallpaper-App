@file:Suppress("JAVA_MODULE_DOES_NOT_EXPORT_PACKAGE")

package com.rahul.apiresult_processor

import com.google.auto.service.AutoService
import com.rahul.apiresult_processor.annotations.ApiResultWrapper
import com.rahul.apiresult_processor.codegen.ApiResultKaptCodeBuilder
import com.squareup.kotlinpoet.DelicateKotlinPoetApi
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.asTypeName
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

//@AutoService(Processor::class)
class ApiResultKaptProcessor : AbstractProcessor() {
    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.RELEASE_11
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
                    .addType(ApiResultKaptCodeBuilder(fileName, modelData).build())
                    .build()
                    .writeTo(File(kaptKotlinGeneratedDir))
            }
        return true
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(ApiResultWrapper::class.java.canonicalName)
    }

    @OptIn(DelicateKotlinPoetApi::class)
    fun toModelData(element: Element): ModelData { //element=> com.rahul.wallpaper.annotationProcessor.UnsplashApiWrapper
        val packageName = processingEnv.elementUtils.getPackageOf(element).toString() //com.rahul.wallpaper.annotationProcessor
        val srcClassName = element.simpleName.toString() //UnsplashApiWrapper
        val attributeCompound = element.annotationMirrors.find { it.annotationType.asTypeName() == ApiResultWrapper::class.asTypeName() }!!

        val interfaceClass = attributeCompound.elementValues.values.first() //as Attribute.Class//=> com.rahul.wallpaper.feature.search.data.apis.unsplash.UnsplashApi.class
//        val t = val t = element.annotationMirrors.find { it.annotationType.asTypeName() == ApiResultWrapper::class.asTypeName() }!!
//val tempInterface = t.elementValues.values.first()
//val myMethod = (tempInterface.value as com.sun.tools.javac.code.Type.ClassType).tsym.enclosedElements.filter { it is MethodSymbol }[0]
//val methodName = myMethod.name.toString()
//val methodSymbol = (myMethod as MethodSymbol)
//val isContinuation = methodSymbol.params.find { it.name.toString() == "continuation" }
//val paramName = methodSymbol.params[0].name.toString()
//val paramType= methodSymbol.params[0].type.toString() // int
//methodSymbol.params.find { it.name.toString() == "continuation" }.type.typeArguments[0].modelType

        return ModelData(
            packageName,
            srcClassName,
            interfaceClass,
            emptyList()
//            element.enclosedElements
//                .filter { it.kind == ElementKind.METHOD }
//                .map { it as ExecutableElement }
        )
    }
}

/*
roundEnv.getElementsAnnotatedWith(ApiResultWrapper::class.java).first() = com.rahul.wallpaper.annotationProcessor.UnsplashApiWrapper

(element as Symbol.ClassSymbol).metadata.declarationAttributes[0].type.asTypeName() == ApiResultWrapper::class.asTypeName()
element as Symbol.ClassSymbol

(element as Symbol.ClassSymbol).metadata.declarationAttributes[0].values[0].snd => com.rahul.wallpaper.feature.search.data.apis.unsplash.UnsplashApi.class
* */