/*
 * OutputRedirector-Android - Redirect stdout and stderr to file
 *
 * Copyright (c) 2020, 2024 ViliusSutkus89.com
 *
 * OutputRedirector-Android is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3 as
 * published by the Free Software Foundation.
 *
 * OutputRedirector-Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

plugins {
    id("com.android.library")
    id("maven-publish")
    id("signing")
}

group = rootProject.group
version = "1.1.1"

android {
    namespace = "com.viliussutkus89.android.outputredirector"
    compileSdk = 34

    defaultConfig {
        minSdk = 16
        externalNativeBuild.cmake.arguments.add("-DANDROID_STL=none")
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
    externalNativeBuild.cmake {
        path = File("src/main/cpp/CMakeLists.txt")
        version = "3.22.1"
    }
    ndkVersion = "23.2.8568313"
}

dependencies {
    implementation("androidx.annotation:annotation:1.7.1")
}

publishing {
    publications {
        create<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
            }

            pom {
                name = rootProject.name
                description.set("Redirect stdout and stderr to file")
                url.set("https://github.com/ViliusSutkus89/OutputRedirector-Android")
                inceptionYear.set("2020")
                developers {
                    developer {
                        id.set("ViliusSutkus89")
                        name.set("Vilius Sutkus '89")
                        email.set("Vilius@ViliusSutkus89")
                    }
                }
                scm {
                    url.set("https://github.com/ViliusSutkus89/OutputRedirector-Android")
                    connection.set("https://github.com/ViliusSutkus89/OutputRedirector-Android.git")
                }
                licenses {
                    license {
                        name.set("GPLv3")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.html")
                        distribution.set("repo")
                    }
                }
            }
        }
    }
}

afterEvaluate {
    System.getenv("SIGNING_KEY")?.let { signingKey ->
        signing {
            isRequired = true
            useInMemoryPgpKeys(signingKey, System.getenv("SIGNING_PASS"))
            sign(publishing.publications.findByName("release"))
        }
    }
}
