package com.example.tmcommonkotlindsl

import org.gradle.api.Plugin
import org.gradle.api.Project
import tmextensions.*

open class TMPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            tasks.tryRegisterOrderedPair("clean", "assemble")
            tasks.tryRegisterOrderedPair("assemble", "publishToMavenLocal")
            tasks.register("easyPublishLocal") {
                group = "publishing"
                subprojects.map { it.tasks.getByName("assemble_publishToMavenLocal") }
                    .also { dependsOn(it) }
            }
        }
    }
}