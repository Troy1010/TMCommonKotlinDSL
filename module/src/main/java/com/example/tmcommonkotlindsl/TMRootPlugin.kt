package com.example.tmcommonkotlindsl

import org.gradle.api.Plugin
import org.gradle.api.Project
import tmextensions.*

open class TMRootPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            subprojects.forEach { it.tasks.tryRegisterOrderedPair("assemble", "publishToMavenLocal") }
            tasks.register("easyCleanPublishLocal") {
                group = "publishing"
                val allCleanTasks = project.getTasksByName("clean", true)
                val allAssemblePublishTasks = subprojects.map { it.tasks.getByName("assemble_publishToMavenLocal") }
                dependsOn(allCleanTasks, allAssemblePublishTasks)
                allAssemblePublishTasks.forEach { it.mustRunAfter(allCleanTasks) }
            }
        }
    }
}