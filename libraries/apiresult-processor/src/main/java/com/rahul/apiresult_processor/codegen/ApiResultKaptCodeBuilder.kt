package com.rahul.apiresult_processor.codegen

import com.rahul.apiresult_processor.ModelData
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import javax.lang.model.element.ExecutableElement

class ApiResultKaptCodeBuilder(private val adapterName: String, private val modelData: ModelData) {

    fun build(): TypeSpec {
        val typeSpec = TypeSpec.classBuilder(adapterName)
            .primaryConstructor(
                FunSpec
                    .builder("")
//                    .addParameter("api", modelData.interfaceName)
                    .build()
            )
//            .addSuperinterface(modelData.interfaceName)


//        modelData.listOfMethods
//            .forEach {
//                addFunctions(typeSpec, it)
//            }

        return typeSpec.build()
    }

    private fun addFunctions(typeSpecBuilder: TypeSpec.Builder, method: ExecutableElement) {
        val functionBuilder = FunSpec
            .builder(method.simpleName.toString())
            .addModifiers(KModifier.PUBLIC)

        method.parameters.forEach {
            functionBuilder.addParameter(it.simpleName.toString(), it.javaClass)
        }

        method.annotationMirrors.forEach {
            functionBuilder.addAnnotation(it.javaClass)
        }

        typeSpecBuilder.addFunction(functionBuilder.build())
    }
}