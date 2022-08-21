package com.alwin.lib_processor

import com.alwin.lib_annotation.BindView
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Filer
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement

class BindingProcessor : AbstractProcessor() {
    var filer: Filer? = null

    override fun init(processingEnv: ProcessingEnvironment?) {
        println("ProcessingEnvironment init--------------------")
        super.init(processingEnv)
        filer = processingEnv?.filer
    }

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        println("BindingProcessor process---------------------------")
        roundEnv ?: return false
        for (element in roundEnv.rootElements) {
            val packageStr = element.enclosingElement.toString()
            val classStr = element.simpleName.toString()
            val className = ClassName.get(packageStr, classStr + "Binding")
            val constructorBuilder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassName.get(packageStr, classStr), "activity")
            var hasBinding = false;
            for (enclosedElement in element.enclosedElements) {
                if (enclosedElement.kind == ElementKind.FIELD) {
                    enclosedElement.getAnnotation(BindView::class.java)?.let {
                        hasBinding = true
                        constructorBuilder.addStatement(
                            "activity.$" + "N" + "= activity.findViewById($" + "L)",
                            enclosedElement.simpleName, it.id
                        )
                    }
                }
            }

            val builtClass = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addMethod(constructorBuilder.build())
                .build()

            if (hasBinding) {
                try {
                    JavaFile.builder(packageStr, builtClass)
                        .build().writeTo(filer)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return false
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(BindView::class.java.canonicalName)
    }
}