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
include(":core:network")
include(":feature:vacancy:data")
include(":feature:vacancy:domain")
include(":feature:vacancy:ui")
include(":feature:company:data")
include(":feature:company:domain")
include(":feature:company:ui")
include(":core:common")
include(":core:featureapi")
