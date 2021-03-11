package tmextensions

import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider

fun TaskContainer.getByPath(vararg taskNames: String) =
    taskNames.map { this.getByPath(it) }

fun TaskContainer.tryRegisterOrderedPair(
    nameOfFirstTask: String,
    nameOfSecondTask: String
): TaskProvider<Task> {
    return try {
        register("${nameOfFirstTask}_${nameOfSecondTask}") {
            dependsOn(getByName(nameOfFirstTask))
            finalizedBy(getByName(nameOfSecondTask))
            description = "$nameOfFirstTask & $nameOfSecondTask"
            group = "ordered task pair"
        }
    } catch (e: Throwable) {
        named("${nameOfFirstTask}_${nameOfSecondTask}")
    }
}