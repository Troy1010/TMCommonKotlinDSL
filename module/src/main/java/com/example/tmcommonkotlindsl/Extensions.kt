package com.example.tmcommonkotlindsl

import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer

fun Iterable<Task>.runInOrder() =
    pairwise().forEach { it.second.mustRunAfter(it.first) }

fun <T> Iterable<T>.pairwise(): Iterable<Pair<T, T>> =
    this.zip(this.drop(1)) { a, b -> Pair(a, b) }

fun TaskContainer.getByPath(vararg taskNames: String) =
    taskNames.map { this.getByPath(it) }

fun Task.dependsOnInOrder(tasks: Iterable<Task>) {
    this.dependsOn(tasks)
    tasks.runInOrder()
}