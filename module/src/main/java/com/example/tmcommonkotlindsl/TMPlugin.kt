package com.example.tmcommonkotlindsl

import org.gradle.api.Plugin
import org.gradle.api.Project
import tmextensions.*

open class TMPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            subprojects.forEach { it.tasks.registerOrderedPair("assemble", "publishToMavenLocal") }
            tasks.register("easyCleanPublishLocal") {
                group = "publishing"
                dependsOn(project.getTasksByName("clean", true))
                subprojects.forEach { finalizedBy(it.tasks.getByName("assemble_publishToMavenLocal")) }
            }
            tasks.register("easyPublishLocal") {
                group = "publishing"
                subprojects.forEach { finalizedBy(it.tasks.getByName("assemble_publishToMavenLocal")) }
            }
        }
    }
}