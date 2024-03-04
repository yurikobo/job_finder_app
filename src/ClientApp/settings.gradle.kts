pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Job Searching Client"
include(":app")
include(":feature:vacancy:data")
include(":feature:vacancy:domain")
include(":feature:vacancy:ui")
include(":core:network")
include(":feature:company:data")
include(":feature:company:domain")
include(":feature:company:ui")
