package com.rahul.apiresult_processor

import javax.lang.model.element.Element
import javax.lang.model.element.ExecutableElement

data class ModelData(
    val packageName: String,
    val className: String,
    val currentClass: Class<*>,
    val interfaceName: Class<*>,
    val listOfMethods: List<ExecutableElement>
)