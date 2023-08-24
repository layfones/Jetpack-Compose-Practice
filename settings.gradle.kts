pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://www.jitpack.io")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://www.jitpack.io")
    }
    // versionCatalogs {
    //     create("depLibs") {
    //         from(files("./gradle/libs.versions.toml"))
    //     }
    // }
}
rootProject.name = "ComposeWanandroid"
include(":app")
