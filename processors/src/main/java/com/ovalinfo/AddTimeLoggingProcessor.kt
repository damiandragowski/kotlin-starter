package com.ovalinfo

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(AddTimeLoggingProcessor.GENERATED_DIR)
class AddTimeLoggingProcessor : AbstractProcessor() {
    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(AddTimeLogging::class.java).forEach {
            if (it.kind != ElementKind.METHOD) {
                processingEnv.messager.printMessage(javax.tools.Diagnostic.Kind.ERROR,"improper use of annotation")
                return false
            }

            addLogging(processingEnv.elementUtils.getPackageOf(it).toString())
        }

        return false
    }

    private fun addLogging(packageOfMethod: String) {
        val generatedSourcesRoot: String = processingEnv.options[GENERATED_DIR].orEmpty()
        if(generatedSourcesRoot.isEmpty()) {
            processingEnv.messager.printMessage(javax.tools.Diagnostic.Kind.ERROR,"Empty dir")
            return
        }

        val funcBuilder = FunSpec.builder("addLoggingToFuncation")
            .addModifiers(KModifier.PUBLIC)
            .addStatement(
                """println("Hello from Annotation")""".trimMargin()
            )
        val file = File(generatedSourcesRoot)
        file.mkdir()
        FileSpec.builder(packageOfMethod, "addLoggingGenerated").addFunction(funcBuilder.build()).build().writeTo(file)
    }

    companion object {
        const val GENERATED_DIR = "kapt.kotlin.generated"
    }
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(AddTimeLogging::class.java.canonicalName)
    }
}