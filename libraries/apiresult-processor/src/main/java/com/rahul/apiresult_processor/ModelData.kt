@file:Suppress("JAVA_MODULE_DOES_NOT_EXPORT_PACKAGE")

package com.rahul.apiresult_processor

import com.sun.tools.javac.code.Attribute
import javax.lang.model.element.AnnotationValue
import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement

data class ModelData(
    val packageName: String,
    val className: String,
    val interfaceName: AnnotationValue,
    val listOfMethods: List<String>
)