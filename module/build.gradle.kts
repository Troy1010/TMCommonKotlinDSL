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
            implementationClass = "uyt.DemoPlugin"
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

dependencies {
    implementation("com.android.tools.build:gradle:4.1.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    implementation(localGroovy())
    implementation(gradleApi())
}
