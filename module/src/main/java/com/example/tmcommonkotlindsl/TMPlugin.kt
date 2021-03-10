package com.example.tmcommonkotlindsl

import org.gradle.api.Plugin
import org.gradle.api.Project
import tmextensions.*

open class TMPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            tasks.registerOrderedPair("assemble", "publishToMavenLocal")
        }
    }
}