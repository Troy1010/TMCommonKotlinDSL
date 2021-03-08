package com.example.tmcommonkotlindsl

import org.gradle.api.Plugin
import org.gradle.api.Project

open class TMPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("easyPublishLocal") {
            description = "assemble & publishToMavenLocal"
            group = "publishing"
            doLast {
                dependsOnInOrder(project.tasks.getByPath("assemble", "publishToMavenLocal"))
            }
        }
    }
}