plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("maven-publish")
}

// # Define a plugin to build, give it an id
gradlePlugin {
    plugins {
        create("keyForPlugin") {
            id = "TMPlugin"
            implementationClass = "com.example.tmcommonkotlindsl.TMPlugin"
        }
    }
}

publishing {
    publications {
        create("pluginPublication", MavenPublication::class) {
            from(project.components["java"])
            groupId = "tminus1010.tmcommonkotlin"
            artifactId = "tmcommonkotlindsl"
            version = "1.0.0"
        }
    }
}


val tempTask = tasks.register("easyCleanAssemble") {
    dependsOn(tasks.getByName("clean"))
    finalizedBy(tasks.getByName("assemble"))
    description = "assemble & publishToMavenLocal"
    group = "notmeanttobeused"
}
tasks.register("easyCleanAssemblePublish") {
    dependsOn(tempTask)
    finalizedBy(tasks.getByName("publishToMavenLocal"))
    description = "assemble & publishToMavenLocal & clean"
    group = "publishing"
}

dependencies {
    implementation("com.android.tools.build:gradle:4.1.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    implementation(localGroovy())
    implementation(gradleApi())
}
