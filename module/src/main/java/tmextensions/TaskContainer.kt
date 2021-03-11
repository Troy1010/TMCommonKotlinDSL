package tmextensions

import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider

fun TaskContainer.getByPath(vararg taskNames: String) =
        taskNames.map { this.getByPath(it) }

fun TaskContainer.registerOrderedPair(nameOfFirstTask: String, nameOfSecondTask: String): TaskProvider<Task> {
    return register("${nameOfFirstTask}_${nameOfSecondTask}") {
        dependsOn(getByName(nameOfFirstTask))
        finalizedBy(getByName(nameOfSecondTask))
        description = "$nameOfFirstTask & $nameOfSecondTask"
        group = "ordered task pair"
    }
}