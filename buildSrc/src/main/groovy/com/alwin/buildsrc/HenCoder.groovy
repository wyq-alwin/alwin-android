package com.alwin.buildsrc

import org.gradle.api.Plugin
import org.gradle.api.Project

class HenCoder implements Plugin<Project> {

    @Override
    void apply(Project target) {
        def extension = target.extensions.create('hencoder',HenCoderExtension)

        target.afterEvaluate {
            println "Hello ${extension.name}"
        }
    }
}

class HenCoderExtension {
    def name = "HenCoderExtension init name"
}
