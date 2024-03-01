// Top-level build file where you can add configuration options common to all sub-projects/modules.

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.library") version "8.3.0" apply false
    id("io.github.gradle-nexus.publish-plugin") version "1.3.0"
}

group = "com.viliussutkus89"

nexusPublishing {
    repositories {
        sonatype()
    }
}
