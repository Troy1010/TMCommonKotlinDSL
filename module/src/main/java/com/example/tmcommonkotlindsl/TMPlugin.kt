package com.example.tmcommonkotlindsl

import org.gradle.api.Plugin
import org.gradle.api.Project

open class TMPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.afterEvaluate {
            tasks.register("easyPublishLocal") {
                dependsOn(tasks.getByName("assemble"))
                finalizedBy(tasks.getByName("publishToMavenLocal"))
                description = "assemble & publishToMavenLocal"
                group = "publishing"
            }
            tasks.register("easyPublishLocalThenClean") {
                dependsOn(tasks.named("easyPublishLocal"))
                finalizedBy(tasks.getByName("clean"))
                description = "assemble & publishToMavenLocal & clean"
                group = "publishing"
            }
        }
    }
}