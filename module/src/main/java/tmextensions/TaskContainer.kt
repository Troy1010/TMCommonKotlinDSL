package tmextensions

import org.gradle.api.tasks.TaskContainer

fun TaskContainer.getByPath(vararg taskNames: String) =
        taskNames.map { this.getByPath(it) }

fun TaskContainer.registerOrderedPair(name: String, nameOfFirstTask: String, nameOfSecondTask: String) {
    register(name) {
        dependsOn(getByName(nameOfFirstTask))
        finalizedBy(getByName(nameOfSecondTask))
        description = "$nameOfFirstTask & $nameOfSecondTask"
        group = "ordered task pair"
    }
}